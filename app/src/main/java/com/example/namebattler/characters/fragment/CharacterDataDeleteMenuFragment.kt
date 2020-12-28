package com.example.namebattler.characters.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.characters.activity.CharacterListActivity
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.database.DateConverter
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.message.AlertDataDelete
import kotlinx.android.synthetic.main.fragment_processing_data_deletion.*


private const val ARG_PARAM_NAME = "param_Name"
private const val ARG_PARAM_CURRENT_AT = "param_current_at"

//キャラクター詳細画面のボタンエリア
class ProcessingDataDeletionFragment : Fragment(), AlertDataDelete.NoticeDialogListener {

    private var paramName : String? = null
    private var paramCurrentAt : String? = null
    private var paramLongToCurrentAt :Long? = null
    private lateinit var characters : Characters
    
    companion object {
        @JvmStatic
        fun newInstance(paramCharacter: CharacterHolder) =
            ProcessingDataDeletionFragment().apply {
                paramName = paramCharacter.name
                paramCurrentAt = DateConverter()
                    .convertLongToString(paramCharacter.currentDate)

                paramLongToCurrentAt = paramCharacter.currentDate

                characters = Characters(
                    paramCharacter.name,
                    JobManager().getJobList(paramCharacter.job),
                    paramCharacter.hp,
                    paramCharacter.mp
                    ,
                    paramCharacter.str,
                    paramCharacter.def,
                    paramCharacter.agi,
                    paramCharacter.luck,
                    paramCharacter.currentDate
                )
                arguments = Bundle().apply {
                    putString(ARG_PARAM_NAME, paramName)
                    putString(ARG_PARAM_CURRENT_AT, paramCurrentAt)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


            paramName = it.getString(ARG_PARAM_NAME)
            paramCurrentAt = it.getString(ARG_PARAM_CURRENT_AT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_processing_data_deletion, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val createAt : TextView =  view.findViewById(R.id.value_status_create_at)
        val args : Bundle? = arguments

        val setViewValueCreateAt = args?.getString(ARG_PARAM_CURRENT_AT)

        createAt.text = setViewValueCreateAt


        btn_delete_character_data.setOnClickListener{
            val dialog = AlertDataDelete()

            //childFragmentManagerを利用してDialogFragmentを自分の子として追加する
            dialog.show(childFragmentManager, "NoticeDialogFragment")
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        delete()

        val intentOfDel = Intent(activity,
            CharacterListActivity::class.java)
        startActivity(intentOfDel)

    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        println("NoticeDialogでCancelボタンが押されたよ！")
    }


    //キャラクター削除
    private fun delete(){
        val mainViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
        mainViewModel.delete(characters)
    }


}
