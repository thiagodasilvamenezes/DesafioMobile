package com.desafiomobile.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.desafiomobile.R
import com.squareup.picasso.Picasso

class FotoAdapter(private var fotos: List<Foto>) : RecyclerView.Adapter<FotoAdapter.FotoViewHolder>() {

    fun updateData(newFotos: List<Foto>) {
        this.fotos = newFotos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.foto_item, parent, false)
        return FotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        val foto = fotos[position]
        // Configure o holder com dados da foto
        Picasso.get().load(foto.url).into(holder.imageView)
    }

    override fun getItemCount(): Int = fotos.size

    class FotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewFoto)
    }
}
