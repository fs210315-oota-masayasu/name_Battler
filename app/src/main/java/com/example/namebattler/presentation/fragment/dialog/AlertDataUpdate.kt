package com.example.namebattler.presentation.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertDataUpdate: DialogFragment() {

    private lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // parentFragmentで親Fragmentにアクセスする
    override fun onAttach(context: Context) {
        super.onAttach(context)

        when {
            context is NoticeDialogListener -> listener = context
            parentFragment is NoticeDialogListener -> listener = parentFragment as NoticeDialogListener
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("確認")
            .setMessage("名前が重複しています\nデータを更新しますか？")
            .setPositiveButton("YES") { dialog, id ->
                println("dialog:$dialog which:$id")
                listener.onDialogPositiveClick(this)
            }
            .setNegativeButton("NO") { dialog, id ->
                println("dialog:$dialog which:$id")
                listener.onDialogNegativeClick(this)
            }

        return builder.create()

    }
}