package com.zelyder.mathtest.ui.adapters

import androidx.databinding.BindingAdapter
import com.zelyder.mathtest.interfaces.KeyboardOutput
import com.zelyder.mathtest.ui.fragments.Keyboard

@BindingAdapter(value = ["app:keys", "app:keyboardOutput"], requireAll = true)
fun setKeys(keyboard: Keyboard, keys: Array<String>, keyboardOutput: KeyboardOutput){
    keyboard.setUpKeyboard(keyboardOutput, keys)
}