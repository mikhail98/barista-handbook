package com.eratart.baristashandbook.presentation.dishdetails.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.*
import com.eratart.baristashandbook.core.util.TextViewUrlUtil.setLinksClickable
import com.eratart.baristashandbook.core.util.markdown.MarkdownUtil.renderMD
import com.eratart.baristashandbook.databinding.ActivityDishDetailsBinding
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.presentation.dishdetails.di.dishDetailsModule
import com.eratart.baristashandbook.presentation.dishdetails.viewmodel.DishDetailsViewModel
import com.eratart.baristashandbook.tools.share.IShareUtil
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishDetailsActivity : BaseActivity<DishDetailsViewModel, ActivityDishDetailsBinding>() {

    companion object {
        const val EXTRAS_DISH = "DishDetailsActivity.EXTRAS_DISH"
    }

    private val shareUtil: IShareUtil by inject()
    private val dish by lazy { intent.getParcelableExtra<Dish>(EXTRAS_DISH) }
    private val items by lazy { mutableListOf<Item>() }

    override val viewModel: DishDetailsViewModel by viewModel()
    override val binding by lazy { ActivityDishDetailsBinding.inflate(layoutInflater) }
    override val koinModules = listOf(dishDetailsModule)

    private val appBar by lazy { binding.appBar }
    private val ivDish by lazy { binding.ivDish }
    private val tvDishTitle by lazy { binding.tvDishTitle }
    private val tvVolume by lazy { binding.tvVolume }
    private val tvDishDescription by lazy { binding.tvDishDescription }

    override fun initView() {
        appBar.init(this)

        ivDish.setHeight(getScreenWidth())

        dish?.run {
            initDish(this)
        }
    }

    private fun initDish(dish: Dish) {
        appBar.initShareBtn(AnalyticsEvents.click_dish_details_share) {
            onWritePermissionsGranted()
        }
        if (dish.photos.isNotEmpty()) {
            ivDish.loadImageWithGlide(dish.photos.first())
        } else {
            ivDish.loadImageWithGlide(R.drawable.ic_placeholder)
        }
        tvDishTitle.text = dish.title
        tvVolume.text = dish.volume
        tvDishDescription.renderMD(dish.description)
        tvDishDescription.setLinksClickable { link ->
            items.find { item -> item.id == link }?.apply {
                analyticsManager.logEvent(AnalyticsEvents.click_dish_details_hyperlink)
                globalNavigator.startItemDetailsActivity(this@DishDetailsActivity, this)
            }
        }
    }

    override fun onWritePermissionsGranted() {
        dish?.run {
            shareUtil.shareDishAsText(this)
        }
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(items, ::handleItems)
        }
    }

    private fun handleItems(items: List<Item>) {
        this.items.replaceAllWith(items)
    }
}