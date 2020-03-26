package com.example.namebattler.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.namebattler.data.AppDatabase
import com.example.namebattler.data.Characters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>
    var getCharacterdata : LiveData<List<Characters>> ?= null

    init {
        val charactersDao = AppDatabase.getInstance(application, scope).charactersDao()
        repository = CharactersRepository(charactersDao)
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