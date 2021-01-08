package com.example.namebattler

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.namebattler.characters.SecondTestFragment
import com.example.namebattler.characters.TestFragment
import com.example.namebattler.characters.fragment.CharacterListFragment
import com.example.namebattler.databinding.ActivityCharacterListBinding
import com.example.namebattler.databinding.ActivityFoundationBinding
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.viewModel.ViewModelFactory
import com.example.namebattler.viewModel.getViewModelFactory

class TestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityFoundationBinding = DataBindingUtil.setContentView(this,R.layout.activity_foundation )

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.attach_screen,
                TestFragment()
            )

            // 張り付けを実行
            fragmentTransaction.commit()
        }
    }
}