package com.example.namebattler.memu

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.characters.CharactersRepository
import com.example.namebattler.data.AppDatabase
import com.example.namebattler.data.Characters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : ViewModel() {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>
    var getCharacterdata : LiveData<List<Characters>> ?= null


    init {
        val charactersdao = AppDatabase.getInstance(application).charactersDao()
        repository =
            CharactersRepository(charactersdao)
        allCharacters = repository.allCharacters
    }

    fun insert(characters: Characters) = scope.launch(Dispatchers.IO) {
        repository.insert(characters)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }




    fun characterAtName(nameToSearch: String) = scope.launch(Dispatchers.IO) {
        repository.nameToSearch = nameToSearch

        getCharacterdata  = repository.characterData
        //val dao = AppDatabase.getInstance(scope).charactersDao()

        //val getCharacter: LiveData<List<Characters>> =

     }





}