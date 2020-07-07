package com.example.bank.bankapp.utils

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `isValidCpf - valida passando letras ao inves de numeros`() {
        assertFalse("dsadsadasd".isValidCpf())
    }

    @Test
    fun `isValidCpf - valida passando cpf invalido`() {
        assertFalse("999.999.999-99".isValidCpf())
    }

    @Test
    fun `isValidCpf - valida passando cpf valido`() {
        assertTrue("038.176.780-96".isValidCpf())
    }

    @Test
    fun `isValidEmail - valida email passando um valor vazio`() {
        Assert.assertFalse("".isValidEmail())
    }

    @Test
    fun `isValidEmail - valida email passando um valor invalido`() {
        Assert.assertFalse("sadsadasdsa".isValidEmail())
    }

    @Test
    fun `isValidEmail - valida email passando um valor valido`() {
        Assert.assertTrue("teste@teste.com".isValidEmail())
    }
}