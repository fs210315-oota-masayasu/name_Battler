package com.example.namebattler.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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


//    var partyInformationList :ArrayList<CharacterHolder> = arrayListOf()
//    var enemyInformationList :ArrayList<CharacterHolder> = arrayListOf()


    var informationNotice = MutableLiveData<ArrayList<CharacterInformationHolder>>()


    var currentEnemyList = arrayListOf<CharacterInformationHolder>()
    var currentPlayerList = arrayListOf<CharacterInformationHolder>()
    fun toSplitCurrentList(){
        currentEnemyList = (informationNotice.value?.filter { it.belong == Belong.ENEMY.name })as ArrayList<CharacterInformationHolder>
        currentPlayerList = (informationNotice.value?.filter { it.belong == Belong.PLAYER.name })as ArrayList<CharacterInformationHolder>
    }



    //ターンごとの戦闘結果を反映させる
    fun setInformationNotice(characterInformationList: ArrayList<CharacterInformationHolder>) {
        informationNotice.postValue(characterInformationList)
    }

    //戦闘開始時にデータを格納する
    fun setInformationNotice() {
        enemyStatusList = InformationManager().initOutputInformationList(sendFromLobbyToMainEnemyList)
        playerStatusList = InformationManager().initOutputInformationList(sendFromLobbyToMainPartyList)

        val initInformation = InformationManager().getOutputInformationList(enemyStatusList, playerStatusList)

        informationNotice.postValue(initInformation)
    }

    //ラジオボタンで選択した作戦を格納
    var operationRadioType = MutableLiveData(OperationIdEnum.OFFENSIVE)


}