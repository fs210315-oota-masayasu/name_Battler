package com.example.namebattler.data.skillAndMagic

import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.util.ConditionEnum

/** 各種Skill・魔法 **/
class Skills {

    /** 近接攻撃 **/

    //剣で攻撃
    fun swordAttack(character: CharacterInformationHolder): SkillData {

        //攻撃基準値
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str
        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //命中基準値
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val hitRateStandard = getPercent(luck)

        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = str / 2
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        val attackPoint = str + hittingPoint


        return SkillData("剣で攻撃した", attackPoint, 0, isCritical, Pair("", 0))
    }

    //斧で攻撃　//HPが減ればクリティカル確率増大
    fun axeAttack(character: CharacterInformationHolder): SkillData {

        //攻撃基準値
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str

        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //命中基準値
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        //命中補正値
        var hitRateCorrection = 100 - getPercent(character.currentHp, character.maxHp)

        var hitRateStandard = 0
        if (getPercent(luck) - 30 + hitRateCorrection > 0) {
            hitRateStandard = getPercent(luck) - 20 + hitRateCorrection
        }
        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = str / 2 + hitRateStandard
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        val attackPoint = str + hittingPoint


        return SkillData("斧で攻撃した", attackPoint, 0, isCritical, Pair("", 0))
    }

    fun maceAttack(character: CharacterInformationHolder): SkillData {

        //攻撃基準値
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str
        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //命中基準値
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val hitRateStandard = (getPercent(luck) / 4) + (getPercent(luck) / 2)

        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = str / 2
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        val attackPoint = str + hittingPoint

        return SkillData("メイスで攻撃した", attackPoint, 0, isCritical, Pair("", 0))
    }

    fun rodAttack(character: CharacterInformationHolder): SkillData {

        //攻撃基準値
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str
        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //命中基準値
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val hitRateStandard = getPercent(luck) / 2


        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = str / 2
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        //ダメージ値
        val attackPoint = str + hittingPoint

        return SkillData("杖で攻撃した", attackPoint, 0, isCritical, Pair("", 0))
    }


    /** 魔法攻撃 **/

    //スリープクラウド（ねむり　MP小）
    fun sleepCloud(character: CharacterInformationHolder): SkillData {
        return SkillData("眠りの呪文を唱えた", 0, 10, false, Pair(ConditionEnum.SLEEP.text, 2))
    }


    //サンダーボルト（ダメージ ＋ 麻痺 MP中）
    fun thunderbolt(character: CharacterInformationHolder): SkillData {

        //魔力
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val mp = (character.maxMp + (0..luck).random()) / 2

        //命中基準値
        val hitRateStandard = getPercent(luck)
        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = mp / 2
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        //ダメージ値
        val attackPoint = mp + hittingPoint

        return SkillData(
            "雷撃の呪文を唱えた",
            attackPoint,
            20,
            isCritical,
            Pair(ConditionEnum.PARALYSIS.text, 2)
        )
    }


    //ファイアボール （全体魔法　MP大）
    fun fireBall(character: CharacterInformationHolder): SkillData {

        //魔力
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val mp = (character.maxMp + (0..luck).random()) / 2

        //命中基準値
        val hitRateStandard = getPercent(luck)
        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = mp / 2
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        //ダメージ値
        val attackPoint = mp + hittingPoint



        return SkillData("火球の呪文を唱えた", attackPoint, 30, isCritical, Pair(ConditionEnum.SCALD.text, 2))
    }

    /** 回復・サポート **/

    //回復呪文
    fun cureWounds(character: CharacterInformationHolder): SkillData {
        //魔力
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val healStandard = getPercent(luck)

        //命中率（低目有利）
        val healRate = (0..100).random()

        var healPoint: Int

        healPoint = (100 - healRate) / 100 * healStandard
        return SkillData("癒しの呪文を唱えた", healPoint, 20, false, Pair("", 0))
    }

    //状態異常回復
    fun refresh(character: CharacterInformationHolder): SkillData {

        return SkillData("リフレッシュの呪文を唱えた", 0, 10, false, Pair(ConditionEnum.FRESH.text, 0))
    }

    //物理防護点
    fun meleeDefense(character: CharacterInformationHolder): SkillData {

        //防御力
        //buff,deBuffの適用
        val correctionDefVale = character.effectTimeOfDef
        var def = character.def
        if (correctionDefVale > 0) {
            def = def * 3 / 2
        } else if (correctionDefVale < 0) {
            def = def * 2 / 3
        }

        //筋力
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str

        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //防護点
        val meleeDefensePoint = (def * 2 / 3) + (str / 3)

        return SkillData("攻撃を防いだ", meleeDefensePoint, 0, false, Pair("", 0))
    }


    //魔法防護点
    fun magicDefense(character: CharacterInformationHolder): SkillData {

        //防御力
        //buff,deBuffの適用
        val correctionDefVale = character.effectTimeOfDef
        var def = character.def
        if (correctionDefVale > 0) {
            def = def * 3 / 2
        } else if (correctionDefVale < 0) {
            def = def * 2 / 3
        }
        //魔力
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val mp = (character.maxMp + (0..luck).random()) / 2

        //防護点
        var magicDefence = (def * 2 / 3) + (mp / 3)

        return SkillData("攻撃を防いだ", magicDefence, 0, false, Pair("", 0))
    }

    //ぼうぎょ
    fun meleeGuard(character: CharacterInformationHolder): SkillData {

        return SkillData("防御の構えをとった", 2, 0, false, Pair("", 0))
    }

    private fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }


    private fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}


