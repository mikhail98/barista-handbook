package com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler

import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.core.ext.getDateTime
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.loadImageWithGlide
import com.eratart.baristashandbook.core.ext.visible
import com.eratart.baristashandbook.databinding.ItemNewsBinding
import com.eratart.baristashandbook.domain.model.NewsBot

class NewsViewHolder(private val binding: ItemNewsBinding, private val listener: INewsListener?) :
    BaseRecyclerViewHolder<Any, ViewBinding>(binding) {

    private val ivNews by lazy { binding.ivNews }
    private val tvNewsTitle by lazy { binding.tvNewsTitle }
    private val tvNewsText by lazy { binding.tvNewsText }
    private val tvNewsDate by lazy { binding.tvNewsDate }
    private val btnShare by lazy { binding.btnShare }
    private val btnOpenInBrowser by lazy { binding.btnOpenInBrowser }

    override fun bindItem(item: Any) {
        super.bindItem(item)
        item as NewsBot

        if (item.photoUrl != null) {
            ivNews.loadImageWithGlide(item.photoUrl)
        } else {
            ivNews.loadImageWithGlide(R.drawable.ic_placeholder)
        }

        tvNewsDate.text = item.date.getDateTime()
        tvNewsTitle.text = item.title
        if (item.text != null) {
            tvNewsText.visible()
            tvNewsText.text = item.text
        } else {
            tvNewsText.gone()
        }
        if (item.url != null) {
            btnOpenInBrowser.visible()
            btnOpenInBrowser.setOnClickListener {
                listener?.onOpenInBrowserClick(item.url)
            }
        } else {
            btnOpenInBrowser.gone()
        }
        btnShare.setOnClickListener {
            listener?.onShareClick(item)
        }
    }

    interface INewsListener {
        fun onOpenInBrowserClick(url: String)
        fun onShareClick(news: NewsBot)
    }

}