package com.example.namebattler.data.battleData

import androidx.lifecycle.MutableLiveData
import com.example.namebattler.data.characterData.CharacterHolder

class InformationManager {

    //ユニークなオブジェクトを渡す
    fun getInstance(): InformationManager {
        return instance
    }


/*    //TODO テスト
    var testString : String = ""

    fun setString(text : String){
        testString = text
    }
    fun getString() :String{
        Log.d("tag", "text is [ $testString ]")
        return testString
    }*/

    var informationNotice = MutableLiveData<ArrayList<OutputInformationHolder>>()
    //var informationLiveData = MutableLiveData<InformationHolder>()

/*

    fun getInformationHolder(
        enemy: ArrayList<CharacterHolder>,
        party: ArrayList<CharacterHolder>
    ): InformationHolder {
        return initInformationHolder(enemy, party)
    }
*/


/*
    fun setNotice(informationHolder: InformationHolder) {
        informationLiveData.postValue(informationHolder)
    }
*/




    fun setInformationNotice(OutputInformationList: ArrayList<OutputInformationHolder>) {
        informationNotice.postValue(OutputInformationList)
    }

    private fun String.setOutputInformationHolder(holder: CharacterHolder): OutputInformationHolder {
        return OutputInformationHolder(
            holder.name,
            holder.hp.toString(),
            holder.hp.toString(),
            holder.mp.toString(),
            holder.mp.toString(),
            this
        )
    }

    fun getOutputInformationHolder(
        enemy: ArrayList<CharacterHolder>,
        party: ArrayList<CharacterHolder>
    ): ArrayList<OutputInformationHolder> {
        val firstEnemyHolder = "".setOutputInformationHolder(enemy[EnemyIndexEnum.FIRST_ENEMY.id])
        val secondEnemyHolder = "".setOutputInformationHolder(enemy[EnemyIndexEnum.SECOND_ENEMY.id])
        val thirdEnemyHolder = "".setOutputInformationHolder(enemy[EnemyIndexEnum.THIRD_ENEMY.id])

        val firstPartyHolder = "".setOutputInformationHolder(party[PartyIndexEnum.FIRST_PARTY.id])
        val secondPartyHolder = "".setOutputInformationHolder(party[PartyIndexEnum.SECOND_PARTY.id])
        val thirdPartyHolder = "".setOutputInformationHolder(party[PartyIndexEnum.THIRD_PARTY.id])

        return arrayListOf(
            firstEnemyHolder,
            secondEnemyHolder,
            thirdEnemyHolder,
            firstPartyHolder,
            secondPartyHolder,
            thirdPartyHolder
        )
    }


    fun initOutputInformationHolder(
        enemy: ArrayList<CharacterHolder>,
        party: ArrayList<CharacterHolder>
    )
            : ArrayList<OutputInformationHolder> {

        //val firstEnemyHolder: CharacterHolder = enemy[EnemyEnum.FIRST_ENEMY.id]
        //val secondEnemyHolder: CharacterHolder = enemy[EnemyEnum.SECOND_ENEMY.id]
        //val thirdEnemyHolder: CharacterHolder = enemy[EnemyEnum.THIRD_ENEMY.id]

//        val firstPartyHolder: CharacterHolder = party[PartyEnum.FIRST_PARTY.id]
//        val secondPartyHolder: CharacterHolder = party[PartyEnum.SECOND_PARTY.id]
//        val thirdPartyHolder: CharacterHolder = party[PartyEnum.THIRD_PARTY.id]


        val firstEnemyHolder = "".setOutputInformationHolder(enemy[EnemyIndexEnum.FIRST_ENEMY.id])
        val secondEnemyHolder = "".setOutputInformationHolder(enemy[EnemyIndexEnum.SECOND_ENEMY.id])
        val thirdEnemyHolder = "".setOutputInformationHolder(enemy[EnemyIndexEnum.THIRD_ENEMY.id])

        val firstPartyHolder = "".setOutputInformationHolder(party[PartyIndexEnum.FIRST_PARTY.id])
        val secondPartyHolder = "".setOutputInformationHolder(party[PartyIndexEnum.SECOND_PARTY.id])
        val thirdPartyHolder = "".setOutputInformationHolder(party[PartyIndexEnum.THIRD_PARTY.id])



        val setArrayList = arrayListOf(
            firstEnemyHolder,
            secondEnemyHolder,
            thirdEnemyHolder,
            firstPartyHolder,
            secondPartyHolder,
            thirdPartyHolder
        )
        informationNotice.postValue(setArrayList)

        return setArrayList
    }


//        val infoOfFirstEnemy =OutputInformationHolder(
//            firstEnemyHolder.name,
//            firstEnemyHolder.hp.toString(),
//            firstEnemyHolder.hp.toString(),
//            firstEnemyHolder.mp.toString(),
//            firstEnemyHolder.mp.toString(),
//            "")



/*

    private fun initInformationHolder(
        enemy: ArrayList<CharacterHolder>,
        party: ArrayList<CharacterHolder>
    ): InformationHolder {

        val firstEnemy: CharacterHolder = enemy[0]
        val secondEnemy: CharacterHolder = enemy[1]
        val thirdEnemy: CharacterHolder = enemy[2]

        val firstParty: CharacterHolder = party[0]
        val secondParty: CharacterHolder = party[1]
        val thirdParty: CharacterHolder = party[2]

        */
/*  Name :String,
        MaxHp :String,
        MaxMp :String,
        CurrentHp :String,
        CurrentMp :String,
        Cond :String,*//*


        return InformationHolder(
            firstEnemy.name,
            firstEnemy.hp.toString(),
            firstEnemy.mp.toString(),
            firstEnemy.hp.toString(),
            firstEnemy.mp.toString(),
            "",

            secondEnemy.name,
            secondEnemy.hp.toString(),
            secondEnemy.mp.toString(),
            secondEnemy.hp.toString(),
            secondEnemy.mp.toString(),
            "",

            thirdEnemy.name,
            thirdEnemy.hp.toString(),
            thirdEnemy.mp.toString(),
            thirdEnemy.hp.toString(),
            thirdEnemy.mp.toString(),
            "",

            firstParty.name,
            firstParty.hp.toString(),
            firstParty.mp.toString(),
            firstParty.hp.toString(),
            firstParty.mp.toString(),
            "",

            secondParty.name,
            secondParty.hp.toString(),
            secondParty.mp.toString(),
            secondParty.hp.toString(),
            secondParty.mp.toString(),
            "",

            thirdParty.name,
            thirdParty.hp.toString(),
            thirdParty.mp.toString(),
            thirdParty.hp.toString(),
            thirdParty.mp.toString(),
            ""
        )

    }
*/

    //インスタンス生成
    companion object {
        val instance = InformationManager()
    }

    enum class EnemyIndexEnum(val id: Int) {
        FIRST_ENEMY(0),
        SECOND_ENEMY(1),
        THIRD_ENEMY(2),
    }

    enum class PartyIndexEnum(val id: Int) {
        FIRST_PARTY(0),
        SECOND_PARTY(1),
        THIRD_PARTY(2)
    }


}