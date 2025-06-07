package com.neurospherex.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowInsetsController
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.neurospherex.R
import kotlin.reflect.KClass

@Suppress("DEPRECATION")
fun AppCompatActivity.initiateStatusBar() {
    window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun AppCompatActivity.hideKeyboard(activity: Activity) {
    val view = activity.currentFocus
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    } ?: run {
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun <T : AppCompatActivity> AppCompatActivity.startScreen(activityClass: KClass<T>, extras: Bundle? = null, clearTask: Boolean = false) {
    val intent = Intent(this, activityClass.java)
    extras?.let { intent.putExtras(it) }
    if (clearTask) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    startActivity(intent)
}

@Suppress("DEPRECATION")
fun AppCompatActivity.showToast(message: String, isSuccess: Boolean) {
    val customToastLayout = layoutInflater.inflate(R.layout.toast_background_layout, null)
    val customToast = Toast(this).apply {
        view = customToastLayout
        duration = Toast.LENGTH_SHORT
        setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
    }

    val container = customToastLayout.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.container)
    val statusTextView = customToastLayout.findViewById<TextView>(R.id.status)
    val iconImageView = customToastLayout.findViewById<ImageView>(R.id.status_icon)
    statusTextView.text = message

    val (backgroundRes, iconRes) = if (isSuccess) {
        R.drawable.success_toast_background to R.drawable.success_toast_icon
    } else {
        R.drawable.failure_toast_background to R.drawable.failure_toast_icon
    }

    container.setBackgroundResource(backgroundRes)
    iconImageView.setImageResource(iconRes)
    customToast.show()
}