package com.example.namebattler.data.battleData

import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.util.Belong
import com.example.namebattler.util.TotalIndexEnum

class InformationManager {

    /** Characters -> CharacterHolder へ変換**/
    private fun String.convertCharacterHolder(characters : Characters): CharacterHolder {
        return CharacterHolder(
//            Belong.PLAYER.name, //所属
            this,
            characters.NAME,
            JobManager().getJobList(characters.JOB),
            characters.HP,
            characters.MP,
            characters.STR,
            characters.DEF,
            characters.AGI,
            characters.LUCK,
            characters.CREATE_AT
        )
    }

    fun setCharacterHolderList(belong: Belong, characterList: MutableList<Characters>):ArrayList<CharacterHolder>{

        val list  = arrayListOf<CharacterHolder>()
        characterList.forEach {
            list.add(belong.name.convertCharacterHolder(it))
        }
        return list

    }
    //キャラクターデータをバトル処理用のデータへ変換
    private fun Int.setOutputInformationHolder(holder: CharacterHolder, condition :MutableMap<String,Int>): CharacterInformationHolder {
        return CharacterInformationHolder(
            this,
            holder.belong,
            holder.name,
            holder.job,
            holder.hp,
            holder.hp,
            holder.mp,
            holder.mp,
            holder.str,
            0,
            holder.def,
            0,
            holder.agi,
            0,
            holder.luck,
            0,
            condition
        )
    }

    //バトル処理用データのIDを割り振りなおす
    private fun Int.setOutputInformationHolder(holder: CharacterInformationHolder?): CharacterInformationHolder {
        return CharacterInformationHolder(
            this,
            holder!!.belong,
            holder.name,
            holder.job,
            holder.maxHp,
            holder.currentHp,
            holder.maxMp,
            holder.currentMp,
            holder.str,
            holder.effectTimeOfStr,
            holder.def,
            holder.effectTimeOfDef,
            holder.agi,
            holder.effectTimeOfAgi,
            holder.luck,
            holder.effectTimeOfLuck,
            holder.cond
        )
    }

    //敵味方に分割したデータを結合する
    fun getOutputInformationList(
        enemy: ArrayList<CharacterInformationHolder>,
        player: ArrayList<CharacterInformationHolder>
    ): ArrayList<CharacterInformationHolder> {
        val firstEnemyHolder = TotalIndexEnum.FIRST_ENEMY.id.setOutputInformationHolder(enemy[0])
        val secondEnemyHolder = TotalIndexEnum.SECOND_ENEMY.id.setOutputInformationHolder(enemy[1])
        val thirdEnemyHolder = TotalIndexEnum.THIRD_ENEMY.id.setOutputInformationHolder(enemy[2])

        val firstPlayerHolder = TotalIndexEnum.FIRST_PLAYER.id.setOutputInformationHolder(player[0])
        val secondPlayerHolder = TotalIndexEnum.SECOND_PLAYER.id.setOutputInformationHolder(player[1])
        val thirdPlayerHolder = TotalIndexEnum.THIRD_PLAYER.id.setOutputInformationHolder(player[2])

        return arrayListOf(
            firstEnemyHolder,
            secondEnemyHolder,
            thirdEnemyHolder,
            firstPlayerHolder,
            secondPlayerHolder,
            thirdPlayerHolder
        )
    }

    //初期値
    fun initOutputInformationList(characterHolder: ArrayList<CharacterHolder>)
            : ArrayList<CharacterInformationHolder> {

        val belong = characterHolder[0].belong

        var firstHolder = CharacterInformationHolder()
        var secondHolder = CharacterInformationHolder()
        var thirdHolder = CharacterInformationHolder()

        if (belong == Belong.ENEMY.name){
            firstHolder = TotalIndexEnum.FIRST_ENEMY.id.setOutputInformationHolder(characterHolder[0],
                mutableMapOf()
            )
            secondHolder = TotalIndexEnum.SECOND_ENEMY.id.setOutputInformationHolder(characterHolder[1],
                mutableMapOf())
            thirdHolder = TotalIndexEnum.THIRD_ENEMY.id.setOutputInformationHolder(characterHolder[2],
                mutableMapOf())
        } else if (belong == Belong.PLAYER.name){
            firstHolder = TotalIndexEnum.FIRST_PLAYER.id.setOutputInformationHolder(characterHolder[0],
                mutableMapOf())
            secondHolder = TotalIndexEnum.SECOND_PLAYER.id.setOutputInformationHolder(characterHolder[1],
                mutableMapOf())
            thirdHolder = TotalIndexEnum.THIRD_PLAYER.id.setOutputInformationHolder(characterHolder[2],
                mutableMapOf())

    }
        return arrayListOf(firstHolder, secondHolder, thirdHolder)

    }

    private fun resetCharacterHolder(holder: CharacterInformationHolder ):CharacterHolder{
        return CharacterHolder(
            holder.belong,
            holder.name,
            holder.job,
            holder.maxHp,
            holder.maxMp,
            holder.str,
            holder.def,
            holder.agi,
            holder.luck,
            0)
    }
}