package com.example.messenger.ui.main.utils

import android.content.Context
import android.widget.Toast


fun getToast(message: String,context:Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}