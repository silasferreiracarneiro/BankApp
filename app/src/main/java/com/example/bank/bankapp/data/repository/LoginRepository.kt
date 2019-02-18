package com.example.bank.bankapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.StrictMode
import com.example.bank.bankapp.data.LoginAPI
import com.example.bank.bankapp.data.Sqlite.UserTable
import com.example.bank.bankapp.infra.RetrofitClient
import com.example.bank.bankapp.data.dto.UsuarioResponseDTO
import com.example.bank.bankapp.data.dto.LoginDto
import com.example.bank.bankapp.infra.DbSettings
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select
import retrofit2.Response

class LoginRepository(ctx: Context) {

    var provider: LoginAPI

    private var instance : DbSettings? = null
    protected var db : SQLiteDatabase? = null

    init {
        val retrofit = RetrofitClient.instance
        provider = retrofit.create(LoginAPI::class.java)

        this.instance = DbSettings.getInstance(ctx)
        this.db = instance?.writableDatabase
    }

    fun sendLogin(usuario: LoginDto) : Response<UsuarioResponseDTO> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var call = provider.login(usuario)
        var response = call.execute()
        return response
    }

    fun insertUser(usuario: LoginDto){
        try {
            deleteAllUserDb()

            val values = ContentValues()
            values.put(UserTable.LOGIN, usuario.user)
            values.put(UserTable.SENHA, usuario.password)

            db?.insertOrThrow(UserTable.NAME_TABLE_USUARIO, null, values)?.toInt()!!
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    fun getUser() : LoginDto {
        try {

            var query = db?.select(UserTable.NAME_TABLE_USUARIO)

            return query?.parseOpt(object: MapRowParser<LoginDto> {
                override fun parseRow(columns: Map<String, Any?>): LoginDto {

                    return LoginDto(columns.getValue(UserTable.LOGIN)as String,
                                    columns.getValue(UserTable.SENHA)as String)
                }
            })!!

        }catch (e : Exception){
            e.printStackTrace()
        }
        return null!!
    }

    private fun deleteAllUserDb(){
        db?.delete(UserTable.NAME_TABLE_USUARIO, null, null)
    }
}