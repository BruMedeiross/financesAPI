package com.brunadev.rapidApi.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.brunadev.rapidApi.presenter.Main.MainViewModel
import com.brunadev.rapidApi.presenter.Main.MainActivity as MainActivity

fun Activity.hideKeyboard() {
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    var view: View? = currentFocus
    if (view == null) {
        view = View(this)
    }

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

//fun Activity.animationEnd(callback: () -> Unit): AnimatorListenerAdapter {
//    return object : AnimatorListenerAdapter() {
//        override fun onAnimationEnd(animation: Animator?) {
//            callback.invoke()
//        }
//    }
//}
