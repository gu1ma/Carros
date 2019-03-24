package com.estudo.carros.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Carro: Serializable {
    var id:Long = 0
    var tipo = "";
    var nome = "";
    var desc = "";
    var urlFoto = "";
    var urlInfo = "";
    var urlVideo = "";
    var latitude = "";
    var longitude = "";

    override fun toString(): String {
        return "Carro($nome)"
    }


}