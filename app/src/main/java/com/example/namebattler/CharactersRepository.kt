package com.example.namebattler

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.namebattler.database.CharactersDao
import com.example.namebattler.model.Characters

class CharactersRepository (private val charactersDao : CharactersDao){
    val allCharacters : LiveData<List<Characters>> = charactersDao.getAllCharacters()

    @WorkerThread
    suspend fun  insert(characters: Characters){
        charactersDao.insert(characters)
    }
}