package com.estudo.carros.domain

import android.content.Context
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.estudo.carros.R
import com.estudo.carros.utils.*
import com.estudo.carros.domain.Response
import org.json.JSONArray


class CarroService {

    companion object {
        //
        private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

        //Busca carro por tipo
        fun getCarros(tipo: TipoCarro): List<Carro>{
            //
            val url = "$BASE_URL/tipo/${tipo.name}"
            //
            val json = HttpHelper.get(url)
            return fromJson(json)
        }

        fun save(carro: Carro): Response{
            //post do JSON carro
            val json = HttpHelper.post(BASE_URL, carro.toJson())
            //leitura da resposta
            val response = fromJson<Response>(json)

            return response
        }

        //deletar carro
        fun delete(carro: Carro): Response{
            val url = "$BASE_URL/${carro.id}"
            var json = HttpHelper.delete(url)
            val response = fromJson<Response>(json)

            return response
        }

    }
}