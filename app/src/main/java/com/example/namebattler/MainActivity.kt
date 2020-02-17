package com.example.namebattler

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
/*    companion object {
     lateinit var database: AppDatabase
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //キャラクター一覧画面への遷移処理
        btn_character_list.setOnClickListener{
            val setintentCharacterList = Intent(this, CharacterListActivity::class.java)
            startActivity(setintentCharacterList)
        }
        //パーティ編成画面への遷移処理
        btn_party_create.setOnClickListener {
            val setintentPartyCreate = Intent(this, PartyCreateActivity::class.java)
            startActivity(setintentPartyCreate)

        }

        //database = AppDatabase.getInstance(applicationContext)

//        val sendToCharacterList: Button = findViewById(R.id.btn_character_list)
//        sendToCharacterList.setOnClickListener { startActivity(intent) }

        //view.findViewById(R.id.btn_character_list).setOnClickListener(btnCharacterListClickListener);



    }

//    var btnCharacterListClickListener: View.OnClickListener = View.OnClickListener {
//        val intent = Intent(this, CharacterListActivity::class.java)


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


//internal inline fun <reified T : Any> objectOf() = T::class.java