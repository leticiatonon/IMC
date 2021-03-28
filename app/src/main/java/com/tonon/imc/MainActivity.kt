package com.tonon.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tonon.imc.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bt_calcular = binding.calcular
        val mensagem = binding.mensagem


        bt_calcular.setOnClickListener {

            val peso = binding.peso.text.toString()
            val altura = binding.altura.text.toString()

            if(peso.isEmpty()){
                mensagem.setText("Informe o seu peso")
            }else if(altura.isEmpty()){
                mensagem.setText("Informe a sua altura")
            }else{
                calculodeIMC()
            }
        }
    }

    private fun calculodeIMC(){

        val pesoID = binding.peso
        val alturaID = binding.altura

        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val resultado = binding.mensagem
        val imc = peso / (altura * altura)

        val mensagem = when{
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau I)"
            imc <= 39.9 -> "Obesidade Severa (Grau II)"
            else -> "Obedidade Mórbida (Grau III)"
        }

        imc.toString()
        resultado.setText("IMC: $imc \n $mensagem")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset ->{

                val limparPeso = binding.peso
                val limparAltura = binding.altura
                val limparMensagem = binding.mensagem

                limparPeso.setText("")
                limparAltura.setText("")
                limparMensagem.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}