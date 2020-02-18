package com.example.namebattler

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CharacterListViewModel (application: Application) : AndroidViewModel(application) {
/*
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository :CharactersRepository
    val allCharacters: LiveData<List<Characters>>

    init {
        val charactersdao = AppDatabase.getInstance(application, scope).charactersDao()
        repository = CharactersRepository(charactersdao)
        allCharacters = repository.allCharacters
    }

    fun insert(characters: Characters) = scope.launch(Dispatchers.IO){
        repository.insert(characters)
    }

    override fun onCleared(){
        super.onCleared()
        parentJob.cancel()
    }

*/


}