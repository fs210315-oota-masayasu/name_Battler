package com.example.namebattler

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.characters.fragment.CharacterListFragment
import com.example.namebattler.databinding.ActivityCharacterListBinding
import com.example.namebattler.databinding.ActivityFoundationBinding
import com.example.namebattler.party.PartyFormationFragment
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel

class CharacterEditActivity  : AppCompatActivity() {

//    private lateinit var characterViewModel: CharacterViewModel
//    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityFoundationBinding = DataBindingUtil.setContentView(this,R.layout.activity_foundation)

//        characterViewModel =
//            ViewModelProvider(this).get(CharacterViewModel::class.java)
//
//        operationDatabaseViewModel =
//            ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)


        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.attach_screen,
                CharacterListFragment()
            )


//            fragmentTransaction.replace(
//                R.id.attach_screen,
//                CharacterListFragment(operationDatabaseViewModel,characterViewModel)
//            )

            // 張り付けを実行
            fragmentTransaction.commit()
        }


    }



}