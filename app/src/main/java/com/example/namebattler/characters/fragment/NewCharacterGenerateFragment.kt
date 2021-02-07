package com.example.namebattler.characters.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.databinding.CharacterNewCreateBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.message.AlertDataDelete
import com.example.namebattler.message.AlertDataUpdate
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.util.JobEnum
import com.example.namebattler.viewModel.*

//キャラクター作成画面
class NewCharacterGenerateFragment: Fragment(), TextWatcher , AlertDataUpdate.NoticeDialogListener{
    private lateinit var binding: CharacterNewCreateBinding

    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    private lateinit var setOperationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CharacterNewCreateBinding.inflate(inflater, container, false).apply {
            operationDatabaseViewModel = setOperationDatabaseViewModel
            characterViewModel = setCharacterViewModel


            setCharacterViewModel.bindEditText.postValue("")



            //ヘッダー
            if (savedInstanceState == null) {
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

//            setCharacterViewModel.selectJob = JobEnum.NONE

            setCharacterViewModel.bindEditText.observe(viewLifecycleOwner, Observer {
                setCharacterViewModel.inputCharacterName = it
            })





            //名前
            //TextWatcherによって名前入力中文字列を取得する
            setInputName.addTextChangedListener(this@NewCharacterGenerateFragment)


            //キャラクター生成 + 画面遷移
            btnOrderCharacterCreation.setOnClickListener {


                //名前入力フォームとジョブ選択のラジオボタンの入力がされているときにキャラクタ作成と画面遷移を行う
                if (validationCheck(setInputName.text.toString()).isBlank() && validationCheck(setCharacterViewModel.selectJob).isBlank()){
                    val generateCharacter =
                        setCharacterViewModel.generateCharacter(setInputName.text.toString(), setCharacterViewModel.selectJob)

                    Log.d("++ generateCharacter ++", "Name ${generateCharacter.NAME}")
                    Log.d("++ generateCharacter ++", "Job ${generateCharacter.JOB}")
                    Log.d("++ generateCharacter ++", "Str ${generateCharacter.STR}")
                    Log.d("++ generateCharacter ++", "HP ${generateCharacter.HP}")



                    setOperationDatabaseViewModel.confirm(setInputName.text.toString())

                    setOperationDatabaseViewModel.countOverlap.observe(viewLifecycleOwner, Observer {
                        countOverlap ->

                        setOperationDatabaseViewModel.apply {
                            Log.d("***きゃらさく***", "きゃらさく:${countOverlap}")

                            if (countOverlap == 1){
                                val dialog = AlertDataUpdate()

                                //childFragmentManagerを利用してDialogFragmentを自分の子として追加する
                                dialog.show(childFragmentManager, "NoticeDialogFragment")
                            }else{
                                setCharacterViewModel.isInsertCharacter = true

                                //ヘッダー情報更新
                                Log.d("<<< string >>>", "ヘッダーテキスト：${getString(R.string.create_character)}")
                                headerViewModel.headerText.postValue(getString(R.string.create_character))
                                headerViewModel.outputFlag = HeaderFlag.DEFAULT

                                //画面遷移
                                val fragmentManager: FragmentManager = parentFragmentManager
                                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                                fragmentTransaction.addToBackStack(BackStack.NEW_CHARACTER_GENERATE.name)


                                fragmentTransaction.replace(
                                    R.id.attach_screen,
                                    ConfirmGenerationCharacterFragment()
                                )

                                fragmentTransaction.commit()
                            }

                        }

                    })






                }else{
                    //名前入力フォームかジョブ選択のラジオボタンのどちらかが未入力の場合ダイアログボックスを出力させる
                    AlertDialog.Builder(activity)
                        .setTitle("警告")
                        .setMessage("${validationCheck(setInputName.text.toString())}\n${validationCheck(setCharacterViewModel.selectJob)}")
                        .setPositiveButton("YES") { dialog, id ->
                            println("dialog: $dialog which: $id")
                        }
                        .show()
                }
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOperationDatabaseViewModel =
            ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)


    }


    //テキストが変更された直後（入力が確定された後）
    override fun afterTextChanged(s: Editable?) {
        val inputStr = s.toString()

        if (inputStr.length > 20) {
            //名前
//            val inputName = findViewById<EditText>(R.id.set_input_name)
            val inputName = binding.setInputName

            inputName.error = "20文字以内で入力してください。"
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {

        //ヘッダー情報更新
        Log.d("<<< string >>>", "ヘッダーテキスト：${getString(R.string.create_character)}")
        headerViewModel.headerText.postValue(getString(R.string.create_character))
        headerViewModel.outputFlag = HeaderFlag.DEFAULT


        //画面遷移
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.addToBackStack(BackStack.NEW_CHARACTER_GENERATE.name)


        fragmentTransaction.replace(
            R.id.attach_screen,
            ConfirmGenerationCharacterFragment()
        )

        fragmentTransaction.commit()

    }
    override fun onDialogNegativeClick(dialog: DialogFragment) {
        val inputName = binding.setInputName
        inputName.error = "名前を変更してください"

    }


    //バリデーションチェック（ジョブ選択のラジオボタン）
    private fun validationCheck(jobEnum: JobEnum?): String {
        return if (jobEnum == null || jobEnum == JobEnum.NONE){
            "ジョブを選択してください"
        }else{
            ""
        }


//        when {


//            inputName != null && inputName != "" && jobEnum != null && jobEnum != JobEnum.NONE -> {
//
//                return null
//            }
//
//            inputName.isNullOrEmpty() && jobEnum == null || jobEnum == JobEnum.NONE -> {
//
//                return "名前を入力してください　ジョブを選択してください"
//            }
//
//            !inputName.isNullOrEmpty() && jobEnum == null || jobEnum == JobEnum.NONE -> {
//
//                return  "ジョブを選択してください(名前のみ入力) $inputName"
//            }
//
//            inputName.isNullOrEmpty() && jobEnum != null && jobEnum != JobEnum.NONE -> {
//                return "名前を入力してください（ジョブのみ入力）"
//            }
//            else ->{
//                return  null
//            }
//        }
    }

    //バリデーションチェック（名前入力フォーム）
    private fun validationCheck(inputName: String?): String {
        return if (inputName.isNullOrBlank()){
            "名前を入力してください"
        }else{
            ""
        }

/*        when {
            inputName != null && inputName != "" && jobEnum != null && jobEnum != JobEnum.NONE -> {

                return null
            }

            inputName.isNullOrEmpty() && jobEnum == null || jobEnum == JobEnum.NONE -> {

                return "名前を入力してください　ジョブを選択してください"
            }

            !inputName.isNullOrEmpty() && jobEnum == null || jobEnum == JobEnum.NONE -> {

                return  "ジョブを選択してください(名前のみ入力) $inputName"
            }

            inputName.isNullOrEmpty() && jobEnum != null && jobEnum != JobEnum.NONE -> {
                return "名前を入力してください（ジョブのみ入力）"
            }
            else ->{
                return  null
            }
        }*/
    }


}


/*    private fun validationCheck(inputName : String?, jobEnum: JobEnum?){
        when{
            inputName != null && inputName != "" && jobEnum != null && jobEnum != JobEnum.NONE ->{

            }

            inputName != null && inputName != "" && jobEnum == null || jobEnum == JobEnum.NONE -> {
                AlertDialog.Builder(activity)
                    .setTitle("注意")
                    .setMessage("ジョブを選択してください(名前のみ入力)")
                    .setPositiveButton("YES") { dialog, id ->
                        println("dialog: $dialog which: $id")
                    }
                    .show()
            }

            inputName == null || inputName == "" && jobEnum != null && jobEnum != JobEnum.NONE ->{
                    AlertDialog.Builder(activity)
                        .setTitle("注意")
                        .setMessage("名前を入力してください（ジョブのみ入力）")
                        .setPositiveButton("YES"){dialog, id ->
                            println("dialog: $dialog which: $id")
                        }
                        .show()
            }

            else ->{
                AlertDialog.Builder(activity)
                    .setTitle("注意")
                    .setMessage("名前を入力してください　ジョブを選択してください")
                    .setPositiveButton("YES"){dialog, id ->
                        println("dialog: $dialog which: $id")
                    }
                    .show()
            }
        }
    }*/

