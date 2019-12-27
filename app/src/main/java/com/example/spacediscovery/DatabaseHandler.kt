package com.example.spacediscovery

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.spacediscovery.chat.Chat
import com.google.gson.Gson

class DatabaseHandler(context: Context):
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ),
    IDatabaseHandler {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "chatsManager"
        private const val TABLE_CHATS = "chats"
        private const val KEY_ID = "id"
        private const val KEY_DATA = "data"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createChatsTable = "CREATE TABLE " + TABLE_CHATS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATA + " TEXT" + ")"
        db.execSQL(createChatsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHATS)
        onCreate(db)
    }

    override fun addChat(chat: Chat) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_DATA, Gson().toJson(chat))
        db.insert(TABLE_CHATS, null, values)
    }

    override fun getAllChats(): List<Chat> {
        val chatList = arrayListOf<Chat>()
        val selectQuery = "SELECT * FROM " + TABLE_CHATS
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val chat = Gson().fromJson(
                    cursor.getString(1),
                    Chat::class.java
                )
                chatList.add(chat)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return chatList
    }

    override fun deleteAll() {
        val db = this.writableDatabase
        db.delete(TABLE_CHATS, null, null)
    }

}