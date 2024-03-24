package com.desafiomobile.model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.desafiomobile.R
import com.desafiomobile.model.Postagem

class PostagemAdapter(private val postagens: List<Postagem>) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>() {

    class PostagemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvId)
        val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_postagem, parent, false)
        return PostagemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = postagens[position]
        holder.tvId.text = postagem.id.toString()
        holder.tvTitulo.text = postagem.title


    }

    override fun getItemCount() = postagens.size
}

