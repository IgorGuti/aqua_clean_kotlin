package com.example.myapplication.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentItemBuscarPraiaUfBinding
import com.example.myapplication.model.Estado


class EstadoAdapter(
    private val context: Context,
    private val listaDeEstados: List<Estado>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<EstadoAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(estado: Estado)
    }
    class ViewHolder(private val binding: FragmentItemBuscarPraiaUfBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(estado: Estado) {
            binding.itemBuscarPraiaUF.text = estado.selecionar
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentItemBuscarPraiaUfBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaDeEstados[position]
        holder.vincula(item)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
    }
    override fun getItemCount(): Int = listaDeEstados.size
}
