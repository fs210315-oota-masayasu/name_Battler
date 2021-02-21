package com.example.namebattler.database.characterDatabase

import androidx.lifecycle.LiveData

class CharactersRepository (private val charactersDao : CharactersDao){

    val allCharacters : LiveData<List<Characters>> = charactersDao.getAllCharacters()

    suspend fun update(characters: Characters){
        charactersDao.update(characters)
    }

    suspend fun insert(characters: Characters){
        charactersDao.insert(characters)
    }

    suspend  fun delete(characters: Characters){
        charactersDao.delete(characters)
    }

    fun numOfRegistrations() :Int{
        return charactersDao.numOfRegistrations()
    }

     fun countOverlap(searchName: String) : Int?{
        return charactersDao.countOverlap(searchName)
    }
}