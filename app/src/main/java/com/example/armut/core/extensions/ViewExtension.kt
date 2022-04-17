package com.example.armut.core.extensions

import android.view.View
import android.widget.Toast
import com.example.armut.App

/**
 * Extension function to show toast message
 * @author Dawar Malik.
 */
fun Any.showToastMsg(message: String?) {
    Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * An Extension to make view Visible
 * @author Dawar Malik.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * An Extension to make view Gone
 * @author Dawar Malik.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * An Extension to make view visible conditionally
 * @author Dawar Malik.
 */

fun View.conditionallyVisible(isVisible: Boolean = true){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}