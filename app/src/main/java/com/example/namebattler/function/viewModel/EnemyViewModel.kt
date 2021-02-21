package com.example.namebattler.function.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.database.characterDatabase.Characters
import com.example.namebattler.function.job.JobParameterProduction

class EnemyViewModel: ViewModel() {

    //仮の敵データ格納スペース
    val enemyFormation: MutableLiveData<List<Characters>> = MutableLiveData()

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobParameterProduction().getJobList(index)
    }

}