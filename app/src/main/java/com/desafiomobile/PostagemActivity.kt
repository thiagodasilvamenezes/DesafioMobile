package com.desafiomobile

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiomobile.api.PostagemAPI
import com.desafiomobile.api.RetrofitHelper
import com.desafiomobile.databinding.ActivityPostagemBinding
import com.desafiomobile.model.Postagem
import com.desafiomobile.model.PostagemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


private lateinit var binding: ActivityPostagemBinding

private val retrofit by lazy {
    RetrofitHelper.retrofit
}
class PostagemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostagemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            recuperarPostagens()
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
                    binding.postagemRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@PostagemActivity)
                        adapter = PostagemAdapter(listaPostagens ?: emptyList())
                    }
                }
            }
        }
    }

}
