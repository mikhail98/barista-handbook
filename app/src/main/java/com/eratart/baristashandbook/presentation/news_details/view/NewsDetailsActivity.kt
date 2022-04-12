package com.eratart.baristashandbook.presentation.news_details.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.*
import com.eratart.baristashandbook.databinding.ActivityNewsDetailsBinding
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.presentation.news_details.viewmodel.NewsDetailsViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.ShareTool
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailsActivity : BaseActivity<NewsDetailsViewModel, ActivityNewsDetailsBinding>() {

    companion object {
        const val EXTRAS_NEWS = "NewsDetailsActivity.EXTRAS_NEWS"
    }

    private val news by lazy { intent.getParcelableExtra<NewsBot>(EXTRAS_NEWS) }

    override val viewModel: NewsDetailsViewModel by viewModel()
    override val binding by lazy { ActivityNewsDetailsBinding.inflate(layoutInflater) }
    private val appBar by lazy { binding.appBar }
    private val ivNews by lazy { binding.ivNews }
    private val tvNewsTitle by lazy { binding.tvNewsTitle }
    private val tvNewsDate by lazy { binding.tvNewsDate }
    private val tvNewsText by lazy { binding.tvNewsText }
    private val btnShowInBrowser by lazy { binding.btnShowInBrowser }

    private val shareTool: IShareTool by lazy { ShareTool(this) }

    override fun initView() {
        appBar.init(this)

        ivNews.setHeight(getScreenWidth())

        news?.run {
            appBar.initShareBtn(AnalyticsEvents.click_news_details_share) {
                getBitmapFromUrl(photoUrl.orEmpty()) { bitmap ->
                    val textToShare = getTextToShare()
                    if (bitmap != null) {
                        shareTool.shareImage(bitmap, textToShare, title)
                    } else {
                        shareTool.shareText(textToShare, title)
                    }
                }
            }
            initNews(this)
        }
    }

    private fun initNews(item: NewsBot) {
        if (item.photoUrl != null) {
            ivNews.loadImageWithGlide(item.photoUrl)
        } else {
            ivNews.loadImageWithGlide(R.drawable.ic_placeholder)
        }
        tvNewsDate.text = item.date.getDateTime()
        tvNewsTitle.text = item.title
        tvNewsText.text = item.text
        if (item.url != null) {
            btnShowInBrowser.visible()
            btnShowInBrowser.setOnClickListener {
                analyticsManager.logEvent(AnalyticsEvents.click_news_details_open_in_browser)
                globalNavigator.openInBrowser(this, item.url)
            }
        }
    }

    override fun initViewModel() {
    }
}