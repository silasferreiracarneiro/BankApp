package com.example.bank.bankapp.utils

import android.util.Base64
import android.util.Log
import java.util.regex.Pattern.compile
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

private val emailRegex = compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun String.isValidCpf(): Boolean {
    val cpf = this.replace("-", "").replace("/","").replace(".","")
    if (cpf == "00000000000" ||
        cpf == "11111111111" ||
        cpf == "22222222222" || cpf == "33333333333" ||
        cpf == "44444444444" || cpf == "55555555555" ||
        cpf == "66666666666" || cpf == "77777777777" ||
        cpf == "88888888888" || cpf == "99999999999" ||
        cpf.length !== 11
    ) return false

    if (cpf.length != 11)
        return false

    try {
        val number  = cpf.toLong()
    }catch (e : Exception){
        return false
    }


    val dvCurrent10 = cpf.substring(9,10).toInt()
    val dvCurrent11= cpf.substring(10,11).toInt()

    val cpfNineFirst = IntArray(9)
    var i = 9
    while (i > 0 ) {
        cpfNineFirst[i-1] = cpf.substring(i-1, i).toInt()
        i--
    }

    val sumProductNine = IntArray(9)
    var weight = 10
    var position = 0
    while (weight >= 2){
        sumProductNine[position] = weight * cpfNineFirst[position]
        weight--
        position++
    }

    var dvForTenthDigit = sumProductNine.sum() % 11
    dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
    if(dvForTenthDigit > 9)
        dvForTenthDigit = 0
    if (dvForTenthDigit != dvCurrent10)
        return false

    val cpfTenFirst = cpfNineFirst.copyOf(10)
    cpfTenFirst[9] = dvCurrent10
    //multiple the nine digits for your weights: 10,9..2
    val sumProductTen = IntArray(10)
    var w = 11
    var p = 0
    while (w >= 2){
        sumProductTen[p] = w * cpfTenFirst[p]
        w--
        p++
    }

    var dvForeleventhDigit = sumProductTen.sum() % 11
    dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
    if(dvForeleventhDigit > 9)
        dvForeleventhDigit = 0
    if (dvForeleventhDigit != dvCurrent11)
        return false

    return true
}

fun String.isValidEmail(): Boolean =
    emailRegex.matcher(this).matches()

fun String.encrypt(): String? {
    try {
        val secretKeySpec = SecretKeySpec(Constants.ENCRYPT_KEY.toByteArray(), "AES")
        val iv = Constants.ENCRYPT_KEY.toByteArray()
        val ivParameterSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

        val encryptedValue = cipher.doFinal(this.toByteArray())
        return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
    } catch (e: Exception) {
        e.message?.let{ Log.e("encryptor", it) }
    }
    return null
}

fun String.decrypt(): String? {
    try {
        val secretKeySpec = SecretKeySpec(Constants.ENCRYPT_KEY.toByteArray(), "AES")
        val iv = Constants.ENCRYPT_KEY.toByteArray()
        val ivParameterSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

        val decodedValue = Base64.decode(this, Base64.DEFAULT)
        val decryptedValue = cipher.doFinal(decodedValue)
        return String(decryptedValue)
    } catch (e: Exception) {
        e.message?.let{ Log.e("ERROR_DECRYPT", it) }
    }
    return null
}