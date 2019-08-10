package com.sotti.watch.error.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.google.android.material.button.MaterialButton
import com.sotti.watch.utils.inflate

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val image by lazy {
        findViewById<ImageView>(R.id.error_view_image)
    }

    private val title by lazy {
        findViewById<TextView>(R.id.error_view_title)
    }

    private val subtitle by lazy {
        findViewById<TextView>(R.id.error_view_subtitle)
    }

    private val retry by lazy {
        findViewById<MaterialButton>(R.id.error_view_retry)
    }

    var onRetryClickListener: (() -> Unit)? = null

    init {
        inflate(R.layout.error_view)
        readAttributes(context, attrs)
        retry.setOnClickListener { onRetryClickListener?.invoke() }
    }

    private fun readAttributes(context: Context, attributeSet: AttributeSet?) {
        context.withStyledAttributes(
            set = attributeSet,
            attrs = R.styleable.ErrorView
        ) {
            image.setImageResource(
                getResourceId(
                    R.styleable.ErrorView_erv_image,
                    R.drawable.image_cloud_sad
                )
            )

            title.text = getString(R.styleable.ErrorView_erv_title)
                ?: resources.getString(R.string.error_view_title_generic)

            subtitle.text = getString(R.styleable.ErrorView_erv_subtitle)
                ?: resources.getString(R.string.error_view_subtitle_generic)

            retry.text = getString(R.styleable.ErrorView_erv_buttonText)
                ?: resources.getString(R.string.retry)
        }
    }
}


