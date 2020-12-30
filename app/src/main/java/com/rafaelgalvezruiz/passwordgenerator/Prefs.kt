package com.rafaelgalvezruiz.passwordgenerator

import android.content.Context
import android.widget.CheckBox

class Prefs(val context: Context) {
    private val SHARED_NAME = "passwordb"
    private val SHARED_LETTERS = "letters"
    private val SHARED_NUMBERS = "numbers"
    private val SHARED_SYMBOLS = "symbols"
    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveLetters(letters: Boolean){
        storage.edit().putBoolean(SHARED_LETTERS, letters).apply()
    }

    fun saveNumbers(numbers: Boolean){
        storage.edit().putBoolean(SHARED_NUMBERS, numbers).apply()
    }

    fun saveSymbols(symbols: Boolean){
        storage.edit().putBoolean(SHARED_SYMBOLS, symbols).apply()
    }

    fun getLetters():Boolean = storage.getBoolean(SHARED_LETTERS,true)
    fun getNumbers():Boolean = storage.getBoolean(SHARED_NUMBERS,true)
    fun getSymbols():Boolean = storage.getBoolean(SHARED_SYMBOLS,true)
}