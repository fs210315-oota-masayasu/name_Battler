package com.example.namebattler.function

import com.example.namebattler.function.job.*

enum class HeaderFlag{
    DEFAULT,
    BATTLE_MAIN,
    OPERATION_CHANGE,
    RETURN_HOME,
    PARTY_FORMATION,
    NONE,
}

enum class BackStack{
    CHARACTER_LIST,
    NEW_CHARACTER_GENERATE,
    PARTY_FORMATION,
    BATTLE_MAIN,
}

enum class JobEnum (val jobName :String, val obj: JobParameterProduction.JobAbstract){
    WARRIOR("戦士" , Warrior()),
    SPELL_CASTER("魔法使い", SpellCaster()),
    PRIEST("僧侶", Priest()),
    BERSERK("バーサーカー", Berserk()),
    NONE("ERROR",Warrior())
}

enum class EndEnum{
    WIN,
    LOSE
}

enum class TotalIndexEnum(val id: Int) {
    FIRST_ENEMY(0),
    SECOND_ENEMY(1),
    THIRD_ENEMY(2),
    FIRST_PLAYER(3),
    SECOND_PLAYER(4),
    THIRD_PLAYER(5),
}

enum class Belong{
    PLAYER,
    ENEMY
}

enum class OperationIdEnum(val id: Int, val text: String) {
    OFFENSIVE (0, "ガンガンいこうぜ"),
    DEFENSIVE(1, "いのちだいじに"),
    FLEXIBLE(2, "バッチリがんばれ"),
    ERROR(9, "ERROR"),
}

enum class ConditionEnum(val id: Int, val text: String) {
    FRESH(0,"治癒"),
    SLEEP(1,"眠り"),
    PARALYSIS(2,"麻痺"),
    SCALD(3,"炎上"),
    DEAD(4,"死亡")
}

enum class SKillEnum(val id: Int) {
    ONE_MELEE_ATTACK(0),
    SLEEP_CLOUD(1),
    THUNDERBOLT(2),
    FIREBALL(3),
    GUARD(4),
    REFRESH(5),
    CURE_WOUNDS(6),
}

enum class DamageTypeEnum{
    NO_DAMAGE,
    NORMAL_DAMAGE,
    NON_LETHAL_DAMAGE
}

enum class TargetIdEnum(val id: Int){
    ONE_ATTACK(0),
    ALL_ATTACK(1),
    MYSELF(2),
    ONE_SUPPORT(3),
    ALL_SUPPORT(4),
}

enum class EntryRule(val num: Int){
    MEMBER(3)
}