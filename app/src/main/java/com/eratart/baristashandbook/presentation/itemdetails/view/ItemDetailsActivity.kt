package com.eratart.baristashandbook.presentation.itemdetails.view

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.utils.PluralsUtil
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.core.ext.loadImageWithGlide
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.mock.DishesMock
import com.eratart.baristashandbook.databinding.ActivityItemDetailsBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentation.itemdetails.view.recycler.ingredients.IngredientAdapter
import com.eratart.baristashandbook.presentation.itemdetails.view.recycler.instructions.InstructionAdapter
import com.eratart.baristashandbook.presentation.itemdetails.viewmodel.ItemDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailsActivity : BaseActivity<ItemDetailsViewModel, ActivityItemDetailsBinding>() {

    companion object {
        const val EXTRAS_ITEM = "ItemDetailsActivity.EXTRAS_ITEM"
        const val EXTRAS_ITEM_CATEGORY = "ItemDetailsActivity.EXTRAS_ITEM_CATEGORY"
    }

    private val item by lazy { intent.getParcelableExtra<Item>(EXTRAS_ITEM) }
    private val category by lazy { intent.getParcelableExtra<ItemCategory>(EXTRAS_ITEM_CATEGORY) }

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
        appBar.initMoreBtn { }

        val ivDrinkParams = ivDrink.layoutParams as ConstraintLayout.LayoutParams
        ivDrinkParams.height = getScreenWidth()
        ivDrink.layoutParams = ivDrinkParams

        item?.run {
            initItem(this)
        }
    }

    private fun initItem(item: Item) {
        if (item.photos.isNotEmpty()) {
            ivDrink.loadImageWithGlide(item.photos.first())
        }
        tvDrinkTitle.text = item.title
        tvDrinkSubtitle.text = getString(R.string.main_menu_drinks)
            .plus(StringConstants.SPACE)
            .plus(StringConstants.BIG_DOT)
            .plus(StringConstants.SPACE)
            .plus(category?.title.orEmpty())

        tvPortions.text = PluralsUtil.getQuantityString(
            context = this,
            number = item.portionsAmount,
            one = R.string.one_portion,
            two = R.string.two_portions,
            five = R.string.five_portions,
            zero = R.string.zero_portions
        )
        tvDish.text = item.dish
        tvDish.setOnClickListener {
            globalNavigator.startDishDetailsActivity(this, DishesMock.getDish(1))
        }

        fbFavorite.setOnCheckChangeListener { onFavoriteChange ->
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

    override fun initViewModel() {
        viewModel.apply {
            observe(isFavorite, ::handleIsFavorite)
        }
        viewModel.fetchIsFavorite(item)
    }

    private fun handleIsFavorite(isFavorite: Boolean) {
        fbFavorite.setFavorite(isFavorite, false)
    }
}