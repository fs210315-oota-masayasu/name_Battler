package com.example.namebattler.characters.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.characters.CharaListAdapter
import com.example.namebattler.characters.activity.NewCharacterGenerateActivity
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.databinding.FragmentCharacterListBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.viewModel.getViewModelFactory

//キャラクター一覧画面
class CharacterListFragment: Fragment() {
    private lateinit var binding: FragmentCharacterListBinding

    private val newCharacterCreateActivityRequestCode = 1

//    private val operationDatabaseViewModel: OperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }
    private lateinit var setOperationDatabaseViewModel: OperationDatabaseViewModel




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val operationDatabaseViewModel: OperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
//        binding = DataBindingUtil.inflate(inflater,R.layout.activity_character_list, container, false)
        binding = FragmentCharacterListBinding.inflate(inflater,container,false).apply {


            //Liveデータからの通知をうけてFragmentやViewを設定
            setOperationDatabaseViewModel.allCharacters.observe(viewLifecycleOwner, Observer {

                /** ヘッダー **/
                if (savedInstanceState == null) {
                    // FragmentManagerのインスタンス生成
                    val fragmentManager: FragmentManager = parentFragmentManager
                    // FragmentTransactionのインスタンスを取得
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                    //スコープ関数で処理を簡略化
                    setOperationDatabaseViewModel.apply {
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

                val adapter = CharaListAdapter(setOperationDatabaseViewModel, setCharacterViewModel, viewLifecycleOwner)





                binding.lifecycleOwner = viewLifecycleOwner
                binding.operationDatabaseViewModel = setOperationDatabaseViewModel
                binding.characterViewModel = setCharacterViewModel

                //adapterにlifecycleOwnerを渡す
                val recyclerViewOfCharacters = binding.listView
                recyclerViewOfCharacters.layoutManager = LinearLayoutManager(view?.context)
                recyclerViewOfCharacters.adapter = adapter



                //キャラクター作成画面へ遷移
                btnCharacterNewCreate.setOnClickListener {
                    val setCharacterNewCreate =
                        Intent(activity, NewCharacterGenerateActivity::class.java)
                    startActivityForResult(setCharacterNewCreate, newCharacterCreateActivityRequestCode)
                }

            })

        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)

        //            val binding: ActivityCharacterListBinding =
//                DataBindingUtil.inflate(, R.layout.activity_character_list)

//        operationDatabaseViewModel =
//            ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
//
//        characterViewModel =
//            ViewModelProvider(this).get(CharacterViewModel::class.java)


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


    }
}