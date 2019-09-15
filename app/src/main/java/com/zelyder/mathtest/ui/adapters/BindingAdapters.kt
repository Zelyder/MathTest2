package com.zelyder.mathtest.ui.adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import com.zelyder.mathtest.interfaces.KeyboardOutput
import com.zelyder.mathtest.ui.fragments.Keyboard

@BindingAdapter(value = ["app:keys", "app:keyboardOutput"], requireAll = true)
fun setKeys(keyboard: Keyboard, keys: Array<String>, keyboardOutput: KeyboardOutput){
    keyboard.setUpKeyboard(keyboardOutput, keys)
}

@BindingAdapter("app:src")
fun  bindSrcCompat(imageView: ImageView, imageResource: Int){
    imageView.setImageResource(imageResource)
}