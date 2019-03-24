package com.estudo.carros.adapter

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.support.v7.widget.CardView
import com.estudo.carros.domain.Carro
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.estudo.carros.R
import com.estudo.carros.utils.loadUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_carro.view.*

class CarroAdapter(
    val carros: List<Carro>,
    val onClick: (Carro) -> Unit
    ): RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>()
{
    class CarrosViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun getItemCount(): Int {
        return this.carros.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CarrosViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.adapter_carro, p0, false)
        return CarrosViewHolder(view);
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val context = holder.itemView.context;
        val carro = carros[position];
        var view = holder.itemView
        with(view){
            tNome.text = carro.nome;
            progress.visibility = View.VISIBLE

            // Log.d("url foto", carro.urlFoto.toString())
            img.loadUrl(carro.urlFoto, progress)

            //Adicionando evento de clique na linha
            setOnClickListener( View.OnClickListener { onClick(carro) } )
        }

    }

}