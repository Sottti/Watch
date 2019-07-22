package com.sotti.watch.one.line.row.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.sotti.watch.utils.dimensToPx
import com.sotti.watch.utils.inflate
import com.sotti.watch.utils.setSelectableItemBackground


class OneLineRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val icon by lazy {
        findViewById<ImageView>(R.id.icon)
    }

    private val text by lazy {
        findViewById<TextView>(R.id.text)
    }

    init {
        setStyle()
        inflate(R.layout.one_line_row)
        readAttributes(context, attrs)
    }

    private fun readAttributes(context: Context, attrs: AttributeSet?) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.OneLineListRow
        ) {
            icon.setImageResource(
                getResourceId(
                    R.styleable.OneLineListRow_olr_icon,
                    R.drawable.ic_outline_bluetooth_24dp
                )
            )

            text.text = getString(R.styleable.OneLineListRow_olr_text)
                ?: resources.getString(R.string.one_line_list_row)
        }
    }

    private fun setStyle() {
        isClickable = true
        isFocusable = true
        setSelectableItemBackground()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(
                dimensToPx(R.dimen.one_line_row_list__with_icon__height),
                MeasureSpec.EXACTLY
            )
        )
    }
}