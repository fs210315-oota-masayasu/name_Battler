package com.example.namebattler.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.R
import com.example.namebattler.databinding.ActivityFoundationBinding
import com.example.namebattler.presentation.fragment.party.PartyFormationFragment
import com.example.namebattler.function.viewModel.PartyFormationViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

class BattleActivity  : AppCompatActivity() {

    private val partyFormationViewModel: PartyFormationViewModel by viewModels{ getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityFoundationBinding>(this,
            R.layout.activity_foundation
        )

        //初期化処理
        partyFormationViewModel.initPartyFormationData()

        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.attach_screen,
                PartyFormationFragment()
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }
    }
}