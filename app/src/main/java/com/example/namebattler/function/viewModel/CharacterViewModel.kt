package com.example.namebattler.function.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.function.characterDataDefinition.Player
import com.example.namebattler.database.characterDatabase.Characters
import com.example.namebattler.function.characterDataDefinition.DateConverter
import com.example.namebattler.function.job.JobParameterProduction
import com.example.namebattler.function.JobEnum

class CharacterViewModel: ViewModel() {

    var isInsertCharacter: Boolean = false

    //CREATE_ATをLong型から日付（String型）へ変換する
    fun toStringCreateAt(date: Long) :String{
        return DateConverter().convertLongToString(date)
    }

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobParameterProduction().getJobList(index)
    }

    //ヘッダーテキスト
    var headerText = MutableLiveData<String>()

    //詳細画面などで表示するキャラクター情報
    var characterStatus = MutableLiveData<Characters>()


    /** キャラクタ-作成画面 **/

    //ラジオボタンを選択したときに格納される
    var inputCharacterName: String = ""
    var selectJob: JobEnum = JobEnum.NONE

    //名前の入力情報をバインドする
    var bindEditText: MutableLiveData<String> = MutableLiveData()

    //入力情報（名前、ジョブ）をクリアする
    fun clearInputData(){
        inputCharacterName = ""
        selectJob = JobEnum.NONE
    }

    fun generateCharacter(inputName: String, jobEnum : JobEnum){
        val character = Player(inputName, JobParameterProduction().getJobList(jobEnum.jobName)).getParam()?: Characters()
        characterStatus.postValue(character)
    }
}