package com.example.namebattler.characters

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R

/**
 * ViewHolder の実装（各要素を表示するための View を保持する）
 */
class CharaListViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    //var label: TextView? = null
    var jobName: TextView? = null
    var button: Button? = null

    init {
        //label = view.findViewById(R.id.txt_job)
        jobName = view.findViewById(R.id.txt_job)
        button = view.findViewById(R.id.btn_hidden)
    }
}