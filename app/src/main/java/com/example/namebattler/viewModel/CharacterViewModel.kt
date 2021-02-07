package com.example.namebattler.viewModel

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.namebattler.characters.CharactersRepository
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.AppDatabase
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.util.Belong
import com.example.namebattler.util.EntryRule
import kotlin.concurrent.thread

import com.example.namebattler.R
import com.example.namebattler.data.characterData.Player
import com.example.namebattler.data.database.DateConverter
import com.example.namebattler.util.JobEnum
import com.example.namebattler.util.RadioType
import java.lang.NullPointerException

class CharacterViewModel: ViewModel() {

    var isInsertCharacter: Boolean = false

    //CREATE_ATをLong型から日付（String型）へ変換する
    fun toStringCreateAt(date: Long) :String{
        return DateConverter().convertLongToString(date)
    }

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobManager().getJobList(index)
    }

    //ConfirmCharacterStatusActivityへ遷移する際に渡すデータ
    fun onrClickListData(currentCharacterData: Characters): CharacterHolder {
        return CharacterHolder(
            Belong.PLAYER.name,
            currentCharacterData.NAME,
            JobManager().getJobList(currentCharacterData.JOB),
            currentCharacterData.HP,
            currentCharacterData.MP,
            currentCharacterData.STR,
            currentCharacterData.DEF,
            currentCharacterData.AGI,
            currentCharacterData.LUCK,
            currentCharacterData.CREATE_AT
        )
    }

    //ヘッダーテキスト
    var headerText = MutableLiveData<String>()

    //詳細画面などで表示するキャラクター情報
    var characterStatus = MutableLiveData<Characters>()

    /** キャラクター詳細画面 **/
    //キャラクタ一覧画面でタップしたキャラクターを格納する
    fun setCharacterStatus(position : Int){
        characterStatus.postValue(Characters())
    }

    /** キャラクタ-作成画面 **/

    //ラジオボタンを選択したときに格納される
//    var inputCharacterName: MutableLiveData<String> = MutableLiveData()
//    var textViewName : MutableLiveData<String> = MutableLiveData("")
    var inputCharacterName: String = ""
    var selectJob: JobEnum = JobEnum.NONE

//    fun initInputCharacterName(){
//        inputCharacterName = MutableLiveData()
//    }

    var bindEditText: MutableLiveData<String> = MutableLiveData()

    fun clearInputData(){
        inputCharacterName = ""
        selectJob = JobEnum.NONE
//        textViewName.postValue("")
    }




    fun generateCharacter(inputName: String, jobEnum : JobEnum): Characters{
//        val job = JobManager().getJobList(selectJob.value!!.jobName)
//        return Player(inputCharacterName.value?:"Error Name", job).getParam()?: Characters()
        val character = Player(inputName, JobManager().getJobList(jobEnum.jobName)).getParam()?: Characters()

        characterStatus.postValue(character)

        return character
    }



}

/*
class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>
    val numOfRegistrations = MutableLiveData<Int>()


    init {
        //Daoのインスタンスを取得してRepositoryへ格納
        val charactersDao = AppDatabase.getInstance(application, viewModelScope).charactersDao()
        repository = CharactersRepository(charactersDao)
        allCharacters = repository.allCharacters

//        isCheckedCheckBox.value = MutableList(repository.allCharacters.value?.size ?:0){false}
    }

    //登録件数取得
    private fun numOfRegistrations(): Int {
        return repository.numOfRegistrations()
    }

    fun confirmNumOfRegistrations() {
        //非同期処理でMutableLiveDataに登録件数を格納
        thread {
            numOfRegistrations.postValue(numOfRegistrations())
        }
    }

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobManager().getJobList(index)
    }

    //ConfirmCharacterStatusActivityへ遷移する際に渡すデータ
    fun onrClickListData(currentCharacterData: Characters): CharacterHolder {
        return CharacterHolder(
            Belong.PLAYER.name,
            currentCharacterData.NAME,
            JobManager().getJobList(currentCharacterData.JOB),
            currentCharacterData.HP,
            currentCharacterData.MP,
            currentCharacterData.STR,
            currentCharacterData.DEF,
            currentCharacterData.AGI,
            currentCharacterData.LUCK,
            currentCharacterData.CREATE_AT
        )
    }

    //ヘッダーテキスト
    var headerText = MutableLiveData<String>()

    */
/** キャラクター詳細画面 **//*

    //詳細画面で表示するキャラクター情報
    var characterStatus = MutableLiveData<Characters>()

    //キャラクタ一覧画面でタップしたキャラクターを格納する
    fun setCharacterStatus(position : Int){
        characterStatus.postValue(allCharacters.value?.get(position))
    }


    //TODO 後で別のViewModelへ移動させる 「BattleViewModel」とか
    */
/** パーティ構成画面 **//*


    var selectionCharacterList: MutableLiveData<MutableList<Characters>> = MutableLiveData()


    val addCharacterList : MutableList<Characters> = mutableListOf()


    //パーティ編成画面のチェック状態
    var isCheckedPartyFormation: MutableList<MutableLiveData<Boolean>> = mutableListOf()
    fun initCheckList() {
        isCheckedPartyFormation =
            MutableList(numOfRegistrations.value ?: 0) { MutableLiveData(false) }
    }

    var btnTextDecideParty = MutableLiveData<String>().apply {
        value = "3名選択してください"
    }


    //    リスト（チェックボックス以外も含む）をタップした際にチェックがついたり外れたりする処理
    fun onClickedCheckbox(position: Int) {


        var isChecked = this.isCheckedPartyFormation[position].value ?: false

        var clickedCharacter = allCharacters.value?.get(position) ?: Characters()

        when (isChecked) {
            false -> {
                isChecked = true
                addCharacterList.add(clickedCharacter)
            }
            true -> {
                isChecked = false
                addCharacterList.remove(clickedCharacter)
            }
        }

        this.isCheckedPartyFormation[position].value = isChecked
        when {
            addCharacterList.size > 0 && addCharacterList.size < EntryRule.MEMBER.num-> {
                btnTextDecideParty.postValue("このパーティで開始${addCharacterList.size}/${EntryRule.MEMBER.num}")

            }
            addCharacterList.size == EntryRule.MEMBER.num ->{
                btnTextDecideParty.postValue("戦闘開始")

            }
            addCharacterList.size > EntryRule.MEMBER.num ->{
                btnTextDecideParty.postValue("${EntryRule.MEMBER.num}名選択してください:${addCharacterList.size}/${EntryRule.MEMBER.num}")
            }
            else -> {
                btnTextDecideParty.postValue("${EntryRule.MEMBER.num}名選択してください")
            }
        }

        Log.d("tag", "[onClickedCheckbox] size is ${addCharacterList.size}")
        buttonCounter.postValue(addCharacterList.size)

        selectionCharacterList.value = mutableListOf()
        selectionCharacterList.postValue(addCharacterList)




    }

    fun onClickDecideParty() {
        var inn = buttonCounter.value

        Log.d("ttt Tag ttt", "check Start")
        Log.d("ttt Tag ttt", "...........")

        if (inn == 3){
            Log.d("ttt Tag ttt", "check Ok!")
            isClickDecideParty.postValue(true)
        }


    }

    var buttonCounter : MutableLiveData<Int> = MutableLiveData()

    var isClickDecideParty : MutableLiveData<Boolean> = MutableLiveData()


    fun setColor(context: Context): Int{
        return ContextCompat.getColor(context, R.color.buttonColor)
    }





}
*/
