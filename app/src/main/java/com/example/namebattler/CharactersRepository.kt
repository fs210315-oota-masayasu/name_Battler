package com.example.namebattler

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class CharactersRepository (private val charactersDao : CharactersDao){
    val allCharacters : LiveData<List<Characters>> = charactersDao.getAllCharacters()

    @WorkerThread
    suspend fun  insert(characters: Characters){
        charactersDao.insert(characters)
    }
}