package com.example.scalioproject.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.scalioproject.R
import com.facebook.shimmer.ShimmerFrameLayout
import java.time.Duration


fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.profile_holder)
        .into(this)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun ShimmerFrameLayout.toggleVisibility(isLoading: Boolean){
    if(isLoading){
        visibility = View.VISIBLE
        startShimmer()
        return
    }
    visibility = View.GONE
}

fun Activity.hideKeyboard() {
    currentFocus?.let { view ->
        val imm =
            getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}
