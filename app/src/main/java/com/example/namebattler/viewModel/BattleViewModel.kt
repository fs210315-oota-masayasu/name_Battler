package com.example.namebattler.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.util.Belong
import com.example.namebattler.util.OperationIdEnum

class BattleViewModel: ViewModel() {

    //BattleLobbyFragmentで選択したパーティ・エネミーの編成データを格納
    var sendFromLobbyToMainPartyList :ArrayList<CharacterHolder> = arrayListOf()
    var sendFromLobbyToMainEnemyList :ArrayList<CharacterHolder> = arrayListOf()


    var enemyStatusList: ArrayList<CharacterInformationHolder> = arrayListOf()
    var playerStatusList: ArrayList<CharacterInformationHolder> = arrayListOf()

    //バトルに参加する敵味方すべてのステータス情報
    var informationNotice = MutableLiveData<ArrayList<CharacterInformationHolder>>()


    //BattleManagerへ渡す戦闘処理用のステータス情報
    var currentEnemyList = arrayListOf<CharacterInformationHolder>()
    var currentPlayerList = arrayListOf<CharacterInformationHolder>()

    //informationNotice内のデータを敵と味方に分割
    fun toSplitCurrentList(){
        currentEnemyList = (informationNotice.value?.filter { it.belong == Belong.ENEMY.name })as ArrayList<CharacterInformationHolder>
        currentPlayerList = (informationNotice.value?.filter { it.belong == Belong.PLAYER.name })as ArrayList<CharacterInformationHolder>
    }

    //ターンごとの戦闘結果を反映させる
    fun setInformationNotice(characterInformationList: ArrayList<CharacterInformationHolder>) {
        informationNotice.postValue(characterInformationList)
    }

    //バトル開始時にデータを格納する
    fun setInformationNotice() {
        //敵味方のステータス情報を戦闘処理用に変換
        enemyStatusList = InformationManager().initOutputInformationList(sendFromLobbyToMainEnemyList)
        playerStatusList = InformationManager().initOutputInformationList(sendFromLobbyToMainPartyList)

        //敵味方の戦闘処理データを一つに纏めてinformationNoticeへ格納
        val initInformation = InformationManager().getOutputInformationList(enemyStatusList, playerStatusList)
        informationNotice.postValue(initInformation)
    }

    //ラジオボタンで選択した作戦を格納
    var operationRadioType = MutableLiveData(OperationIdEnum.OFFENSIVE)


}