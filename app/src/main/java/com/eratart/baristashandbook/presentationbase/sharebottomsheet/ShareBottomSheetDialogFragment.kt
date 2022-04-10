package com.eratart.baristashandbook.presentationbase.sharebottomsheet

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.fragment.BaseBottomSheetDialogFragment
import com.eratart.baristashandbook.baseui.utils.PluralsUtil
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.*
import com.eratart.baristashandbook.core.util.markdown.MarkdownUtil.getWithoutMd
import com.eratart.baristashandbook.databinding.BottomSheetShareBinding
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.tools.pdfcreator.IPdfCreator
import com.eratart.baristashandbook.tools.pdfcreator.PdfCreator
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.ShareTool

class ShareBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<BaseViewModel, BottomSheetShareBinding>() {

    companion object {
        private const val TAG = "ShareBottomSheetDialogFragment"
        private const val ARGS_ITEM = "args.item"
        private const val ARGS_DISH = "args.dish"

        private fun newInstance(item: Item? = null, dish: Dish? = null) =
            ShareBottomSheetDialogFragment().withArgs {
                putParcelable(ARGS_ITEM, item)
                putParcelable(ARGS_DISH, dish)
            }

        fun show(manager: FragmentManager, item: Item? = null, dish: Dish? = null) {
            newInstance(item, dish).show(manager, TAG)
        }
    }

    private val item by lazyArgument<Item>(ARGS_ITEM)
    private val dish by lazyArgument<Dish>(ARGS_DISH)

    override val viewModel: BaseViewModel? = null

    private val shareTool: IShareTool by lazy { ShareTool(requireActivity()) }
    private val pdfCreator: IPdfCreator by lazy { PdfCreator(requireActivity()) }

    private val llShareRoot by lazy { binding?.llRootShare }
    private val btnShareAsText by lazy { binding?.btnShareAsText }
    private val btnShareAsPdf by lazy { binding?.btnShareAsPdf }
    private val progress by lazy { binding?.progress }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val window = dialog?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.setDecorFitsSystemWindows(true)
        } else {
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        }
        return with(BottomSheetShareBinding.inflate(inflater)) {
            binding = this
            root
        }
    }

    override fun initView() {
        val height = (activity?.getScreenWidth() ?: IntConstants.ZERO) / IntConstants.TWO
        llShareRoot?.setHeight(height)

        btnShareAsPdf?.setOnClickListener {
            progress?.visibleWithAlpha()
            when {
                dish != null -> dish?.apply { shareDishAsPdf(this) }
                item != null -> item?.apply { shareItemAsPdf(this) }
            }
        }
        btnShareAsText?.setOnClickListener {
            progress?.visibleWithAlpha()
            when {
                dish != null -> dish?.apply { shareDishAsText(this) }
                item != null -> item?.apply { shareItemAsText(this) }
            }
        }

    }

    private fun shareDishAsText(dish: Dish) {
        val context = context ?: return
        val text = dish.title
            .plus(StringConstants.NEW_LINE)
            .plus(getString(R.string.dish_details_volume))
            .plus(StringConstants.SPACE)
            .plus(dish.volume)
            .plus(StringConstants.DOT)
            .plus(StringConstants.NEW_LINE)
            .plus(StringConstants.NEW_LINE)
            .plus(StringConstants.NEW_LINE)
            .plus(dish.description.getWithoutMd())

        if (dish.photos.isNotEmpty()) {
            context.getBitmapFromUrl(dish.photos.first()) { bitmap ->
                if (bitmap != null) {
                    shareTool.shareImage(bitmap, text, dish.title)
                }
                dismiss()
            }
        } else {
            shareTool.shareText(text, dish.title)
            dismiss()
        }
    }

    private fun shareItemAsText(item: Item) {
        val context = context ?: return
        val portionsText = PluralsUtil.getQuantityString(
            context = context,
            number = item.portionsAmount,
            one = R.string.one_portion,
            two = R.string.two_portions,
            five = R.string.five_portions,
            zero = R.string.zero_portions
        )

        var ingredientsText = getString(R.string.item_details_ingredients)
            .plus(StringConstants.SEMICOLON)
            .plus(StringConstants.NEW_LINE)
        item.ingredients.forEach { ingredient ->
            val ingredientText = ingredient.title
                .plus(StringConstants.SEMICOLON)
                .plus(StringConstants.SPACE)
                .plus(ingredient.volume)
                .plus(StringConstants.NEW_LINE)
            ingredientsText += ingredientText
        }

        var instructionsText = getString(R.string.item_details_instruction)
            .plus(StringConstants.SEMICOLON)
            .plus(StringConstants.NEW_LINE)
        item.instructions.forEachIndexed { pos, instruction ->
            val instructionText = pos.toString()
                .plus(StringConstants.DOT)
                .plus(StringConstants.SPACE)
                .plus(instruction)
                .plus(StringConstants.NEW_LINE)
            instructionsText += instructionText
        }
        val text = item.title
            .plus(StringConstants.NEW_LINE)
            .plus(portionsText)
            .plus(StringConstants.DOT)
            .plus(StringConstants.NEW_LINE)
            .plus(StringConstants.NEW_LINE)
            .plus(StringConstants.NEW_LINE)
            .plus(ingredientsText)
            .plus(StringConstants.NEW_LINE)
            .plus(instructionsText)

        if (item.photos.isNotEmpty()) {
            context.getBitmapFromUrl(item.photos.first()) { bitmap ->
                if (bitmap != null) {
                    shareTool.shareImage(bitmap, text, item.title)
                }
                dismiss()
            }
        } else {
            shareTool.shareText(text, item.title)
            dismiss()
        }
    }

    private fun shareDishAsPdf(dish: Dish) {
        pdfCreator.createPdfForDish(dish) { file ->
            if (file != null) {
                shareTool.sharePdf(file, dish.title)
            }
            dismiss()
        }
    }

    private fun shareItemAsPdf(item: Item) {
        pdfCreator.createPdfForItem(item) { file ->
            if (file != null) {
                shareTool.sharePdf(file, item.title)
            }
            dismiss()
        }
    }
}