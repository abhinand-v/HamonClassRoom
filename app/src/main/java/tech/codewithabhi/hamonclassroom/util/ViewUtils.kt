package tech.codewithabhi.hamonclassroom.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}