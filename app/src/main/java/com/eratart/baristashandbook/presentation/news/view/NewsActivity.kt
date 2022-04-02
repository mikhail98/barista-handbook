package com.eratart.baristashandbook.presentation.news.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityNewsBinding
import com.eratart.baristashandbook.presentation.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity :
    BaseActivity<NewsViewModel, ActivityNewsBinding>() {

    override val viewModel: NewsViewModel by viewModel()
    override val binding by lazy { ActivityNewsBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}