package com.example.namebattler.characters

import androidx.lifecycle.LiveData
import com.example.namebattler.data.Characters
import com.example.namebattler.data.CharactersDao

class CharactersRepository (private val charactersDao : CharactersDao){
    val allCharacters : LiveData<List<Characters>> = charactersDao.getAllCharacters()

    suspend fun insert(characters: Characters){
        charactersDao.insert(characters)
    }

    suspend  fun delete(characters: Characters){
        charactersDao.delete(characters)
    }


//    @WorkerThread
//    fun getCharacterAtName(nameToSearch: String){
//        charactersDao.getCharacterAtName(nameToSearch)
//    }
}