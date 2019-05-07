package com.portugal.andrii.myretrofitcrud

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add.setOnClickListener { //при нажатии на эллемент открываем activity_crud.xml
            //Intent - (намерение) переход из одного активити в другое
            val add = Intent(this, CrudActivity::class.java)
            startActivity(add)
        }

        val rv = findViewById<RecyclerView>(R.id.recyclerView1)//подключаем разметку

        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val list = ArrayList<Crud>()
        //создаем объект crud, который реализует интерфейс CrudInterface с помощью метода getCrud()
        val crud = CrudInterface.create().getCrud()
        //делаем асинхронный (enqueue) запрос и в качестве параметра метода передаем реализацию интерфейса Callback
        //Callback - дествие, которое выполняется после наступления события, в нашем случае, ответ
        //от сервера
        crud.enqueue(object : retrofit2.Callback<List<Crud>> {
           //onFailure и onResponse - это стандартные функции для отработки Callback(работы с сервером)
            override fun onFailure(call: retrofit2.Call<List<Crud>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"ERROR",Toast.LENGTH_LONG).show()
            }
        // метод  onResponse отработает в случае удачного запроса с сервера
            override fun onResponse(call: retrofit2.Call<List<Crud>>, response: Response<List<Crud>>) {
                //принимаем тело ответа, а передаем массив
                list.addAll(response.body()!!) //добавляем список list всех crud, которые получили от сервера и
                                               //записали результаты в список, передали список в адаптер, а
                                               //адаптер в RecyclerView
                var adapter:AdapterCrud= AdapterCrud(this@MainActivity,list)
                rv.adapter = adapter

            }
        })
    }
}
