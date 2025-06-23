package com.example.agendamentoconsultamedica

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var btnData: Button
    private lateinit var btnHora: Button
    private lateinit var btnAgendar: Button

    private var ano = 0
    private var mes = 0
    private var dia = 0
    private var hora = 0
    private var minuto = 0
    private var dataSelecionada = false
    private var horaSelecionada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNome = findViewById(R.id.etNome)
        btnData = findViewById(R.id.btnData)
        btnHora = findViewById(R.id.btnHora)
        btnAgendar = findViewById(R.id.btnAgendar)

        btnData.setOnClickListener {
            val c = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                ano = year
                mes = month
                dia = dayOfMonth
                btnData.text = "$dia/${mes + 1}/$ano"
                dataSelecionada = true
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        btnHora.setOnClickListener {
            val c = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                hora = hourOfDay
                minuto = minute
                btnHora.text = String.format("%02d:%02d", hora, minuto)
                horaSelecionada = true
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
            timePicker.show()
        }

        btnAgendar.setOnClickListener {
            val nome = etNome.text.toString().trim()

            if (nome.isEmpty()) {
                Toast.makeText(this, "Informe o nome do paciente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!dataSelecionada) {
                Toast.makeText(this, "Selecione a data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!horaSelecionada) {
                Toast.makeText(this, "Selecione o horário", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mensagem = "Consulta agendada para $nome em $dia/${mes + 1}/$ano às ${String.format("%02d:%02d", hora, minuto)}"
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()

            etNome.text.clear()
            btnData.text = "Selecionar Data"
            btnHora.text = "Selecionar Horário"
            dataSelecionada = false
            horaSelecionada = false
        }
    }
}
