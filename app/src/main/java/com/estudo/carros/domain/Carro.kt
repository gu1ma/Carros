package com.estudo.carros.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "carro")
class Carro: Serializable {
    @PrimaryKey
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