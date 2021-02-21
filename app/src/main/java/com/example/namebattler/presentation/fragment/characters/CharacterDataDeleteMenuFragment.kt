package com.example.namebattler.presentation.fragment.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.function.viewModel.OperationDatabaseViewModel
import com.example.namebattler.database.characterDatabase.Characters
import com.example.namebattler.databinding.FragmentCharacterDataDeleteMenuBinding
import com.example.namebattler.presentation.fragment.dialog.AlertDataDelete
import com.example.namebattler.function.BackStack
import com.example.namebattler.function.viewModel.CharacterViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

/** キャラクター詳細画面 削除ボタンFragment **/
class CharacterDataDeleteMenuFragment: Fragment(), AlertDataDelete.NoticeDialogListener{

    private lateinit var binding: FragmentCharacterDataDeleteMenuBinding

    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }

    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharacterDataDeleteMenuBinding.inflate(inflater, container, false).apply {
            characterViewModel = setCharacterViewModel
            operationDatabaseViewModel = operationDatabaseViewModel

            btnDeleteCharacterData.setOnClickListener {
                val dialog = AlertDataDelete()

                //childFragmentManagerを利用してDialogFragmentを自分の子として追加する
                dialog.show(childFragmentManager, "NoticeDialogFragment")
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        operationDatabaseViewModel =
            ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
    }

    //削除確認ダイアログボックスの処理（AlertDataDelete）
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        delete()
        val fragmentManager: FragmentManager = parentFragmentManager
        fragmentManager.popBackStack(BackStack.CHARACTER_LIST.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        println("NoticeDialogでCancelボタンが押されたよ！")
    }

    //キャラクター削除
    private fun delete(){
        operationDatabaseViewModel.delete(setCharacterViewModel.characterStatus.value?: Characters())
    }
}
