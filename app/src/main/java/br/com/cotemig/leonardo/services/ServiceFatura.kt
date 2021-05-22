package br.com.cotemig.leonardo.services

import br.com.cotemig.leonardo.models.Fatura
import retrofit2.Call
import retrofit2.http.GET

interface ServiceFatura {
    @GET("fatura")
    fun getFatura(): Call<Fatura>
}