package com.example.namebattler

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder の実装（各要素を表示するための View を保持する）
 */

class CharaListViewHolder (private val view: View) : RecyclerView.ViewHolder(view) {
    val label: TextView by lazy {
        view.findViewById<TextView>(R.id.label)
    }
    val button: Button by lazy {
        view.findViewById<Button>(R.id.button)
    }
}