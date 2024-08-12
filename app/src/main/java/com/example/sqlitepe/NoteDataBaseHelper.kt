package com.example.sqlitepe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.RawContacts.Data

class NoteDataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_LINK = "link"
        private const val COLUMN_CONTENT = "content"
    }



    override fun onCreate(db: SQLiteDatabase?) {
        // Definir la consulta SQL para crear la tabla
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_LINK TEXT,
                $COLUMN_CONTENT TEXT
            )
        """.trimIndent()

        // Ejecutar la consulta SQL
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar la tabla existente si existe y crearla de nuevo
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertNotes(note:Nota){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_LINK,note.link)
            put(COLUMN_CONTENT,note.contenido)
        }
        db.insert(TABLE_NAME, null,values)
        db.close()
    }

    fun getAllNotes():List<Nota>{
        val notesList = mutableListOf<Nota>()
        val db = readableDatabase
        val query ="SELECT * FROM $TABLE_NAME"
        val cursor =db.rawQuery(query,null)


        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINK))
            val content= cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val note =Nota(id,title,content)
            notesList.add(note)
        }

        cursor.close()
        db.close()
        return notesList
    }



}