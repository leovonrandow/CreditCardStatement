package br.com.cotemig.leonardo.models

class Fatura {
    var limiteDisponivel: Double = 0.0
    var valor: Double = 0.0
    var vencimento: String = ""
    var despesas: List<Despesa> = emptyList()
}