package com.example.myapplication.repository
import com.example.myapplication.model.Estado
import com.example.myapplication.model.Praia


class BuscarList {
    companion object {
        val listaDeEstados: List<Estado> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Estado("TODOS","carregar__todas_as_listas"),
                Estado("SANTA CATARINA","carregar_lista_santa_catarina"),
                Estado("SÃO PAULO","carregar_lista_sao_paulo"),
                Estado("BAHIA","carregar_lista_bahia"),

                // Adicione mais itens conforme necessário
            )
        }
        val listaDePraiasSantaCatarina: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("ARROIO DA PRAIA DAS GAIVOTAS"),
                Praia("CANAL DO LINGUADO"),
                Praia("LAGOA BRAVA"),
                Praia("LAGOA DA CONCEIÇÃO"),
                Praia("LAGOA DA FERRUGEM"),
                Praia("LAGOA DE BARRA VELHA"),
                Praia("LAGOA DO FAXINAL"),
                Praia("LAGOA DO PERI"),
                Praia("LAGOA DOS FREITAS"),
                Praia("PRAIA DA ARMAÇÃO DO PÂNTANO DO SUL"),
                Praia("PRAIA DA BARRA DA LAGOA"),
                Praia("PRAIA DA BARRA DO SUL"),
                Praia("PRAIA DA BARRA VELHA"),
                Praia("PRAIA DA BASE AÉREA"),
                Praia("PRAIA DA BEIRA MAR NORTE"),
                Praia("PRAIA DA CACHOEIRA DO BOM JESUS"),
                Praia("PRAIA DA CAIACANGAÇÚ"),
                Praia("PRAIA DA CAIEIRA"),
                Praia("PRAIA DA DANIELA"),
                Praia("PRAIA DA JOAQUINA"),
                Praia("PRAIA DA LAGOINHA"),
                Praia("PRAIA DA SAUDADE"),
                Praia("PRAIA DA SOLIDÃO"),
                Praia("PRAIA DA TAPERA"),
                Praia("PRAIA DA VIGIA"),
                Praia("PRAIA DAS GAIVOTAS"),
                Praia("PRAIA DAS PALMEIRAS"),
                Praia("PRAIA DE BOMBAS"),
                Praia("PRAIA DE BOMBINHAS"),
                Praia("PRAIA DE CANASVIEIRAS"),
                Praia("PRAIA DE GAROPABA"),
                Praia("PRAIA DE JERERE"),
                Praia("PRAIA DE JURERE INTERNACIONA"),
                Praia("PRAIA DE LARANJEIRAS"),
                Praia("PRAIA DE MOÇAMBIQUE"),
                Praia("PRAIA DE PIÇARRAS"),
                Praia("PRAIA DE PONTA DAS CANAS"),
                Praia("PRAIA DE SAMBAQUI"),
                Praia("PRAIA DE SANTO ANTÔNIO DE LISBOA"),
                Praia("PRAIA DE SÃO MIGUEL"),
                Praia("PRAIA DE TAQUARAS"),
                Praia("PRAIA DE ZIMBROS"),
                Praia("PRAIA DO ARROIO DO SILVA"),
                Praia("PRAIA DO BALNEÁRIO"),
                Praia("PRAIA DO BALNEÁRIO CAMBORIÚ"),
                Praia("PRAIA DO BOM ABRIGO"),
                Praia("PRAIA DO CACUPÉ"),
                Praia("PRAIA DO CANTO GRANDE"),
                Praia("PRAIA DO CERRO"),
                Praia("PRAIA DO ESTALEIRINHO"),
                Praia("PRAIA DO ESTALEIRO"),
                Praia("PRAIA DO FORTE"),
                Praia("PRAIA DO ITAGUAÇU"),
                Praia("PRAIA DO JARDIM ATLÂNTICO"),
                Praia("PRAIA DO JOSÉ MENDES"),
                Praia("PRAIA DO MARISCAL"),
                Praia("PRAIA DO MATADOURO"),
                Praia("PRAIA DO MEIO"),
                Praia("PRAIA DO MOCAMBIQUE"),
                Praia("PRAIA DO MORRO DAS PEDRAS"),
                Praia("PRAIA DO MORRO DOS CONVENTOS"),
                Praia("PRAIA DO PANTANO DO SUL"),
                Praia("PRAIA DO RIBEIRÃO DA ILHA"),
                Praia("PRAIA DO RINCÃO"),
                Praia("PRAIA DO SANTINHO"),
                Praia("PRAIA DO SIRIÚ"),
                Praia("PRAIA DOS INGLESES"),
                Praia("PRAIA MOLE"),
                // Adicione mais itens conforme necessário
            )
        }

        val listaDePraiasSaoPaulo: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("teste5"),
                Praia("teste4"),
                Praia("teste3"),
                Praia("teste2"),
                Praia("teste"),
                // Adicione mais itens conforme necessário
            )
        }
    }
}