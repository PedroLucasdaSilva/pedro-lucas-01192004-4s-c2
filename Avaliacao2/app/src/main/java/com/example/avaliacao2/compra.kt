package com.example.avaliacao2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
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
        val cachorro1 = intent.getIntExtra("idCachorro1",0)
        val cachorro2 = intent.getIntExtra("idCachorro2",0)


        var valor1:Int = 0
        var erro:String ="Deu ruim... Nenhum cachorro encontrado o(s) id(s) "
        val tvCompra: TextView = findViewById(R.id.tv_compra)
        val foto: ImageView = findViewById(R.id.iv_triste)

        val apiCachorros = CachorrosConexao.criar()

        val id1 = cachorro1.toString().toInt()
        val id2 = cachorro2.toString().toInt()

        apiCachorros.get(id1).enqueue(object : Callback<Cachorros> {
            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
                tvCompra.text = "Deu ruim... Nenhum cachorro encontrado o(s) id(s) ${id1}"
            }

            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
                val cachorros = response.body()
                if (cachorros!=null){
                    valor1 = cachorros.precoMedio
                    tvCompra.text =  tvCompra.text.toString()+ "Cachorro 1: ${cachorros.precoMedio}\n"
                } else {
                    
                    erro = erro.toString() + "${id1}"
                    tvCompra.text = erro +"\n"
                }
            }
        })
        apiCachorros.get(id2).enqueue(object : Callback<Cachorros> {
            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
                tvCompra.text = tvCompra.text.toString() + "${id2}"
            }

            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
                val cachorros = response.body()
                val soma:Int
                if (cachorros!=null){

                    soma = cachorros.precoMedio + valor1
                    tvCompra.text = tvCompra.text.toString() +  "Cachorro 2: ${cachorros.precoMedio}\n" +
                            "Valor Total: ${soma}\n"


                } else {
                    erro = erro.toString() + "${id2}"
                    tvCompra.text = tvCompra.text.toString() + erro + "\n"
                }
            }
        })
    }
}