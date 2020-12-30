package com.rafaelgalvezruiz.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.rafaelgalvezruiz.passwordgenerator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var startPoint = 0
    var endPoint = 40
    private val passwordGenerator = PasswordGenerator()
    private lateinit var password: String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PasswordGenerator)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generatePassword(4, binding)

        binding.textView4.text = "LULA MUNGULA"
        seekBarConfiguration(binding)
        //Configuration button can copytext of textview
        binding.btnCopiar.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("Editext", binding.etPassword.text)
            clipboard.setPrimaryClip(clipData)
            Toast.makeText(this@MainActivity, "Copiado", Toast.LENGTH_SHORT).show()
        }

        //Refresh the password
        binding.btnRefrescar.setOnClickListener {
            generatePassword(binding.etPassword.text.length, binding)
        }
        //Controller Checkbox
        checkCheckbox(binding)
    }

    private fun seekBarConfiguration(binding: ActivityMainBinding) {

        //Configuration seekBar
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvLongitud.text = "Longitud ($p1)"
                //Generate password with the lenght of seekbar
                generatePassword(p1, binding)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                if (p0 != null) {
                    startPoint = p0.progress
                }
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                if (p0 != null) {
                    endPoint = p0.progress
                }
            }

        })
    }


    private fun generatePassword(nCharacters: Int, binding: ActivityMainBinding) {
        password = passwordGenerator.generatePassword(
            nCharacters,
            letters = binding.cbLetras.isChecked,
            number = binding.cbDigitos.isChecked,
            symbol = binding.cbSimbolos.isChecked
        )
        //Check lenght of seekBar
        when {
            nCharacters < 8 -> {
                binding.etPassword.setText(password)
                binding.tvMensaje.text = getString(R.string.no_segura)
                binding.tvMensaje.setTextColor(Color.RED)

            }
            nCharacters in 8..10 -> {
                binding.etPassword.setText(password)
                binding.tvMensaje.text = getString(R.string.aun_mejorar)
                binding.tvMensaje.setTextColor(Color.parseColor("#ffb845"))
            }
            nCharacters in 11..14 -> {
                binding.etPassword.setText(password)
                binding.tvMensaje.text = getString(R.string.password_segura)
                binding.tvMensaje.setTextColor(Color.parseColor("#53ff30"))
            }
            nCharacters > 14 -> {
                binding.etPassword.setText(password)
                binding.tvMensaje.text = getString(R.string.password_asegura)
                binding.tvMensaje.setTextColor(Color.GREEN)
                binding.tvMensaje.setTextColor(Color.parseColor("#53ff30"))
            }
        }
    }

    private fun checkCheckbox(binding: ActivityMainBinding) {
        binding.cbDigitos.setOnClickListener {
            if(binding.cbDigitos.isChecked){
                binding.cbLetras.isEnabled = true
                binding.cbSimbolos.isEnabled = true
            }else{
                if(!binding.cbLetras.isChecked && binding.cbSimbolos.isChecked){
                    binding.cbSimbolos.isEnabled = false
                }else if(binding.cbLetras.isChecked && !binding.cbSimbolos.isChecked){
                    binding.cbLetras.isEnabled = false
                }
            }
        }
        binding.cbLetras.setOnClickListener {
            if(binding.cbLetras.isChecked){
                binding.cbDigitos.isEnabled = true
                binding.cbSimbolos.isEnabled = true
            }else{
                if(!binding.cbDigitos.isChecked && binding.cbSimbolos.isChecked){
                    binding.cbSimbolos.isEnabled = false
                }else if(binding.cbDigitos.isChecked && !binding.cbSimbolos.isChecked){
                    binding.cbDigitos.isEnabled = false
                }
            }
        }
        binding.cbSimbolos.setOnClickListener {
            if(binding.cbSimbolos.isChecked){
                binding.cbLetras.isEnabled = true
                binding.cbDigitos.isEnabled = true
            }else{
                if(!binding.cbLetras.isChecked && binding.cbDigitos.isChecked){
                    binding.cbDigitos.isEnabled = false
                }else if(binding.cbLetras.isChecked && !binding.cbDigitos.isChecked){
                    binding.cbLetras.isEnabled = false
                }
            }
        }
    }
}