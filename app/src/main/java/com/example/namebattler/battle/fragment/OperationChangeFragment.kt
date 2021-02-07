package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.OperationChangeBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.viewModel.BattleViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class OperationChangeFragment: Fragment() {

    private lateinit var binding: OperationChangeBinding
    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OperationChangeBinding.inflate(inflater, container, false).apply {

            battleViewModel = this@OperationChangeFragment.battleViewModel

            //ヘッダー
            /** バトルメイン画面へ戻る際のヘッダー情報更新はHeaderOfOperationChangeFragmentの中で処理する **/
            if (savedInstanceState == null){
                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                // インスタンスに対して張り付け方を指定する
                fragmentTransaction.replace(
                    R.id.header_area,
                    HeaderFragment()
                )
                // 張り付けを実行
                fragmentTransaction.commit()
            }

        }

        return binding.root
    }
}