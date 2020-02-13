package com.example.namebattler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
/*    companion object {
     lateinit var database: AppDatabase
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //database = AppDatabase.getInstance(applicationContext)

        val sendToCharacterList: Button = findViewById(R.id.btn_character_list)

        val intent = Intent(this, CharacterListActivity::class.java)
        sendToCharacterList.setOnClickListener { startActivity(intent) }


    }

/*    private fun create(){
        val dao = database.charactersDao()
        dao.insert(Characters(0,"test_name",100,100,100,100,100,100,0, 0))
        //eturn dao.getAllCharacters()
    }*/



/*
    // データモデルを作成
    val user = User()
    user.uid = Random().nextInt()
    user.firstName = "Yuya"
    user.lastName = "Matsuo"
*/


/*    private fun update() {
        val dao = database.charactersDao()
        var userList: List<User> = dao.getAllUserData()
        dao.update(User(0, "miyagawa", Gender.FEMALE))
        userList = dao.getAllUserData()
    }*/

/*    private fun delete() {
        val dao = database.charactersDao()
        var userList: List<User> = dao.getAllUserData()
        dao.delete(User(0, "miyagawa", Gender.FEMALE))
        userList = dao.getAllUserData()
    }*/

}

//internal inline fun <reified T : Any> objectOf() = T::class.java