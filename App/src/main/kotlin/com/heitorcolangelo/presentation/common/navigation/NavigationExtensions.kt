package com.heitorcolangelo.presentation.common.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.heitorcolangelo.R
import timber.log.Timber

fun Fragment.navigateSafelyWithAnimation(
    navigation: Navigation,
    animation: NavigationAnimation = NavigationAnimation.SlideRightToLeft
) {
    navigateSafely(navigation, animation.options)
}

fun Fragment.navigateSafely(navigation: Navigation, navOptions: NavOptions? = null) {
    try {
        findNavController().navigate(navigation.direction, navOptions)
    } catch (exception: IllegalArgumentException) {
        Timber.e(exception, "Multiple navigation attempts handled.\n$exception")
    }
}

sealed class NavigationAnimation(val options: NavOptions) {
    object SlideRightToLeft : NavigationAnimation(
        NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
    )
}
