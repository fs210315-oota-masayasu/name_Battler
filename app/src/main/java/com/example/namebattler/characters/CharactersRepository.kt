package com.example.namebattler.characters

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.namebattler.data.Characters
import com.example.namebattler.data.CharactersDao

class CharactersRepository (private val charactersDao : CharactersDao){
    val allCharacters : LiveData<List<Characters>> = charactersDao.getAllCharacters()

    @WorkerThread
    fun insert(characters: Characters){
        charactersDao.insert(characters)
    }

}