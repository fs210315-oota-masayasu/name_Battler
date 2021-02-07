package com.example.namebattler.characters.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.HomeActivity
import com.example.namebattler.data.database.Characters
import com.example.namebattler.databinding.FragmentProcessingAfterCreationBinding
import com.example.namebattler.util.BackStack
import com.example.namebattler.viewModel.*


class AfterGenerationMenuFragment : Fragment() {

    private lateinit var binding: FragmentProcessingAfterCreationBinding

    private val setCharacterViewModel :CharacterViewModel by viewModels{ getViewModelFactory() }
    private lateinit var setOperationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProcessingAfterCreationBinding.inflate(inflater, container, false).apply {
            val character = setCharacterViewModel.characterStatus.value?:Characters()

            //続けて作成(キャラクタ作成画面へ戻る)
            btnContinueCharacterCreate.setOnClickListener {
                setCharacterViewModel.clearInputData()
                setCharacterViewModel.bindEditText.postValue("")
                //名前の重複チェック（重複=update、not=insert）
                setOperationDatabaseViewModel.apply {
                    if (setCharacterViewModel.isInsertCharacter){
                        insert(character)
                    }else{
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
                        insert(character)
                    }else{
                        update(character)
                    }

                }
                val backToHome = Intent(activity, HomeActivity::class.java)
                backToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(backToHome)
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
    }
}
