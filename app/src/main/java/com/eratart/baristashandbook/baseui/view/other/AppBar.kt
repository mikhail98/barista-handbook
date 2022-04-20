package com.eratart.baristashandbook.baseui.view.other

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.utils.KeyboardUtil
import com.eratart.baristashandbook.core.constants.LongConstants
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.invisible
import com.eratart.baristashandbook.core.ext.loadImageWithGlide
import com.eratart.baristashandbook.core.ext.visible
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AppBar(context: Context, attributeSet: AttributeSet? = null) :
    FrameLayout(context, attributeSet) {

    private val view = inflate(context, R.layout.view_app_bar, this)

    private val clRoot: ConstraintLayout by lazy { view.findViewById(R.id.clRoot) }
    private val tvTitle: MaterialTextView by lazy { view.findViewById(R.id.tvTitle) }
    private val tvSubtitle: MaterialTextView by lazy { view.findViewById(R.id.tvSubtitle) }
    private val etSearch: EditText by lazy { view.findViewById(R.id.etSearch) }
    private val btnBack: ImageView by lazy { view.findViewById(R.id.btnBack) }
    private val btnSearch: ImageView by lazy { view.findViewById(R.id.btnSearch) }
    private val btnShare: ImageView by lazy { view.findViewById(R.id.btnShare) }

    private val attributes by lazy {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.AppBar, 0, 0)
    }
    private var title: String? = null
    private var subtitle: String? = null
    private var searchHint: String? = null
    private var background: Int? = null
    private var tintColor: Int? = null

    private var isSearchBtnShown = false
    private var isShareBtnShown = false

    var isSearchActive: Boolean = false
    private var searchDebounce = LongConstants.ZERO
    private var searchListener: ((String) -> Unit)? = null

    private var activity: BaseActivity<*, *>? = null

    init {
        try {
            title = attributes.getString(R.styleable.AppBar_text)
            subtitle = attributes.getString(R.styleable.AppBar_subtitle)
            searchHint = attributes.getString(R.styleable.AppBar_searchHint)
            tintColor = attributes.getColor(
                R.styleable.AppBar_iconTitColor, context.getColor(R.color.gray_80)
            )
            background = attributes.getColor(
                R.styleable.AppBar_backColor, context.getColor(R.color.action_bar)
            )
        } finally {
            attributes.recycle()
        }

        setTitleText(title)
        setSubtitleText(subtitle)
        setBackgroundColor(background)
        setIconsTintColor(tintColor)
        setHintText(searchHint)

        etSearch.setOnFocusChangeListener { view, isFocused ->
            if (isFocused) {
                KeyboardUtil.show(view.context, view)
            }
        }

        initBackBtn()
    }

    fun init(activity: BaseActivity<*, *>) {
        this.activity = activity
    }

    fun initShareBtn(event: String?, listener: (() -> Unit)?) {
        isShareBtnShown = true
        if (listener != null) {
            btnShare.visible()
            btnShare.setOnClickListener {
                event?.run {
                    activity?.analyticsManager?.logEvent(this)
                }
                listener.invoke()
            }
        } else {
            btnShare.gone()
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun initSearchBtn(searchDebounce: Long, event: String, listener: (String) -> Unit) {
        isSearchBtnShown = true
        this.searchDebounce = searchDebounce
        this.searchListener = listener
        btnSearch.visible()
        btnSearch.setOnClickListener {
            if (isSearchActive) {
                etSearch.setText(StringConstants.EMPTY)
            } else {
                activity?.analyticsManager?.logEvent(event)
                activateSearch()
            }
        }

        channelFlow {
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(editable: Editable?) {
                    offer(editable.toString())
                }
            }

            etSearch.addTextChangedListener(textWatcher)

            awaitClose {
                etSearch.removeTextChangedListener(textWatcher)
            }
        }.debounce(searchDebounce)
            .onEach { listener.invoke(it) }
            .launchIn(CoroutineScope(Dispatchers.Main))
    }

    private fun initBackBtn() {
        btnBack.setOnClickListener {
            if (isSearchActive) {
                deactivateSearch()
            } else {
                activity?.onBackPressed()
            }
        }
    }

    fun setTitleText(title: String?) {
        if (!title.isNullOrEmpty()) {
            tvTitle.text = title
        }
    }

    fun setHintText(hint: String?) {
        if (!hint.isNullOrEmpty()) {
            etSearch.hint = hint
        }
    }

    fun setSubtitleText(subtitle: String?) {
        if (subtitle != null) {
            tvSubtitle.visible()
            tvSubtitle.text = subtitle
        } else {
            tvSubtitle.gone()
        }
    }

    fun setIconsTintColor(color: Int?) {
        val colorToApply = color ?: context.getColor(R.color.gray_80)
        btnBack.setColorFilter(colorToApply)
        btnSearch.setColorFilter(colorToApply)
        btnShare.setColorFilter(colorToApply)
    }

    fun setBackgroundColor(color: Int?) {
        color?.run {
            clRoot.setBackgroundColor(this)
        }
    }

    private fun activateSearch() {
        isSearchActive = true
        etSearch.visible()
        etSearch.requestFocus()

        btnSearch.loadImageWithGlide(R.drawable.ic_clear)

        btnShare.gone()
        tvTitle.invisible()
        tvSubtitle.gone()
    }

    fun deactivateSearch() {
        isSearchActive = false

        etSearch.setText(StringConstants.EMPTY)
        etSearch.gone()
        KeyboardUtil.hide(activity)

        btnSearch.loadImageWithGlide(R.drawable.ic_search)

        if (isShareBtnShown) {
            btnShare.visible()
        }
        tvTitle.visible()
        if (!tvSubtitle.text.isNullOrEmpty()) {
            tvSubtitle.visible()
        }
    }
}