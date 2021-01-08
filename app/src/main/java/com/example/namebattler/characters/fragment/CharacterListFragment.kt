package com.example.namebattler.characters.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.characters.CharaListAdapter
import com.example.namebattler.characters.activity.ConfirmCharacterStatusActivity
import com.example.namebattler.characters.activity.NewCharacterGenerateActivity
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.databinding.ActivityCharacterListBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import kotlinx.android.synthetic.main.activity_character_list.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

//キャラクター一覧画面
class CharacterListFragment(private var operationDatabaseViewModel: OperationDatabaseViewModel,
                            private var characterViewModel: CharacterViewModel): Fragment() {

    private val newCharacterCreateActivityRequestCode = 1


    private lateinit var binding: ActivityCharacterListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_character_list, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        operationDatabaseViewModel =
//            ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
//
//        characterViewModel =
//            ViewModelProvider(this).get(CharacterViewModel::class.java)

        //Liveデータからの通知をうけてFragmentやViewを設定
        operationDatabaseViewModel.allCharacters.observe(this, Observer {

            /** ヘッダー **/
            if (savedInstanceState == null) {
                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                //スコープ関数で処理を簡略化
                operationDatabaseViewModel.apply {
                    //LiveDataの通知を受け取って処理を実行
                    numOfRegistrations.observe(viewLifecycleOwner, Observer {

                        val setText = "キャラ一覧 ( $it )"

                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(
                            R.id.header_area,
                            HeaderFragment.newInstance(
                                setText
                            )
                        )
                        // 張り付けを実行
                        fragmentTransaction.commit()

                    })
                    //ViewModelの件数取得処理を実行（numOfRegistrationsに格納される）
                    confirmNumOfRegistrations()
                }
            }

            /** RecyclerView **/

            val adapter = CharaListAdapter(operationDatabaseViewModel, characterViewModel, this)


//            val binding: ActivityCharacterListBinding =
//                DataBindingUtil.inflate(, R.layout.activity_character_list)

            binding.lifecycleOwner = this
            binding.viewModel = operationDatabaseViewModel
            binding.characterViewModel = characterViewModel

            //adapterにlifecycleOwnerを渡す
            val recyclerViewOfCharacters = binding.listView
            recyclerViewOfCharacters.layoutManager = LinearLayoutManager(view?.context)
            recyclerViewOfCharacters.adapter = adapter

            // RecyclerViewのクリックイベント（Adapter内のインターフェース実装）
//            adapter.setOnItemClickListener(object :
//                CharaListAdapter.OnItemClickListener {
//                override fun onItemClickListener(
//                    view: View,
//                    position: Int,
//                    sendToData: CharacterHolder?
//                ) {
//                    Log.d("***tag***", """CCCClick!!!""")
//                    characterViewModel.characterStatus.observe(viewLifecycleOwner, Observer {
//                        Log.d("***tag***", """this : ${it.NAME}""")
//                    })

//                    val fragmentManager: FragmentManager = parentFragmentManager
//                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//                    fragmentTransaction.replace(
//                        R.id.attach_screen,
//                        ConfirmCharacterStatusFragment()
//                    )
//                    fragmentTransaction.commit()



//                    val intent = Intent(view.context, ConfirmCharacterStatusActivity::class.java)
//                    intent.putExtra(CharacterHolder.EXTRA_DATA, sendToData)
//                    startActivity(intent)

//                }
//            })

            //キャラクター作成画面へ遷移
            btn_character_new_create.setOnClickListener {
                val setCharacterNewCreate =
                    Intent(activity, NewCharacterGenerateActivity::class.java)
                startActivityForResult(setCharacterNewCreate, newCharacterCreateActivityRequestCode)
            }

        })
    }
}