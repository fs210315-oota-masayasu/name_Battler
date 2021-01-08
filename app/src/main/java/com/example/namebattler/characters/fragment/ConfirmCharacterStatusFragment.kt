package com.example.namebattler.characters.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.databinding.OutputScreenBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.viewModel.CharacterViewModel

//キャラクター詳細画面
class ConfirmCharacterStatusFragment: Fragment() {
    private lateinit var characterViewModel: CharacterViewModel

    private lateinit var binding: OutputScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.output_screen, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = parentFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.header_area,
                HeaderFragment.newInstance(
                    "キャラ詳細"
                )
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }

        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = parentFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する

            //ステータス情報
            fragmentTransaction.replace(
                R.id.container_area,
                CharacterStatusDetailFragment()
            )

            //削除ボタン
            fragmentTransaction.replace(
                R.id.button_area,
                CharacterDataDeleteMenuFragment()
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }


    }
}