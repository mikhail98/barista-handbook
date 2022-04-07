package com.eratart.baristashandbook.presentation.dishdetails.view

import androidx.constraintlayout.widget.ConstraintLayout
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.core.ext.loadImageWithGlide
import com.eratart.baristashandbook.databinding.ActivityDishDetailsBinding
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.presentation.dishdetails.viewmodel.DishDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishDetailsActivity : BaseActivity<DishDetailsViewModel, ActivityDishDetailsBinding>() {

    companion object {
        const val EXTRAS_DISH = "ItemDetailsActivity.EXTRAS_DISH"
    }

    private val dish by lazy { intent.getParcelableExtra<Dish>(EXTRAS_DISH) }

    override val viewModel: DishDetailsViewModel by viewModel()
    override val binding by lazy { ActivityDishDetailsBinding.inflate(layoutInflater) }

    private val appBar by lazy { binding.appBar }
    private val ivDish by lazy { binding.ivDish }
    private val tvDishTitle by lazy { binding.tvDishTitle }
    private val tvVolume by lazy { binding.tvVolume }
    private val tvDishDescription by lazy { binding.tvDishDescription }

    override fun initView() {
        appBar.init(this)
        appBar.initMoreBtn { }

        val ivDishParams = ivDish.layoutParams as ConstraintLayout.LayoutParams
        ivDishParams.height = getScreenWidth()
        ivDish.layoutParams = ivDishParams

        dish?.run {
            initDish(this)
        }
    }

    private fun initDish(dish: Dish) {
        if (dish.photos.isNotEmpty()) {
            ivDish.loadImageWithGlide(dish.photos.first())
        }
        tvDishTitle.text = dish.title
        tvVolume.text = dish.volume
        tvDishDescription.text = dish.description
    }

    override fun initViewModel() {
    }
}