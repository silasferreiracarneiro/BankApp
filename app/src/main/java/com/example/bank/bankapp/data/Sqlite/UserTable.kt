package com.example.bank.bankapp.data.Sqlite

class UserTable {

    companion object {

        //NAME TABLE
        const val NAME_TABLE_USUARIO: String = "USUARIO"

        //COLUMNS TABLE
        const val LOGIN = "login"
        const val SENHA = "senha"

        //CREATE TABLE
        const val CREATE_TABLE_USUARIO = "CREATE TABLE "+ NAME_TABLE_USUARIO+"(" +
                ""+ LOGIN+" VARCHAR(100) NOT NULL," +
                ""+ SENHA+" VARCHAR(200) NOT NULL);"

    }
}