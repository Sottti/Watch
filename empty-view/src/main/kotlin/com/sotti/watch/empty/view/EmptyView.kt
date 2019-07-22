package com.sotti.watch.empty.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.sotti.watch.utils.inflate

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val image by lazy {
        findViewById<ImageView>(R.id.empty_view_image)
    }

    private val title by lazy {
        findViewById<TextView>(R.id.empty_view_title)
    }

    private val subtitle by lazy {
        findViewById<TextView>(R.id.empty_view_subtitle)
    }

    init {
        inflate(R.layout.empty_view)
        readAttributes(context, attrs)
    }

    private fun readAttributes(context: Context, attributeSet: AttributeSet?) {
        context.withStyledAttributes(
            set = attributeSet,
            attrs = R.styleable.EmptyView
        ) {
            image.setImageResource(
                getResourceId(R.styleable.EmptyView_emv_image, R.drawable.image_sun_smile)
            )
            title.text = getString(R.styleable.EmptyView_emv_title)
                ?: resources.getString(R.string.empty_view_title)

            subtitle.text =
                getString(R.styleable.EmptyView_emv_subtitle)
                    ?: resources.getString(R.string.empty_view_subtitle)
        }
    }
}
