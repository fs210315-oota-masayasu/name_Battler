package com.example.namebattler.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.enemyData.EnemyRepository
import com.example.namebattler.data.jobData.JobManager

class EnemyViewModel: ViewModel() {

    //仮の敵データ格納スペース
    val enemyFormation: MutableLiveData<List<Characters>> = MutableLiveData()

/*    init {
        enemyFormation.value = EnemyRepository().getEnemyData()
    }*/

    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobManager().getJobList(index)
    }




}