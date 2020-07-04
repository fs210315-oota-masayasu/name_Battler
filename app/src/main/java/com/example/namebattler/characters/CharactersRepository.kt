package com.example.namebattler.characters

import androidx.lifecycle.LiveData
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.database.CharactersDao

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

    fun areYouThere(name :String)  : LiveData<List<Characters>>{
        return charactersDao.getCharacterAtName(name)
    }

//    @WorkerThread
//    fun getCharacterAtName(nameToSearch: String){
//        charactersDao.getCharacterAtName(nameToSearch)
//    }
}