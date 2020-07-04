package com.example.namebattler.data.jobData


open class JobManager{

    var hp : Int = 0
    var mp : Int = 0
    var str : Int = 0
    var def : Int = 0
    var agi : Int = 0
    var luck : Int = 0

    //各ジョブのインスタンス
    private var warriorObj = Warrior()
    private var spellCasterObj = SpellCaster()
    private var priestObj = Priest()
    private var berserkObj = Berserk()

    /*
    指定したジョブからパラメータを取得するしてメンバ変数へ格納する
    */
    fun addParam(job:Int) {
        var param = Parameter(0,0,0,0,0,0)
        when(job){
        /*
        ジョブのインデックスを条件にして処理を分ける
            0 -> param = Warrior()
            1 -> param = SpellCaster()
            2 -> param = Priest()
            3 -> param = Berserk()
        */
            0 -> {
                warriorObj.addParam()
                param = Parameter(warriorObj.hp ,warriorObj.mp ,warriorObj.str ,
                    warriorObj.def ,warriorObj.agi ,warriorObj.luck)
            }

            1 -> {
                spellCasterObj.addParam()
                param = Parameter(
                    spellCasterObj.hp, spellCasterObj.mp, spellCasterObj.str,
                    spellCasterObj.def, spellCasterObj.agi, spellCasterObj.luck)
            }


            2 -> {
                priestObj.addParam()
                param = Parameter(priestObj.hp ,priestObj.mp ,priestObj.str ,
                    priestObj.def ,priestObj.agi ,priestObj.luck)
            }

            3 -> {
                berserkObj.addParam()
                param = Parameter(berserkObj.hp ,berserkObj.mp ,berserkObj.str ,
                    berserkObj.def ,berserkObj.agi ,berserkObj.luck)
            }
        }
        this.hp = param.hp
        this.mp = param.mp
        this.str = param.str
        this.def = param.def
        this.agi = param.agi
        this.luck = param.luck
    }

    //インデックスがキャラクターテーブルのジョブの値になる
    val jobList = mutableListOf("戦士","魔法使い","僧侶","バーサーカー")
    //職業を追加する場合はaddで

    //名前からインデックスを取得
    fun getJobList(name : String): Int {
        return jobList.indexOf(name)
    }

    //インデックスから名前を取得
    fun getJobList(index : Int):String{
        return jobList[index]
    }

    //addParam実行時に対象のジョブのパラメータを格納
    inner class Parameter(
        val hp :Int,
        val mp :Int,
        val str:Int,
        val def:Int,
        val agi:Int,
        val luck:Int
    )

    //各ジョブで実装する継承クラス
    abstract class JobAbstract{
        abstract var hp : Int
        abstract var mp : Int
        abstract var str : Int
        abstract var def : Int
        abstract var agi : Int
        abstract var luck : Int
        abstract var supplyBox:List<Int>
        abstract fun addParam()
        abstract fun uniqueVale()
    }
}