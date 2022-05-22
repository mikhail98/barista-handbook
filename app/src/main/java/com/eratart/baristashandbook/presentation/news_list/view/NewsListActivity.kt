package com.eratart.baristashandbook.presentation.news_list.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.ext.getBitmapFromUrl
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.replaceAllWith
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.presentation.news_list.di.newsListModule
import com.eratart.baristashandbook.presentation.news_list.viewmodel.NewsListViewModel
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity
import com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler.NewsViewHolder
import com.eratart.baristashandbook.tools.share.IShareTool
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListActivity : BaseItemsListActivity<NewsListViewModel>(), NewsViewHolder.INewsListener {

    override val titleRes = R.string.main_menu_news
    override var swipeEnabled = false
    override val viewModel: NewsListViewModel by viewModel()
    private val shareTool: IShareTool by inject()

    override val koinModules = listOf(newsListModule)

    override val searchAnalyticsEvent by lazy { AnalyticsEvents.click_news_list_search }

    private var newsToShare: NewsBot? = null

    override fun initView() {
        itemAdapter.setNewsListener(this)
        super.initView()
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(news, ::handleNews)
        }
    }

    private fun handleNews(news: List<NewsBot>) {
        sourceList.replaceAllWith(news)
        showContent(news, false)
    }

    override fun onItemClick(item: Any, pos: Int) {
        when (item) {
            is NewsBot -> {
                if (item.text != null) {
                    analyticsManager.logEvent(AnalyticsEvents.click_news_list_item)
                    globalNavigator.startNewsDetailsActivity(this, item)
                }
            }
        }
    }

    override fun onOpenInBrowserClick(url: String) {
        analyticsManager.logEvent(AnalyticsEvents.click_news_list_item_open_in_browser)
        globalNavigator.openInBrowser(this, url)
    }

    override fun onShareClick(news: NewsBot) {
        analyticsManager.logEvent(AnalyticsEvents.click_news_list_item_share)
        newsToShare = news
        shareNews(news)
    }

    private fun shareNews(news: NewsBot) {
        getBitmapFromUrl(news.photoUrl.orEmpty()) { bitmap ->
            val textToShare = news.getTextToShare()
            if (bitmap != null) {
                shareTool.shareImage(bitmap, textToShare, news.title)
            } else {
                shareTool.shareText(textToShare, news.title)
            }
        }
    }

    override fun onWritePermissionsGranted() {
        newsToShare?.run {
            shareNews(this)
            newsToShare = null
        }
    }
}