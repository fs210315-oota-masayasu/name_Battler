package com.example.namebattler.util

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

open class ScopedAppActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}