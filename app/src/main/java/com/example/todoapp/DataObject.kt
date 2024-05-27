package com.example.todoapp

object DataObject {
     var list_data = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String) {
        list_data.add(CardInfo(title, priority))
    }

    fun getAllData(): List<CardInfo> {
        return list_data
    }

    fun getData(pos: Int): CardInfo {
        return list_data[pos]
    }

    fun deleteData(pos: Int) {
        list_data.removeAt(pos)
    }

    fun deleteAll() {
        list_data.clear()
    }

    fun updateData(pos: Int, title: String, priority: String) {
        list_data[pos].title = title
        list_data[pos].priority = priority

    }
}