package com.desafiomobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiomobile.api.PostagemAPI
import com.desafiomobile.api.RetrofitHelper
import com.desafiomobile.databinding.ActivitySegundaBinding
import com.desafiomobile.model.Postagem
import com.desafiomobile.model.PostagemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SegundaActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySegundaBinding
    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura a Toolbar
     //   setSupportActionBar(binding.toolbarLayout.toolbarprincipal) // Aqui você precisa ajustar para o ID correto da sua Toolbar

        // Habilita o botão de voltar na Toolbar Configura a ação para o botão de voltar
    //    supportActionBar?.setDisplayHomeAsUpEnabled(true)
     //   supportActionBar?.setDisplayShowHomeEnabled(true)

        // Configura a ação para o botão de voltar
/*        binding.toolbarLayout.toolbarprincipal.setNavigationOnClickListener {
            onBackPressed() // Isso fará com que a Activity feche e retorne à anterior
        }*/
        CoroutineScope(Dispatchers.IO).launch {
            //recuperarEndereco()
            recuperarPostagens()
            //recuperarPostagemUnica()
            //recuperarComentariosParaPostagem()
            //salvarPostagem()
            //atualizarPostagem()
            //removerPostagem()
            //recuperarFotoUnica()
        }
        binding.voltarButton.setOnClickListener {
            finish()
        }

    }

    private suspend fun recuperarPostagens() {
        var retorno: Response<List<Postagem>>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagens()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val listaPostagens = retorno.body()
                listaPostagens?.forEach { postagem ->
                    val id = postagem.id
                    val title = postagem.title
                    Log.i("info_jsonplace", "$id - $title")
                }

                // Atualiza a UI na Main Thread
                withContext(Dispatchers.Main) {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@SegundaActivity)
                        adapter = PostagemAdapter(listaPostagens ?: emptyList())
                    }
                }
            }
        }
    }

}