package com.estudo.carros.utils

import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import kotlin.math.log

object HttpHelper {
    private const val TAG = "http"
    private const val LOG_ON = true
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    var client = OkHttpClient()


    //GET
    fun get(url: String): String{
        //log("HttpHelper.get: $url")
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    //POST com JSON
    fun post(url: String, json: String): String{
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()

        return getJson(request)
    }

    //POST com parâmetros (form-urlencoded)
    fun postForm(url: String, params: Map<String, String>): String{
        val builder = FormBody.Builder()

        for((key, value) in params){
            builder.add(key, value)
        }

        val body = builder.build()

        //faz a request
        val request = Request.Builder().url(url).post(body).build()

        return getJson(request)
    }

    //DELETE
    fun delete(url: String): String{
        val request = Request.Builder().url(url).delete().build()

        return getJson(request)
    }

    //Lê a resposta do servidor em json
    private fun getJson(request: Request): String {
        val response = client.newCall(request).execute()
        val responseBody = response.body()

        if(responseBody != null){
            val json = responseBody.string()
            return json
        }

        throw IOException("Erro ao fazer a requisição")
    }


}