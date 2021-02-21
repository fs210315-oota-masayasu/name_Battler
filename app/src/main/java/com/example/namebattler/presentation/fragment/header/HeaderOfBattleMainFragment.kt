package com.example.namebattler.presentation.fragment.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.namebattler.databinding.FragmentHeaderOfBattleMainBinding
import com.example.namebattler.function.viewModel.HeaderViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

class HeaderOfBattleMainFragment : Fragment() {

    private lateinit var binding: FragmentHeaderOfBattleMainBinding
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeaderOfBattleMainBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfBattleMainFragment.headerViewModel
        }

        return binding.root
    }
}