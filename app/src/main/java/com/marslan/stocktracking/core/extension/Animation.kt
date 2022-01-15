package com.marslan.stocktracking.core.extension

import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation

fun View.scaleUpHorizontal(gravity: Int) {
    val anim = when (gravity) {
        Gravity.CENTER -> {
            ScaleAnimation(
                0f,
                1f,
                1f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
        Gravity.START -> {
            ScaleAnimation(
                0f,
                1f,
                1f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
        else -> {
            ScaleAnimation(
                0f,
                1f,
                1f,
                1f,
                Animation.RELATIVE_TO_SELF,
                1f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
    }
    val alphaAnimation = AlphaAnimation(0f,1f)
    alphaAnimation.duration = 100
    anim.duration = 200
    val set = AnimationSet(false)
    set.addAnimation(alphaAnimation)
    set.addAnimation(anim)
    startAnimation(set)
    visible()
}

fun View.scaleDownHorizontal(gravity: Int) {
    val anim = when (gravity) {
        Gravity.CENTER -> {
            ScaleAnimation(
                1f,
                0f,
                1f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
        Gravity.START -> {
            ScaleAnimation(
                1f,
                0f,
                1f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
        else -> {
            ScaleAnimation(
                1f,
                0f,
                1f,
                1f,
                Animation.RELATIVE_TO_SELF,
                1f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
    }
    val alphaAnimation = AlphaAnimation(1f,0f)
    alphaAnimation.duration = 100
    anim.duration = 200
    anim.setAnimationListener(object : Animation.AnimationListener{
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) { gone() }
        override fun onAnimationRepeat(animation: Animation?) {}
    })
    val set = AnimationSet(false)
    set.addAnimation(alphaAnimation)
    set.addAnimation(anim)
    startAnimation(set)
}

fun View.scaleUpVertical(gravity: Int) {
    val anim = when (gravity) {
        Gravity.TOP -> {
            ScaleAnimation(
                1f,
                1f,
                0f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f
            )
        }
        else -> {
            ScaleAnimation(
                1f,
                1f,
                0f,
                1f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                1f
            )
        }
    }
    val alphaAnimation = AlphaAnimation(0f,1f)
    alphaAnimation.duration = 100
    anim.duration = 200
    val set = AnimationSet(false)
    set.addAnimation(alphaAnimation)
    set.addAnimation(anim)
    startAnimation(set)
    visible()
}

fun View.scaleDownVertical(gravity: Int) {
    val anim = when (gravity) {
        Gravity.TOP -> {
            ScaleAnimation(
                1f,
                1f,
                1f,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f
            )
        }
        else -> {
            ScaleAnimation(
                1f,
                1f,
                1f,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                1f
            )
        }
    }
    val alphaAnimation = AlphaAnimation(1f,0f)
    alphaAnimation.duration = 100
    anim.duration = 200
    val set = AnimationSet(false)
    set.addAnimation(alphaAnimation)
    set.addAnimation(anim)
    startAnimation(set)
    gone()
}