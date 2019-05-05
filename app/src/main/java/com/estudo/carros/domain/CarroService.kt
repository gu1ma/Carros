package com.estudo.carros.domain

import com.estudo.carros.utils.*


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

            if(response.isOk())
            {
                //se removeu corretamente do web service, removemos tb do banco de dados interno
                val dao = DatabaseManager.getCarroDAO();
                dao.delete(carro);
            }

            return response
        }

    }
}