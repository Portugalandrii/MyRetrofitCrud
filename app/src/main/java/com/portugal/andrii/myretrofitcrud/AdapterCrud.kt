package com.portugal.andrii.myretrofitcrud

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class AdapterCrud(val context: Context,val userList: ArrayList<Crud>): RecyclerView.Adapter<AdapterCrud.ViewHolder>() {

//onCreateViewHolder создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
// Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder,
// и каждый дочерний view-компонент может быть найден и сохранен.

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_layout, p0, false)
        return ViewHolder(v);
    }
//принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте
    override fun onBindViewHolder(vh: ViewHolder, posit: Int) {
        // vt поле
        vh?.author?.text = userList[posit].author
        vh?.description?.text = userList[posit].description
        vh?.id?.text = userList[posit].id.toString()
        vh?.published?.text = userList[posit].published.toString()
        vh?.title?.text = userList[posit].title
        vh?.item?.setOnClickListener { //при нажатии на эллемент открываем activity_crud.xml
            //Intent - (намерение) переход из одного активити в другое
            val intent = Intent(context, CrudActivity::class.java)
            // упаковываем данные в объект Extra по каждому эллементу
            intent.putExtra("id", userList.get(posit).id)
            intent.putExtra("title", userList.get(posit).title)
            intent.putExtra("author", userList.get(posit).author)
            intent.putExtra("description", userList.get(posit).description)
            intent.putExtra("published", userList.get(posit).published)
             // запустили интент
            context.startActivity(intent)
            }
    }
//возвращает общее количество элементов списка. Значения списка передаются с помощью конструктора.
    override fun getItemCount(): Int {
        return userList.size
    }
        // класс ViewHolder содержит ссылки эллемента разметки
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val author = itemView.findViewById<TextView>(R.id.author)
        val description = itemView.findViewById<TextView>(R.id.description)
        val id = itemView.findViewById<TextView>(R.id.id)
        val published = itemView.findViewById<TextView>(R.id.published)
        val title = itemView.findViewById<TextView>(R.id.title)
        val item = itemView.findViewById<LinearLayout>(R.id.item2)
    }

}