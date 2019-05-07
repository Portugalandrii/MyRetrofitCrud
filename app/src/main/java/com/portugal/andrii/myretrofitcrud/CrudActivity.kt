package com.portugal.andrii.myretrofitcrud

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crud.*


class CrudActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
       // получаем объект из intent

        if (intent.extras!=null) {
            id.setText(intent.extras.getInt("id").toString())
            title_book.setText(intent.extras.getString("title"))
            author.setText(intent.extras.getString("author"))
            description.setText(intent.extras.getString("description"))
            published.setText(intent.extras.getInt("published").toString())
        }
    }
}
