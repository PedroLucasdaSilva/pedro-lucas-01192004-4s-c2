package com.example.avaliacao2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.avaliacao2.data_classes.Cachorros
import com.example.avaliacao2.data_classes.CachorrosConexao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class compra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra)
        var valor1:Int = 0
        val cachorro1 = intent.getStringExtra("idCachorro1")
        val cachorro2 = intent.getStringExtra("idCachorro2")
        val tvCompra: TextView = findViewById(R.id.tv_compra)
        val apiCachorros = CachorrosConexao.criar()
        val etId1: EditText = findViewById(R.id.et_id1)
        val etId2: EditText = findViewById(R.id.et_id2)
        val id1 = etId1.text.toString().toInt()
        val id2 = etId2.text.toString().toInt()
        apiCachorros.get(id1).enqueue(object : Callback<Cachorros> {
            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
               tvCompra.text = "Deu ruim... Nenhum cachorro encontrado o(s) id(s) ${id1}"
            }

            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
                val tvCompra1:TextView = findViewById(R.id.et_id1)
                val cachorros = response.body()
                if (cachorros!= null){
                    valor1 = cachorros.precoMedio
                    tvCompra.text =tvCompra.text.toString() + "Cachorro 1: ${cachorros.precoMedio}\n"
                } else {
                    tvCompra.text = ""
                }
            }
        })
        apiCachorros.get(id2).enqueue(object : Callback<Cachorros> {
            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
                tvCompra.text =tvCompra.text.toString() + "${id2}"
            }

            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
                val tvCompra1:TextView = findViewById(R.id.et_id1)
                val cachorros = response.body()
                val soma:Int
                if (cachorros!=null){

                    soma = cachorros.precoMedio + valor1
                    tvCompra.text = tvCompra.text.toString() + "Cachorro 2: ${cachorros.precoMedio}\n" +
                                                            "Valor Total: ${soma}"
                } else {
                    tvCompra.text = ""
                }
            }
        })
    }
}