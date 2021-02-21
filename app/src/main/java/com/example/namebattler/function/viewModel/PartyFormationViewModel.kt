package com.example.namebattler.function.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.database.characterDatabase.Characters
import com.example.namebattler.function.job.JobParameterProduction
import com.example.namebattler.function.EntryRule

class PartyFormationViewModel: ViewModel(){

    /** パーティ構成画面 **/

    //OperationDatabaseViewModelからallCharacterを受け取って格納
    var allCharacterList:   MutableList<Characters> = mutableListOf()

    //編成済みキャラクターリスト（バトル開始画面へ送る）
    var selectionCharacterList: MutableLiveData<MutableList<Characters>> = MutableLiveData()

    //初期化
    fun initPartyFormationData(){
        addCharacterList = mutableListOf()
        isClickDecideParty = MutableLiveData()
        buttonCounter = MutableLiveData()
        isClickDecideParty = MutableLiveData()
        btnTextDecideParty = MutableLiveData<String>().apply {
            value = "3名選択してください"
        }
    }
    //選択するキャラクターデータの候補リスト
    var addCharacterList : MutableList<Characters> = mutableListOf()
    //パーティ編成画面のチェック状態（OperationDatabaseViewModelから値を受け取って来る）
    var isCheckedPartyFormation: MutableList<MutableLiveData<Boolean>> = mutableListOf()
    //ボタンテキスト
    var btnTextDecideParty = MutableLiveData<String>().apply {
        value = "3名選択してください"
    }
    //チェックした数を保持
    var buttonCounter : MutableLiveData<Int> = MutableLiveData()
    //チェック数が規定数に達しているか判定
    var isClickDecideParty : MutableLiveData<Boolean> = MutableLiveData()

    //    リスト（チェックボックス以外も含む）をタップした際にチェックがついたり外れたりする処理
    fun onClickedCheckbox(position: Int) {
        //タッチしたチェックボックスの状態を取得
        var isChecked = this.isCheckedPartyFormation[position].value ?: false
        //チェックしたキャラクターデータを抽出
        var clickedCharacter = allCharacterList[position]
        //チェック状態を反転してキャラクターデータを候補リストへ格納・除外する
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

        //チェック状態を更新
        this.isCheckedPartyFormation[position].value = isChecked

        //候補リストに格納されたキャラクターデータが規定数に達したか判定し状態をボタンテキストへ反映
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
        //チェック数を更新
        buttonCounter.postValue(addCharacterList.size)

        //編成済みキャラクターリストを一旦初期化してから候補を格納
        selectionCharacterList.value = mutableListOf()
        selectionCharacterList.postValue(addCharacterList)

    }

    fun onClickDecideParty() {
        //チェック数を取得
        var inn = buttonCounter.value

        //チェック数が規定（デフォは3）に達していたらtrueにする
        if (inn == 3){
            isClickDecideParty.postValue(true)
        }
    }

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobParameterProduction().getJobList(index)
    }
}