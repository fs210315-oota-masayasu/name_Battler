package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.data.battleData.Operation
import com.example.namebattler.databinding.OperationChangeBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.util.HeaderFlag
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

            //バトルメイン画面へ戻る際のヘッダー情報更新はHeaderOfOperationChangeFragmentの中で処理する

            //ヘッダー
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





//            val operationInstance = Operation().getInstance()

//            var selectedOperationName : String? = operationInstance.get()


//            val radioGroup = findViewById<RadioGroup>(R.id.grp_select_operation)

            //RadioGroup配下の各RadioButtonを取得
//            val offensiveBtn: RadioButton = grpSelectOperation.

//            val offensiveBtn = findViewById<RadioButton>(radioGroup[0].id)
//            val offensiveName = offensiveBtn
//
//            val defensiveBtn = findViewById<RadioButton>(radioGroup[1].id)
//            val defensiveName = defensiveBtn.text.toString()
//
//            val flexibleBtn = findViewById<RadioButton>(radioGroup[2].id)
//            val flexibleName = flexibleBtn.text.toString()
//
//            //チェック済みボタンの初期位置を決める
//            when(selectedOperationName){
//                offensiveName -> offensiveBtn.isChecked = true
//                defensiveName -> defensiveBtn.isChecked = true
//                flexibleName -> flexibleBtn.isChecked = true
//            }
//
//            var radioButton : RadioButton?

//            radioGroup.setOnCheckedChangeListener { _, checkedId ->
//                // checkedIdから、選択されたRadioButtonを取得
//                radioButton = findViewById(checkedId)
//
//                operationInstance.operationName = radioButton?.text.toString()
//
//            }

        }


        return binding.root
    }
}