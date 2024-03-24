package com.desafiomobile.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.desafiomobile.R
import com.desafiomobile.model.entity.PatApelido

class UsuariosAdapter(private val usuarios: List<PatApelido>) : RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder>() {

    class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val apelidoTextView: TextView = view.findViewById(R.id.apelidoTextView)
        val patTextView: TextView = view.findViewById(R.id.patTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.usuario_item, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.apelidoTextView.text = usuario.apelido
        holder.patTextView.text = usuario.pat.toString()
    }

    override fun getItemCount() = usuarios.size
}
