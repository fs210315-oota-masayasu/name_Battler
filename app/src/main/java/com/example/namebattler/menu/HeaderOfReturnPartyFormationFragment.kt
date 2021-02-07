package com.example.namebattler.menu

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
import com.example.namebattler.party.fragment.PartyFormationFragment
import com.example.namebattler.util.BackStack
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderOfReturnPartyFormationFragment: Fragment() {

    private lateinit var binding : FragmentHeaderDefaultBinding
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeaderDefaultBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfReturnPartyFormationFragment.headerViewModel


            /**「戻る」ボタンイベント **/
            btnScreenBack.setOnClickListener {
                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = parentFragmentManager

                fragmentManager.popBackStack(BackStack.PARTY_FORMATION.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)

/*                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                // インスタンスに対して張り付け方を指定する
                fragmentTransaction.replace(
                    R.id.attach_screen,
                    PartyFormationFragment()
                )
                // 張り付けを実行
                fragmentTransaction.commit()*/


            }

        }

        return binding.root
    }

}