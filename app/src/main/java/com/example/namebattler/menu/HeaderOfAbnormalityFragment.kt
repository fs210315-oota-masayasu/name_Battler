package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namebattler.databinding.FragmentHeaderOfBattleMainBinding

class HeaderOfAbnormalityFragment: Fragment() {

    private lateinit var binding: FragmentHeaderOfBattleMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHeaderOfBattleMainBinding.inflate(inflater, container, false)
        return binding.root
    }
}