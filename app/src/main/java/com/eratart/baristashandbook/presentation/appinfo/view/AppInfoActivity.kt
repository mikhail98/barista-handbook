package com.eratart.baristashandbook.presentation.appinfo.view

import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.fromHtml
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.core.ext.setHeight
import com.eratart.baristashandbook.core.util.TextViewUrlUtil.setLinksClickable
import com.eratart.baristashandbook.databinding.ActivityAppInfoBinding
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.presentation.appinfo.viewmodel.AppInfoViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppInfoActivity : BaseActivity<AppInfoViewModel, ActivityAppInfoBinding>() {

    private val shareTool: IShareTool by inject()
    override val viewModel: AppInfoViewModel by viewModel()

    override val binding by lazy { ActivityAppInfoBinding.inflate(layoutInflater) }
    private val appBar by lazy { binding.appBar }
    private val ivBg by lazy { binding.ivBg }
    private val tvAppVersionName by lazy { binding.tvAppVersionName }
    private val tvCreatedBy by lazy { binding.tvCreatedBy }

    private val clConnectWithUs by lazy { binding.clConnectWithUs }
    private val clRateApp by lazy { binding.clRateApp }

    override fun initView() {
        appBar.init(this)
        appBar.initShareBtn(AnalyticsEvents.click_app_info_share) {
            shareTool.shareApp()
        }
        ivBg.setHeight(getScreenWidth())
        tvAppVersionName.text = BuildConfig.VERSION_NAME
        tvCreatedBy.text = getString(R.string.app_info_created_by).fromHtml()
        tvCreatedBy.setLinksClickable {
            analyticsManager.logEvent(AnalyticsEvents.click_app_info_developer_link)
        }
        clRateApp.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_app_info_rate_app)
            globalNavigator.openInBrowser(this, BuildConfig.APP_URL_GOOGLE)
        }
        clConnectWithUs.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_app_info_connect_with_us)
            val email = getString(R.string.app_info_email)
            val subject = getString(R.string.app_info_email_subject)
            globalNavigator.openEmailApp(this, email, subject, null)
        }
    }

    override fun initViewModel() {
    }
}