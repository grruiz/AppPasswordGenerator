package com.rafaelgalvezruiz.passwordgenerator

class PasswordGenerator {

    private val characters = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val numbers = "12345678910"
    private val symbols = "!@#$%^&*()_-+=]{[}';:.,?></"
    fun generatePassword(length: Int, letters: Boolean, number: Boolean, symbol: Boolean): String {
        val sb = StringBuilder(length)
        var password: String = ""
        for (x in 0 until length) {
            if(letters){
                password += characters
            }
            if(number){
                password += numbers
            }
            if(symbol){
                password+= symbols
            }
            val random = (password.indices).random()
            sb.append(password[random])
        }
        return sb.toString()
    }
}