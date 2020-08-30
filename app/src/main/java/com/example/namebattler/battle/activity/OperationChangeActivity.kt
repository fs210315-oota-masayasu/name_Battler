package com.example.namebattler.battle.activity

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.R
import com.example.namebattler.data.battleData.Operation
import com.example.namebattler.menu.HeaderOfOperationChangeFragment

class OperationChangeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.operation_change)

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.header_area,
                HeaderOfOperationChangeFragment.newInstance(
                    "作戦"
                )
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }

        val operationInstance = Operation().getInstance()

        var selectedOperationName : String? = operationInstance.get()


        val radioGroup = findViewById<RadioGroup>(R.id.grp_select_operation)

        //RadioGroup配下の各RadioButtonを取得
        val offensiveBtn = findViewById<RadioButton>(radioGroup[0].id)
        val offensiveName = offensiveBtn.text.toString()

        val defensiveBtn = findViewById<RadioButton>(radioGroup[1].id)
        val defensiveName = defensiveBtn.text.toString()

        val flexibleBtn = findViewById<RadioButton>(radioGroup[2].id)
        val flexibleName = flexibleBtn.text.toString()

        //チェック済みボタンの初期位置を決める
        when(selectedOperationName){
            offensiveName -> offensiveBtn.isChecked = true
            defensiveName -> defensiveBtn.isChecked = true
            flexibleName -> flexibleBtn.isChecked = true
        }

        var radioButton : RadioButton?

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            // checkedIdから、選択されたRadioButtonを取得
            radioButton = findViewById(checkedId)

            operationInstance.operationName = radioButton?.text.toString()

        }
    }
}