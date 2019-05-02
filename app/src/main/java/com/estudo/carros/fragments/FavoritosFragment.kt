package com.estudo.carros.fragments

import android.support.annotation.UiThread
import com.estudo.carros.activity.CarroActivity
import com.estudo.carros.adapter.CarroAdapter
import com.estudo.carros.domain.Carro
import com.estudo.carros.domain.FavoritosService
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class FavoritosFragment: CarrosFragment(){
    override fun taskCarros(){
        doAsync {
            //Busca carros
            carros = FavoritosService.getCarros()

            uiThread{
                recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }
            }
        }
    }

    fun onClickCarro(carro: Carro){
        activity?.startActivity<CarroActivity>("carro" to carro)
    }
}