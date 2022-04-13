package com.eratart.baristashandbook.tools.share

import android.content.Context
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.utils.PluralsUtil
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.getBitmapFromUrl
import com.eratart.baristashandbook.core.util.markdown.MarkdownUtil.getWithoutMd
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item

class ShareUtil(private val context: Context, private val shareTool: IShareTool): IShareUtil {

    override fun shareDishAsText(dish: Dish) {
        val text = dish.title
            .plus(StringConstants.NEW_LINE)
            .plus(context.getString(R.string.dish_details_volume))
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
            }
        } else {
            shareTool.shareText(text, dish.title)
        }
    }

    override fun shareItemAsText(item: Item) {
        val portionsText = PluralsUtil.getQuantityString(
            context = context,
            number = item.portionsAmount,
            one = R.string.one_portion,
            two = R.string.two_portions,
            five = R.string.five_portions,
            zero = R.string.zero_portions
        )

        var ingredientsText = context.getString(R.string.item_details_ingredients)
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

        var instructionsText = context.getString(R.string.item_details_instruction)
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
            }
        } else {
            shareTool.shareText(text, item.title)
        }
    }

}