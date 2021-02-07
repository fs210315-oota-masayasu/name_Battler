package com.example.namebattler.characters.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.HomeActivity
import com.example.namebattler.R
import com.example.namebattler.characters.activity.CharacterListActivity
import com.example.namebattler.characters.activity.NewCharacterGenerateActivity
import com.example.namebattler.data.database.Characters
import com.example.namebattler.databinding.FragmentProcessingAfterCreationBinding
import com.example.namebattler.util.BackStack
import com.example.namebattler.viewModel.*
import kotlin.concurrent.thread


class AfterGenerationMenuFragment : Fragment() {

    private lateinit var binding: FragmentProcessingAfterCreationBinding

    private val setCharacterViewModel :CharacterViewModel by viewModels{ getViewModelFactory() }
    private lateinit var setOperationDatabaseViewModel: OperationDatabaseViewModel
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentProcessingAfterCreationBinding.inflate(inflater, container, false).apply {
            val character = setCharacterViewModel.characterStatus.value?:Characters()
            Log.d("++ 作成完了画面 ++", "Name ${character.NAME}")
            Log.d("++ 作成完了画面 ++", "Job ${character.JOB}")
            Log.d("++ 作成完了画面 ++", "Str ${character.STR}")
            Log.d("++ 作成完了画面 ++", "HP ${character.HP}")



            //続けて作成(キャラクタ作成画面へ戻る)
            btnContinueCharacterCreate.setOnClickListener {

                setCharacterViewModel.clearInputData()
                setCharacterViewModel.bindEditText.postValue("")



                //名前の重複チェック（重複=update、not=insert）
                setOperationDatabaseViewModel.apply {

                    if (setCharacterViewModel.isInsertCharacter){
                        Log.d("+++ INSERT", "insert")
                        insert(character)
                    }else{
                        Log.d("+++ UPDATE", "update")
                        update(character)
                    }

                }



                val fragmentManager: FragmentManager = parentFragmentManager
                fragmentManager.popBackStack(BackStack.NEW_CHARACTER_GENERATE.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }


            //作成を終了する（ホームへ戻る）
            btnEndToCharacterCreate.setOnClickListener {

                //名前の重複チェック（重複=update、not=insert）
                setOperationDatabaseViewModel.apply {

                    if (setCharacterViewModel.isInsertCharacter){
                        Log.d("+++ INSERT", "insert")
                        insert(character)
                    }else{
                        Log.d("+++ UPDATE", "update")
                        update(character)
                    }

                }
                val backToHome = Intent(activity, HomeActivity::class.java)
                backToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(backToHome)
            }



        }



        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_processing_after_creation, container, false)

        return binding.root
    }
/*    companion object {
        @JvmStatic
        fun newInstance(): AfterGenerationMenuFragment {
            return AfterGenerationMenuFragment()
        }
    }*/
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setOperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)



}
//    override fun onStart() {
//        super.onStart()
//
//
//
//        //続けて作成する
//
///*
//
//        btn_continue_character_create.setOnClickListener {
//            val intentOfContinue = Intent(activity,
//                NewCharacterGenerateActivity::class.java)
//            intentOfContinue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            startActivity(intentOfContinue)
//        }
//
//
//        //作成を終了する
//        btn_end_to_character_create.setOnClickListener {
//            val intentOfEnd = Intent(activity, HomeActivity::class.java)
//            intentOfEnd.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            startActivity(intentOfEnd)
//        }
//
//*/
//    }
}
