package com.desafiomobile.api

import com.desafiomobile.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {

    //https://viacep.com.br/ + ws/01001000/json/
    /* BASE URL: https://viacep.com.br/ +
                                            postagens
                                            salvarpostagem
                                            saldo
                                            transferencia
                                            extrato

     */
    //GET, POST, PUT, PATCH e DELETE
    // https://api.banco.com.br/ + saldo
    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(
        @Path("cep") cep: String
    ) : Response<Endereco>

}