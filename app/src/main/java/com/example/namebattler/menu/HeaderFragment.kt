package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.HeaderScreenBinding
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderFragment : Fragment() {

    private lateinit var binding: HeaderScreenBinding
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeaderScreenBinding.inflate(inflater, container, false)

        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        when (headerViewModel.outputFlag) {
            HeaderFlag.NONE -> {
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfAbnormalityFragment()
                )
            }
            HeaderFlag.DEFAULT -> {
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfDefaultFragment()
                )
            }

            HeaderFlag.BATTLE_MAIN -> {
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfBattleMainFragment()
                )
            }
            HeaderFlag.OPERATION_CHANGE -> {
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfOperationChangeFragment()
                )
            }
            HeaderFlag.RETURN_HOME -> {
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfReturnHomeFragment()
                )
            }

            HeaderFlag.PARTY_FORMATION -> {
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfReturnPartyFormationFragment()
                )
            }

        }
        fragmentTransaction.commit()
        return binding.root
    }
}