package com.example.namebattler.presentation.fragment.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.function.viewModel.OperationDatabaseViewModel
import com.example.namebattler.database.characterDatabase.Characters
import com.example.namebattler.databinding.FragmentCharacterDataDeleteMenuBinding
import com.example.namebattler.presentation.fragment.dialog.AlertDataDelete
import com.example.namebattler.function.BackStack
import com.example.namebattler.function.HeaderFlag
import com.example.namebattler.function.viewModel.CharacterViewModel
import com.example.namebattler.function.viewModel.HeaderViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

/** キャラクター詳細画面 削除ボタンFragment **/
class CharacterDataDeleteMenuFragment: Fragment(), AlertDataDelete.NoticeDialogListener{

    private lateinit var binding: FragmentCharacterDataDeleteMenuBinding

    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharacterDataDeleteMenuBinding.inflate(inflater, container, false).apply {
            characterViewModel = setCharacterViewModel

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

        //削除後の登録件数取得し直し
        operationDatabaseViewModel.confirmNumOfRegistrations()

        //取得しなおした登録件数でheaderModelを更新
        operationDatabaseViewModel.numOfRegistrations.observe(viewLifecycleOwner, {
            val setText = "${getString(R.string.character_list)} ( $it 人 )"

            headerViewModel.apply {
                headerText.postValue(setText)
                outputFlag = HeaderFlag.RETURN_HOME
            }
        })

        //キャラクター一覧画面（CharacterListFragment）へ画面を戻す
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
