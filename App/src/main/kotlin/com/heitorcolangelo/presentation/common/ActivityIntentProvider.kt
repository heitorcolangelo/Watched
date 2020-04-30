package com.heitorcolangelo.presentation.common

import android.content.Intent

object ActivityIntentProvider {

    private const val PACKAGE_NAME = "com.heitorcolangelo"

    /**
     * An [android.app.Activity] that can be accessed by an intent.
     */
    interface AccessibleActivity {
        /**
         * The activity class name, for example:
         * com.heitorcolangelo.dummy.ui.DummyActivity
         */
        val className: String

        fun intentTo(): Intent {
            return Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)
        }
    }

    object Dummy : AccessibleActivity {
        override val className = "$PACKAGE_NAME.dummy.ui.DummyActivity"
    }
}
