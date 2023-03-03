package com.gvstang.greetingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtName: EditText
    private lateinit var edtCity: EditText
    private lateinit var edtAge: EditText
    private lateinit var btnGreet: Button
    private lateinit var result: TextView
    private lateinit var btnClear: Button

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    private fun errorMessage(field: String) = "Field $field tidak boleh kosong!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edt_name)
        edtCity = findViewById(R.id.edt_city)
        edtAge = findViewById(R.id.edt_age)
        btnGreet = findViewById(R.id.btn_greet)
        result = findViewById(R.id.result)
        btnClear = findViewById(R.id.btn_clear)

        btnGreet.setOnClickListener(this)
        btnClear.setOnClickListener(this)

        if(savedInstanceState != null) {
            val res = savedInstanceState.getString(STATE_RESULT)
            result.text = res
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, result.text.toString())
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_greet) {
            val inputName = edtName.text.toString().trim()
            val inputCity = edtCity.text.toString().trim()
            val inputAge = edtAge.text.toString().trim()

            var isEmptyFields = false

            if(inputName.isEmpty()) {
                isEmptyFields = true
                edtName.error = errorMessage("nama")
            }

            if(inputCity.isEmpty()) {
                isEmptyFields = true
                edtCity.error = errorMessage("kota")
            }

            if(inputAge.isEmpty()) {
                isEmptyFields = true
                edtAge.error = errorMessage("umur")
            }

            if(!isEmptyFields) {
                val res = "Hai $inputName! Kamu berumur $inputAge tahun dan sekarang tinggal di $inputCity. Nice to meet you, Akmal!"
                result.text = res

                edtName.text.clear()
                edtCity.text.clear()
                edtAge.text.clear()
            }
        }

        if(v?.id == R.id.btn_clear) {
            result.text = getString(R.string.result)
        }
    }
}