package com.example.namebattler.presentation.fragment.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.FragmentHeaderDefaultBinding
import com.example.namebattler.databinding.FragmentHeaderOfBattleMainBinding
import com.example.namebattler.function.HeaderFlag
import com.example.namebattler.function.viewModel.HeaderViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory
import com.example.namebattler.presentation.fragment.characters.NewCharacterGenerateFragment

class HeaderOfConfirmGenerationCharacterFragment: Fragment() {
    private lateinit var binding: FragmentHeaderDefaultBinding
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeaderDefaultBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfConfirmGenerationCharacterFragment.headerViewModel

            /**「戻る」ボタンイベント **/
            btnScreenBack.setOnClickListener {
                //ヘッダー情報更新
                this@HeaderOfConfirmGenerationCharacterFragment.headerViewModel.headerText.postValue(getString(R.string.create_character))
                this@HeaderOfConfirmGenerationCharacterFragment.headerViewModel.outputFlag = HeaderFlag.NEW_CHARACTER_GENERATE

                val fragmentManager: FragmentManager = parentFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction.replace(
                    R.id.attach_screen,
                    NewCharacterGenerateFragment()
                )
                fragmentTransaction.commit()
            }

        }



        return binding.root
    }


}