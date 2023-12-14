package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Estado
import com.example.myapplication.model.Praia
import com.example.myapplication.repository.BuscarList
import com.example.myapplication.ui.recyclerviewadapter.BuscarAdapter
import com.example.myapplication.ui.recyclerviewadapter.ListaUFAdapter

class BuscarPraiaFragment : Fragment(), BuscarAdapter.OnItemClickListener {

    private lateinit var editTextPesquisa: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BuscarAdapter
    private lateinit var listaUFRecyclerView: RecyclerView
    private lateinit var meuBotao: Button
    private var estadoSelecionado: Estado? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Infla o layout do fragmento de busca de praia
        val view = inflater.inflate(R.layout.fragment_buscar_praia, container, false)

        // Inicialize a referência ao botão
        meuBotao = view.findViewById(R.id.ListUF) // Substitua pelo ID correto do seu botão

        // Inicializa os componentes da UI
        recyclerView = view.findViewById(R.id.lista_buscar_praia_recyclerview)
        editTextPesquisa = view.findViewById(R.id.pesquisar)
        listaUFRecyclerView = view.findViewById(R.id.lista_uf_recyclerview)

        // Configura o clique no botão de lista de UF
        val listaUFButton: Button = view.findViewById(R.id.ListUF)
        listaUFButton.setOnClickListener {
            // Chama o método para carregar a lista de estados
            carregarListaEstados()

            // Ajusta a visibilidade do RecyclerView para VISIBLE
            if (listaUFRecyclerView.visibility == View.VISIBLE) {
                listaUFRecyclerView.visibility = View.GONE
            } else {
                listaUFRecyclerView.visibility = View.VISIBLE
            }
        }
        // Obtém a lista de praias da classe BuscarList
        val listaDePraiasOriginal = BuscarList.listaDePraiasSaoPaulo

        // Criando e configurando o adaptador
        adapter = BuscarAdapter(requireContext(), listaDePraiasOriginal.toMutableList(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Ajusta a visibilidade inicial do RecyclerView para GONE
        recyclerView.visibility = View.GONE

        // Adiciona um TextWatcher para monitorar as alterações no EditText
        editTextPesquisa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Executa antes de qualquer alteração no texto
            }
            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // Executa quando o texto está sendo alterado
            }
            override fun afterTextChanged(editable: Editable?) {
                // Após o texto ser alterado, atualiza a lista de praias com base no texto atual
                val listaDePraias = estadoSelecionado?.let {
                    obterListaDePraiasComBaseNoEstadoClicado(it)
                } ?: BuscarList.listaDePraiasSaoPaulo

                adapter.atualizaListaComTextoAtual(listaDePraias, editTextPesquisa.text.toString())

                // Ajusta a visibilidade do RecyclerView com base no texto na caixa de pesquisa
                ajustarVisibilidadeLista()
            }
        })
        return view
    }

    // Método para ajustar a visibilidade do RecyclerView com base no texto na caixa de pesquisa
    private fun ajustarVisibilidadeLista() {
        recyclerView.visibility = if (editTextPesquisa.text.isNotEmpty()) View.VISIBLE else View.GONE
    }

    // Lidar com o clique do item
    override fun onItemClick(praia: Praia) {
        val navController = findNavController()

        // Certifique-se de que estadoSelecionado não é nulo antes de navegar
        estadoSelecionado?.let {
            // Criando um bundle para enviar dados para o próximo fragmento
            val bundle = Bundle().apply {
                putString(VisualizarPraiaFragment.ARG_PRAIA_PESQUISAR, praia.pesquisar)
                putString(VisualizarPraiaFragment.ARG_ESTADO_SELECIONADO, it.nome)
            }

            // Navegando para o fragmento VisualizarPraiaFragment com o bundle
            navController.navigate(R.id.nav_verPraia, bundle)
        } ?: run {
            // Trate o caso em que o estadoSelecionado é nulo (se necessário)
            // Por exemplo, exibindo uma mensagem de erro
            Toast.makeText(
                requireContext(),
                "Selecione um estado antes de visualizar a praia.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    // Método para carregar a lista de estados
    private fun carregarListaEstados() {
        // Obtém a lista de estados da classe BuscarList
        val listaEstados = BuscarList.listaDeEstados

        // Certifique-se de inicializar listaUFRecyclerView apenas se ainda não foi inicializado
        if (!::listaUFRecyclerView.isInitialized) {
            listaUFRecyclerView = view?.findViewById(R.id.lista_uf_recyclerview) ?: return

            // Adicione um layout manager ao RecyclerView
            listaUFRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        // Atualiza o adaptador do RecyclerView com a nova lista
        val adapterEstados = ListaUFAdapter(requireContext(), listaEstados, this)
        listaUFRecyclerView.adapter = adapterEstados
    }
    // Implementação do método da interface OnEstadoItemClickListener
    override fun onEstadoItemClick(estado: Estado?) {
        estado?.let {
            // Adicione a lógica desejada ao clicar em um estado na lista
            Toast.makeText(
                requireContext(),
                "Estado selecionado: ${estado.nome}",
                Toast.LENGTH_SHORT
            ).show()

            // Atualiza o estado selecionado
            estadoSelecionado = estado

            // Obtém a lista de praias correspondente ao estado clicado
            val listaDePraias = obterListaDePraiasComBaseNoEstadoClicado(estado)

            // Atualiza a lista de praias no adaptador do RecyclerView
            adapter.atualizaListaComTextoAtual(listaDePraias, editTextPesquisa.text.toString())

            // Atualiza o texto do botão com o nome do estado selecionado
            meuBotao.text = estado.nome

            // Esconde o RecyclerView de estados
            listaUFRecyclerView.visibility = View.GONE
        }
    }
    // Método auxiliar para obter a lista de praias com base no estado clicado
    private fun obterListaDePraiasComBaseNoEstadoClicado(estado: Estado): List<Praia> {
        // Lógica para obter a lista de praias com base no estado clicado
        // Substitua isso com a lógica específica do seu aplicativo
        return when (estado.nome) {
            "MARANHÃO" -> BuscarList.listaDePraiasMaranhao
            "PIAUÍ" -> BuscarList.listaDePraiasPiaui
            "CEARÁ" -> BuscarList.listaDePraiasCeara
            "RIO GRANDE DO NORTE" -> BuscarList.listaDePraiasRioGrandeDoNorte
            "PARAÍBA" -> BuscarList.listaDePraiasParnaiba
            "PERNAMBUCO" -> BuscarList.listaDePraiasPernambuco
            "ALAGOAS" -> BuscarList.listaDePraiasAlagoas
            "SERGIPE" -> BuscarList.listaDePraiasSergipe
            "BAHIA" -> BuscarList.listaDePraiasBahia
            "ESPÍRITO SANTO" -> BuscarList.listaDePraiasEspiritoSanto
            "RIO DE JANEIRO" -> BuscarList.listaDePraiasRioDeJaneiro
            "SÃO PAULO" -> BuscarList.listaDePraiasSaoPaulo
            "PARANÁ" -> BuscarList.listaDePraiasParana
            "SANTA CATARINA" -> BuscarList.listaDePraiasSantaCatarina
            "RIO GRANDE DO SUL" -> BuscarList.listaDePraiasRioGrandeDoSul
            else -> emptyList() // Retorne uma lista vazia se o estado não for reconhecido
        }
    }
}
