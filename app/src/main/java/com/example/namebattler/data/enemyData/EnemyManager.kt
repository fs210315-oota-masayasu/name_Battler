package com.example.namebattler.data.enemyData

import com.example.namebattler.data.characterData.Player
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.util.OperationIdEnum
import kotlin.random.Random

class EnemyManager {

    companion object {
        //Enumを取得
        val operationEnum = OperationIdEnum.values()
    }

    init {
        //初期化時にoperationListへ作戦名を格納
        operationEnum.forEach {
            operationList.add(it.text)
        }
    }

    private val enemyNameList = EnemyList()
    //作戦リスト
    private val operationList= mutableListOf<String>()

    fun selectEnemyOperation() :String{
        //取得する要素をランダムで決定する
        val index = Random.nextInt(operationList.size)
        return operationList[index]
    }

    fun getEnemyData() : List<Characters>{

        //enemyNameListから重複なく名前を3点取得
        val randomSelectEnemyList =  enemyNameList.nameList.takeAtRandom(3)

        //JobManager().jobListから重複なくジョブを3点取得
        val randomSelectJob = JobManager().jobEnumList.takeAtRandom(3)

        //戻り値用変数
        val enemyDataList = mutableListOf<Characters>()

        randomSelectEnemyList.forEach {

            //randomSelectEnemyListのインデックスでrandomSelectJobの中からジョブ名を取り出す
            val extractJobEnum = randomSelectJob[randomSelectEnemyList.indexOf(it)]

            //キャラクター名、ジョブでパラメータを自動生成
            //obManager().getJobListでジョブに割り振られているインデックスを取得
            //val enemyData = Player(it,JobManager().getJobList(extractJobName)).getParam()?:
            val enemyData = Player(it,JobManager().getJobList(extractJobEnum.jobName)).getParam()?:
            Characters(
                "Error:パラメータ取得失敗",
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
            )
            enemyDataList.add(enemyData)
        }

        return enemyDataList
    }

    /**
     * リストから指定された数の要素をランダムに重複なく取り出す。
     *
     * @receiver     このリストから要素を取り出す。
     * @param n      取り出す要素の数。
     * @param random 使用する乱数生成器。
     * @param <T>    要素の型。
     * @return 取得された要素を持つリスト。
     */
    private fun <T> Collection<T>.takeAtRandom(n: Int, random: Random = Random): List<T> {
        require(n <= size) { "引数 n の値 $n がレシーバーのサイズ ${size} より大きいです。" }

        val taken = mutableListOf<T>() // ランダムに選択された要素を持たせるリスト

        val remaining = toMutableList() // 残っている要素のリスト
        // n 回繰り返す。
        repeat(n) {
            val remainingCount = remaining.size // 残っている要素の数
            val index = random.nextInt(remainingCount) // ランダムに選択されたインデックス

            taken += remaining[index] // ランダムに選択された要素

            val lastIndex = remainingCount - 1 // 残っている要素のリストの末尾のインデックス
            val lastElement = remaining.removeAt(lastIndex) // 残っている要素のリストから末尾を削除する。
            if (index < lastIndex) { // ランダムに選択された要素が末尾以外なら…
                remaining[index] = lastElement // それを末尾の要素で置換する。
            }
        }

        return taken
    }
}