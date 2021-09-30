package com.cloud273.localization

import android.content.Context
import android.util.AttributeSet

class CLTextInputEditText: com.google.android.material.textfield.TextInputEditText {

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
            val localizedHint: String?
            try {
                localizedHint = getString(R.styleable.Localization_lHint)
            } finally {
                recycle()
            }
            if (localizedHint != null) {
                lHint = localizedHint
            }
        }
    }

}