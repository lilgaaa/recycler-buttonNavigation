package com.example.sqlitepe

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotasAdapter(private var notes: List<Nota>, private val context: Context) : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contenidoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NotaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val note = notes[position]
        holder.linkTextView.text = note.link
        holder.contentTextView.text = note.contenido

        // Configura el link para que sea clicable
        holder.linkTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(note.link))
            // Verifica que hay una aplicación que pueda manejar el intent
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }

    fun refreshData(newNotes: List<Nota>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
