package com.estudo.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.estudo.carros.fragments.CarrosFragment
import com.estudo.carros.utils.TipoCarro

class TabsAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm){

    //Retorna tipo pela posição
    private fun getTipoCarro(position: Int) = when(position){
        0 -> TipoCarro.Classicos
        1 -> TipoCarro.Esportivos
        else -> TipoCarro.Luxo
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val tipo = getTipoCarro(position)

        return context.getString(tipo.string)
    }

    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        val arguments = Bundle()
        arguments.putSerializable("tipo", tipo)
        f.arguments = arguments

        return f
    }

    override fun getCount(): Int {
        return 3
    }
}