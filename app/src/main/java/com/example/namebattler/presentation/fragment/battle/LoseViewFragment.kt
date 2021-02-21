package com.example.namebattler.presentation.fragment.battle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.namebattler.presentation.HomeActivity
import com.example.namebattler.R
import com.example.namebattler.databinding.FragmentLoseViewBinding
import com.example.namebattler.presentation.fragment.party.PartyFormationFragment
import com.example.namebattler.function.HeaderFlag
import com.example.namebattler.function.viewModel.BattleViewModel
import com.example.namebattler.function.viewModel.HeaderViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

class LoseViewFragment : Fragment() {

    private lateinit var binding: FragmentLoseViewBinding
    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoseViewBinding.inflate(inflater, container, false).apply {

            //再挑戦
            challengeAgain.setOnClickListener {

                //ヘッダー情報をセット
                headerViewModel.apply {
                    headerText.postValue(getString(R.string.battle_main))
                    outputFlag = HeaderFlag.BATTLE_MAIN
                }

                /** ステータスの初期情報の取得とLiveData（informationNotice）への格納 **/
                battleViewModel.setInformationNotice()

                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction.replace(
                    R.id.attach_screen,
                    BattleMainFragment()
                )
                fragmentTransaction.commit()
            }

            //次の対戦へ
            nextBattle.setOnClickListener {

                //ヘッダー情報をセット
                headerViewModel.apply {
                    headerText.postValue(getString(R.string.party_formation))
                    outputFlag = HeaderFlag.DEFAULT
                }


                if (savedInstanceState == null){
                    // FragmentManagerのインスタンス生成
                    val fragmentManager: FragmentManager = parentFragmentManager
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

            //対戦を終了する
            endBattle.setOnClickListener {
                val setIntentEndBattle = Intent(activity, HomeActivity::class.java)
                setIntentEndBattle.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(setIntentEndBattle)
            }
        }
        return binding.root
    }

}
