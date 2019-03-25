package com.estudo.carros.domain

import android.content.Context
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.estudo.carros.R
import com.estudo.carros.utils.HttpHelper
import com.estudo.carros.utils.SystemUtils
import com.estudo.carros.utils.TipoCarro
import com.estudo.carros.utils.fromJson
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

    }
}