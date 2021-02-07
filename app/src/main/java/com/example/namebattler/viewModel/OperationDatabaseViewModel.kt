package com.example.namebattler.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.namebattler.characters.CharactersRepository
import com.example.namebattler.data.database.AppDatabase
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class OperationDatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>

//    val countOverlap = MutableLiveData<Int>()

    var countOverlap = MutableLiveData<Int>()
    val numOfRegistrations = MutableLiveData<Int>()

    init {
        //Daoのインスタンスを取得してRepositoryへ格納
        val charactersDao = AppDatabase.getInstance(application, viewModelScope).charactersDao()
        repository = CharactersRepository(charactersDao)
        allCharacters = repository.allCharacters
    }

    fun update(characters: Characters) = viewModelScope.launch {
        repository.update(characters)
    }

    fun insert(characters: Characters) = viewModelScope.launch {
        repository.insert(characters)
    }

    fun delete(characters: Characters) = viewModelScope.launch {
        repository.delete(characters)
    }

    private fun countOverlap(searchName: String): Int? {
        return repository.countOverlap(searchName)
    }

    private fun numOfRegistrations(): Int {
        return repository.numOfRegistrations()
    }

    fun confirmNumOfRegistrations() {
        //非同期処理でMutableLiveDataに登録件数を格納
        thread {
            numOfRegistrations.postValue(numOfRegistrations())
        }
    }

    //パーティ編成画面のチェック状態を保持するリスト
    var isCheckedPartyFormation: MutableList<MutableLiveData<Boolean>> = mutableListOf()

    //isCheckedPartyFormationのサイズ数を登録件数と同一にしてfalseで埋める
    fun initCheckList() {
        isCheckedPartyFormation =
            MutableList(numOfRegistrations.value ?: 0) { MutableLiveData(false) }
    }

    fun confirm(searchName: String) {
        Log.d("VM_NAME","おなまえ：$searchName")

        viewModelScope.launch(Dispatchers.IO) {

            Log.d("VM_CO", "すでにありますか？${countOverlap(searchName)}")
            countOverlap.postValue(countOverlap(searchName))
        }
    }


    //DBに格納されている値から名前へと変換
    fun getJobName(index: Int): String {
        return JobManager().getJobList(index)
    }


}