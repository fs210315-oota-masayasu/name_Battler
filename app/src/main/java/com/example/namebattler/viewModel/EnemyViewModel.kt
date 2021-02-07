package com.example.namebattler.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager

class EnemyViewModel: ViewModel() {

    //仮の敵データ格納スペース
    val enemyFormation: MutableLiveData<List<Characters>> = MutableLiveData()

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobManager().getJobList(index)
    }

}