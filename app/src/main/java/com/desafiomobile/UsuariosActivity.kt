package com.desafiomobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiomobile.databinding.ActivityUsuariosBinding
import com.desafiomobile.model.UsuariosAdapter
import com.desafiomobile.model.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsuariosBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        binding.voltarButton.setOnClickListener {
            finish()
        }

        CoroutineScope(Dispatchers.IO).launch {
            val usuarios = db.patApelidoDao().obterTodos()
            withContext(Dispatchers.Main) {
                val usuariosAdapter = UsuariosAdapter(usuarios)
                binding.usuariosRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@UsuariosActivity)
                    adapter = usuariosAdapter
                }
            }
        }
    }
}