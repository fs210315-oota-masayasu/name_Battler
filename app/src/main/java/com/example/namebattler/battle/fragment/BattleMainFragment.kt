package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.database.Characters
import com.example.namebattler.databinding.BattleMainBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.util.BackStack
import com.example.namebattler.viewModel.BattleViewModel
import com.example.namebattler.viewModel.EnemyViewModel
import com.example.namebattler.viewModel.PartyFormationViewModel
import com.example.namebattler.viewModel.getViewModelFactory

//バトルメイン画面
class BattleMainFragment: Fragment() {

    private lateinit var binding: BattleMainBinding

    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory()  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BattleMainBinding.inflate(inflater, container, false).apply {

//            val pList = partyFormationViewModel.selectionCharacterList.value ?: mutableListOf()
//            val eList :MutableList<Characters> = (enemyViewModel.enemyFormation.value ?: listOf(Characters())) as MutableList<Characters>


            /** ヘッダー **/
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = parentFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.header_area,
                HeaderFragment()
            )
            fragmentTransaction.commit()



            /** ステータス情報「status_information」**/
            if (savedInstanceState == null) {
                // FragmentManagerのインスタンス生成
                val statusInformationFragmentManager: FragmentManager = parentFragmentManager

                //LiveData（informationNotice）に変化があったときにFragmentが再生成される
                battleViewModel.informationNotice.observe(viewLifecycleOwner, {

//                    it.forEach {
//                        Log.d("[INFO]","[INFO] name:${it.name} belong:${it.belong} ")
//                    }
//



                    // FragmentTransactionのインスタンスを取得
                    val statusInformationFragmentTransaction: FragmentTransaction = statusInformationFragmentManager.beginTransaction()
                    statusInformationFragmentTransaction.replace(
                        R.id.status_information_area,
                        StatusInformationFragment())
                    // 張り付けを実行
                    statusInformationFragmentTransaction.commit()
                })
            }
            /** バトル操作「battle_operation」**/
            if  (savedInstanceState == null){
                val battleOperationFragmentManager: FragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                val battleOperationFragmentTransaction: FragmentTransaction = battleOperationFragmentManager.beginTransaction()
                //ステータスの初期情報をFragmentへ送る
                battleOperationFragmentTransaction.replace(R.id.battle_operation_area, BattleOperationFragment())

                // 張り付けを実行
                battleOperationFragmentTransaction.commit()
            }



        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}