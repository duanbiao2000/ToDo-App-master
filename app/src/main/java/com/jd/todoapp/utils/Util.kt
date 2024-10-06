package com.jd.todoapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

// 隐藏键盘
fun hideKeyboard(activity: Activity) {
    // 获取输入法管理器
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    // 获取当前焦点视图
    val currentFocusedView = activity.currentFocus
    // 如果当前焦点视图不为空
    currentFocusedView?.let {
        // 隐藏软键盘
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}