package com.desafiomobile

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.desafiomobile.api.PostagemAPI
import com.desafiomobile.api.RetrofitHelper
import com.desafiomobile.databinding.ActivityFotoBinding
import com.desafiomobile.model.FotoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFotoBinding
    private lateinit var fotoAdapter: FotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fotoAdapter = FotoAdapter(listOf())
        binding.fotoRecyclerView.adapter = fotoAdapter

        CoroutineScope(Dispatchers.IO).launch {
            recuperarFotos()
        }


        binding.voltarButton.setOnClickListener {
            finish()
        }

    }

    private suspend fun recuperarFotos() {
        val response = RetrofitHelper.retrofit.create(PostagemAPI::class.java).recuperarFotos()
        if (response.isSuccessful) {
            val fotos = response.body() ?: emptyList()
            withContext(Dispatchers.Main) {
                fotoAdapter.updateData(fotos) // Atualiza o adapter com a lista de fotos
            }
        } else {
            Log.e("FotoActivity", "Falha ao recuperar fotos: ${response.code()}")
        }
    }
}
