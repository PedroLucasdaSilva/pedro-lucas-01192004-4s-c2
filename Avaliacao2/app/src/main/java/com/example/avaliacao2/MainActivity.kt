package com.example.avaliacao2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.avaliacao2.data_classes.Cachorros
import com.example.avaliacao2.data_classes.CachorrosConexao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var preferencias: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Não perder a compra dos cachorros
        preferencias = getSharedPreferences("autenticação",MODE_PRIVATE)



        val compraAnterior1= preferencias.getInt("idCachorro1",0)
        val compraAnterior2 = preferencias.getInt("idCachorro2",0)
        if (compraAnterior1!=0 || compraAnterior2!=0){
            val compra = Intent(this, compra::class.java)
            compra.putExtra("idCachorro1",compraAnterior1)
            compra.putExtra("idCachorro2",compraAnterior2)
            startActivity(compra)
        }
    }

    fun comprarCachorros(view: View) {
        val etCachorro1: EditText = findViewById(R.id.et_id1)
        val etCachorro2: EditText = findViewById(R.id.et_id2)
        val edit = preferencias.edit()
        edit.putInt("idCachorro1",etCachorro1.text.toString().toInt())
        edit.putInt("idCachorro2",etCachorro2.text.toString().toInt())
        edit.commit()

//        var valor1:Int = 0
//        var erro:String ="Deu ruim... Nenhum cachorro encontrado o(s) id(s) "
//        val tvCompra: TextView = findViewById(R.id.tv_compra)
//
//        val apiCachorros = CachorrosConexao.criar()
//        val etId1: EditText = findViewById(R.id.et_id1)
//        val etId2: EditText = findViewById(R.id.et_id2)
//        val id1 = etId1.text.toString().toInt()
//        val id2 = etId2.text.toString().toInt()
//
//        apiCachorros.get(id1).enqueue(object : Callback<Cachorros> {
//            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
//               tvCompra.text = "Deu ruim... Nenhum cachorro encontrado o(s) id(s) ${id1}"
//            }
//
//            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
//                val tvCompra1:TextView = findViewById(R.id.et_id1)
//                val cachorros = response.body()
//                if (cachorros!=null){
//                    valor1 = cachorros.precoMedio
//                    tvCompra.text =  "Cachorro 1: ${cachorros.precoMedio}\n"
//                } else {
//                    erro = erro.toString() + "${id1}"
//                    tvCompra.text = erro +"\n"
//                }
//            }
//        })
//        apiCachorros.get(id2).enqueue(object : Callback<Cachorros> {
//            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
//                tvCompra.text = tvCompra.text.toString() + "${id2}"
//            }
//
//            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
//                val tvCompra1:TextView = findViewById(R.id.et_id1)
//                val cachorros = response.body()
//                val soma:Int
//                if (cachorros!=null){
//
//                    soma = cachorros.precoMedio + valor1
//                    tvCompra.text = tvCompra.text.toString() +  "Cachorro 2: ${cachorros.precoMedio}\n" +
//                            "Valor Total: ${soma}\n"
//
//
//                } else {
//                    erro = erro.toString() + "${id2}"
//                    tvCompra.text = tvCompra.text.toString() + erro + "\n"
//                }
//            }
//        })

        val compra = Intent(this, compra::class.java)
        compra.putExtra("idCachorro1",etCachorro1.text.toString().toInt())
        compra.putExtra("idCachorro2",etCachorro2.text.toString().toInt())
        startActivity(compra)
    }
}