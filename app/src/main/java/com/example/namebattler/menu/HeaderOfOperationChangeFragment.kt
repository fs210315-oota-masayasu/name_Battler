package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.FragmentHeaderOfOperationChangeBinding
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderOfOperationChangeFragment : Fragment() {

    private lateinit var binding: FragmentHeaderOfOperationChangeBinding
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeaderOfOperationChangeBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfOperationChangeFragment.headerViewModel

            //ヘッダー情報更新（バトルメイン画面）
            this@HeaderOfOperationChangeFragment.headerViewModel.apply {
                headerText.postValue(getString(R.string.battle_main))
                outputFlag = HeaderFlag.BATTLE_MAIN
            }
            btnDecision.setOnClickListener {
                val fragmentManager = parentFragmentManager
                fragmentManager.popBackStack(BackStack.BATTLE_MAIN.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

        }
        return binding.root
    }
}