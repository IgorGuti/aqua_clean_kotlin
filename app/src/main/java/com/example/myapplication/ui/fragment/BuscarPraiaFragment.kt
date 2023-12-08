package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Estado
import com.example.myapplication.model.Praia
import com.example.myapplication.ui.recyclerviewadapter.BuscarAdapter
import com.example.myapplication.repository.BuscarList
import com.example.myapplication.ui.recyclerviewadapter.EstadoAdapter

class BuscarPraiaFragment : Fragment(), BuscarAdapter.OnItemClickListener, EstadoAdapter.OnItemClickListener {

    private lateinit var editTextPesquisa: EditText
    private lateinit var recyclerViewPraias: RecyclerView
    private lateinit var recyclerViewEstados: RecyclerView
    private lateinit var adapterPraias: BuscarAdapter
    private lateinit var adapterEstados: EstadoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_buscar_praia, container, false)

        recyclerViewPraias = view.findViewById(R.id.lista_buscar_praia_recyclerview)
        recyclerViewEstados = view.findViewById(R.id.listUF)
        editTextPesquisa = view.findViewById(R.id.pesquisar)

        val listaDePraiasOriginal = BuscarList.listaDePraiasSantaCatarina
        val listaDeEstados = BuscarList.listaDeEstados

        // Configurar o adaptador para as praias
        adapterPraias = BuscarAdapter(requireContext(), listaDePraiasOriginal.toMutableList(), this)
        recyclerViewPraias.adapter = adapterPraias
        recyclerViewPraias.layoutManager = LinearLayoutManager(requireContext())

        // Configurar o adaptador para os estados
        adapterEstados = EstadoAdapter(requireContext(), listaDeEstados, this)
        recyclerViewEstados.adapter = adapterEstados
        recyclerViewEstados.layoutManager = LinearLayoutManager(requireContext())

        recyclerViewPraias.visibility = View.GONE

        editTextPesquisa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Executa antes de qualquer alteração no texto
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Executa quando o texto está sendo alterado
            }

            override fun afterTextChanged(editable: Editable?) {
                adapterPraias.atualizaListaComTextoAtual(listaDePraiasOriginal, editTextPesquisa.text.toString())
                ajustarVisibilidadeLista()
            }
        })

        return view
    }

    private fun ajustarVisibilidadeLista() {
        recyclerViewPraias.visibility = if (editTextPesquisa.text.isNotEmpty()) View.VISIBLE else View.GONE
        recyclerViewEstados.visibility = if (editTextPesquisa.text.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onItemClick(praia: Praia) {
        val navController = findNavController()
        val bundle = Bundle().apply {
            putString(VisualizarPraiaFragment.ARG_PRAIA_PESQUISAR, praia.pesquisar)
        }
        navController.navigate(R.id.nav_verPraia, bundle)
    }

    override fun onItemClick(estado: Estado) {
        // Lógica para lidar com o clique em um estado
        // Determine a ação específica e carregue a lista correspondente
        when (estado.acao) {
            "carregar_lista_santa_catarina" -> {
                val listaPraias = BuscarList.listaDePraiasSantaCatarina
                adapterPraias.atualizaListaComTextoAtual(listaPraias, editTextPesquisa.text.toString())
            }
            "carregar_lista_sao_paulo" -> {
                val listaPraias = BuscarList.listaDePraiasSaoPaulo
                adapterPraias.atualizaListaComTextoAtual(listaPraias, editTextPesquisa.text.toString())
            }
            "carregar_lista_bahia" -> {
                // Implemente a lógica para carregar a lista correspondente a Bahia
            }
            // Adicione mais casos conforme necessário
        }

        // Notificar o adaptador de estados sobre as alterações
        adapterEstados.notifyDataSetChanged()
        ajustarVisibilidadeLista()
    }

}