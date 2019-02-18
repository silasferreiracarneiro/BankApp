package com.example.bank.bankapp.infra

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.bank.bankapp.data.Sqlite.UserTable
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class DbSettings(var ctx : Context) : ManagedSQLiteOpenHelper(ctx, "BANK_BD_APP", null , 1) {

    companion object {
        private var instance : DbSettings? = null

        @Synchronized
        fun getInstance(ctx : Context) : DbSettings{
            if(instance == null)
            {
                instance = DbSettings(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(UserTable.CREATE_TABLE_USUARIO)
        Log.e("CREATE_TABLES", "TABELAS CRIADAS")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+UserTable.CREATE_TABLE_USUARIO)
        Log.e("UPDATE_TABLES", "TABELAS DELETADAS")
        onCreate(db)
    }
}