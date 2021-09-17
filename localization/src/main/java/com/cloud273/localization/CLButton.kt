package com.cloud273.localization

import android.content.Context
import android.util.AttributeSet

class CLButton: androidx.appcompat.widget.AppCompatButton {

    constructor(context: Context): super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Localization,0,0).apply {
            val localizedText: String?
            try {
                localizedText = getString(R.styleable.Localization_lText)
            } finally {
                recycle()
            }
            if (localizedText != null) {
                lText = localizedText
            }
        }
    }

}