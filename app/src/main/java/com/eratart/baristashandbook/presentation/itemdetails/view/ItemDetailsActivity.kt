package com.eratart.baristashandbook.presentation.itemdetails.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.utils.PluralsUtil
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.core.ext.loadImageWithGlide
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.setHeight
import com.eratart.baristashandbook.databinding.ActivityItemDetailsBinding
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentation.itemdetails.view.recycler.ingredients.IngredientAdapter
import com.eratart.baristashandbook.presentation.itemdetails.view.recycler.instructions.InstructionAdapter
import com.eratart.baristashandbook.presentation.itemdetails.viewmodel.ItemDetailsViewModel
import com.eratart.baristashandbook.presentationbase.sharebottomsheet.ShareBottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailsActivity : BaseActivity<ItemDetailsViewModel, ActivityItemDetailsBinding>() {

    companion object {
        const val EXTRAS_ITEM = "ItemDetailsActivity.EXTRAS_ITEM"
    }

    private val item by lazy { intent.getParcelableExtra<Item>(EXTRAS_ITEM) }

    override val viewModel: ItemDetailsViewModel by viewModel()
    override val binding by lazy { ActivityItemDetailsBinding.inflate(layoutInflater) }

    private val appBar by lazy { binding.appBar }
    private val ivDrink by lazy { binding.ivDrink }
    private val tvDrinkTitle by lazy { binding.tvDrinkTitle }
    private val tvDrinkSubtitle by lazy { binding.tvDrinkSubtitle }
    private val fbFavorite by lazy { binding.fbFavorite }
    private val tvPortions by lazy { binding.tvPortions }
    private val tvDish by lazy { binding.tvDish }
    private val rvIngredients by lazy { binding.rvIngredients }
    private val rvInstructions by lazy { binding.rvInstructions }

    override fun initView() {
        appBar.init(this)
        appBar.initShareBtn(AnalyticsEvents.click_item_details_share) {
            ShareBottomSheetDialogFragment.show(supportFragmentManager, item = item)
        }

        ivDrink.setHeight(getScreenWidth())
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(initData, ::handleInitData)
        }
        viewModel.fetchIsFavorite(item)
    }

    private fun handleInitData(initData: Triple<Boolean, Dish, ItemCategory>) {
        item?.apply {
            initItem(this, initData)
        }
    }

    private fun initItem(item: Item, data: Triple<Boolean, Dish, ItemCategory>) {
        if (item.photos.isNotEmpty()) {
            ivDrink.loadImageWithGlide(item.photos.first())
        } else {
            ivDrink.loadImageWithGlide(R.drawable.ic_placeholder)
        }
        tvDrinkTitle.text = item.title
        tvDrinkSubtitle.text = getString(R.string.main_menu_drinks)
            .plus(StringConstants.SPACE)
            .plus(StringConstants.BIG_DOT)
            .plus(StringConstants.SPACE)
            .plus(data.third.title)

        tvPortions.text = PluralsUtil.getQuantityString(
            context = this,
            number = item.portionsAmount,
            one = R.string.one_portion,
            two = R.string.two_portions,
            five = R.string.five_portions,
            zero = R.string.zero_portions
        )

        tvDish.text = data.second.title
        tvDish.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_item_details_dish)
            globalNavigator.startDishDetailsActivity(this@ItemDetailsActivity, data.second)
        }

        fbFavorite.setFavorite(data.first, false)
        fbFavorite.setOnCheckChangeListener { onFavoriteChange ->
            analyticsManager.logEvent(AnalyticsEvents.click_item_details_favorites)
            viewModel.markFavorite(item, onFavoriteChange)
        }

        rvIngredients.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = IngredientAdapter(item.ingredients.toMutableList())
        }

        rvInstructions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = InstructionAdapter(item.instructions.toMutableList())
        }
    }
}