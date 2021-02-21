package com.example.namebattler.presentation.fragment.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.namebattler.databinding.FragmentHeaderDefaultBinding
import com.example.namebattler.function.viewModel.HeaderViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

class HeaderOfDefaultFragment: Fragment() {

    private lateinit var binding :FragmentHeaderDefaultBinding
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeaderDefaultBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfDefaultFragment.headerViewModel
            /**「戻る」ボタンイベント **/
            btnScreenBack.setOnClickListener {
                val fragmentManager = parentFragmentManager
                fragmentManager.popBackStack()
            }
        }
        return binding.root
    }
}