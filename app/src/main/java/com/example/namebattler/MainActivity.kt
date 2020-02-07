package com.example.namebattler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    companion object {
     lateinit var database: AppDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendToCharacterList: Button = findViewById(R.id.btn_character_list)

        val intent = Intent(this, CharacterListActivity::class.java)
        sendToCharacterList.setOnClickListener { startActivity(intent) }

        database = Room.databaseBuilder(this, objectOf<AppDatabase>(), "name_battler.db").build()
    }
//https://re-engines.com/2019/10/24/%E3%80%90kotlin%E3%80%91room%E3%81%A7db%E7%AE%A1%E7%90%86%E3%82%92%E3%81%99%E3%82%8B/

    private fun create() {
        val dao = database.charactersDao()
        dao.insert(Characters("miya-n",1,100,100,100,100,100,100,null)
        var userList: List<User> = dao.getAllUserData()
    }

    private fun update() {
        val dao = database.charactersDao()
        var userList: List<User> = dao.getAllUserData()
        dao.update(User(0, "miyagawa", Gender.FEMALE))
        userList = dao.getAllUserData()
    }

    private fun delete() {
        val dao = database.charactersDao()
        var userList: List<User> = dao.getAllUserData()
        dao.delete(User(0, "miyagawa", Gender.FEMALE))
        userList = dao.getAllUserData()
    }

}

internal inline fun <reified T : Any> objectOf() = T::class.java