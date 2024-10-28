package com.example.ejercicio_git

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.util.Log


class DatabaseCurso(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Cursos.db"
        private const val TABLE_NAME = "curso"
        private const val CODIGO = "codigo"
        private const val CURSO = "curso"
        private const val CARRERA = "carrera"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$CODIGO TEXT PRIMARY KEY ," +
                "$CURSO TEXT," +
                "$CARRERA TEXT )"
                )
        db.execSQL(createTable)
        Log.d("DatabaseCurso", "Tabla creada: $createTable")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun agregarCurso(curso: Model): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(CODIGO, curso.codigo)
            put(CURSO, curso.curso)
            put(CARRERA, curso.carrera)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun editarCurso(curso: Model): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(CURSO, curso.curso)
            put(CARRERA, curso.carrera)
        }
        val selection = "$CODIGO = ?"
        val selectionArgs = arrayOf(curso.codigo)
        return db.update(TABLE_NAME, values, selection, selectionArgs)
    }

    fun eliminarCurso(codigo: String): Int {
        val db = writableDatabase
        val selection = "$CODIGO = ?"
        val selectionArgs = arrayOf(codigo)
        return db.delete(TABLE_NAME, selection, selectionArgs)
    }

    fun mostarCursos(): List<Model> {
        val cursos = mutableListOf<Model>()
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_NAME, // La tabla a consultar
            null,   // Columnas (null selecciona todas)
            null,   // WHERE (null selecciona todas las filas)
            null,   // Argumentos del WHERE
            null,   // GROUP BY
            null,   // HAVING
            null    // ORDER BY
        )

        with(cursor) {
            while (moveToNext()) {
                val codigo = getString(getColumnIndexOrThrow(CODIGO))
                val curso = getString(getColumnIndexOrThrow(CURSO))
                val carrera = getString(getColumnIndexOrThrow(CARRERA))
                cursos.add(Model(codigo, curso, carrera))
            }
        }
        cursor.close()
        return cursos
    }
}

