package com.sotti.ui.kit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes

class OneLineListRowWithIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val icon: ImageView by lazy {
        findViewById<ImageView>(R.id.icon)
    }

    private val text: TextView by lazy {
        findViewById<TextView>(R.id.text)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.one_line_list_row_with_icon, this)
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.oneLineListRowWithIcon
        ) {
            icon.setImageResource(
                getResourceId(
                    R.styleable.oneLineListRowWithIcon_ollrwi_icon,
                    R.drawable.ic_outline_bluetooth_24dp
                )
            )

            text.text = getString(R.styleable.oneLineListRowWithIcon_ollrwi_text)
                ?: resources.getString(R.string.one_line_list_row)
        }
    }
}