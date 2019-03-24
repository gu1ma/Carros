package com.estudo.carros.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.estudo.carros.R
import com.estudo.carros.activity.CarroActivity
import com.estudo.carros.adapter.CarroAdapter
import com.estudo.carros.domain.Carro
import com.estudo.carros.domain.CarroService
import com.estudo.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.fragment_carros.*
import kotlinx.android.synthetic.main.include_progress.*
import org.jetbrains.anko.startActivity

class CarrosFragment : BaseFragment()
{
    private var tipo = TipoCarro.Classicos;
    private var carros = listOf<Carro>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_carros, container, false)

        //tipo de argumentos
        this.tipo = arguments?.getSerializable("tipo") as TipoCarro

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Views
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }


    override fun onResume() {
        super.onResume()

        taskCarros()
    }

    private fun taskCarros(){
        //Mostra uma janela de progresso
        progress.visibility = View.VISIBLE

        object: Thread(){
            override fun run() {
                //busca carros
                carros = CarroService.getCarros(tipo)

                //atualiza a lista de carros
                activity?.runOnUiThread(Runnable {
                    recyclerView.adapter = CarroAdapter(carros){ onClickCarro(it) }
                    //fecha o dialog
                    progress.visibility = View.INVISIBLE
                })

            }
        }.start()

    }

    private fun onClickCarro(carro: Carro){
        activity?.startActivity<CarroActivity>("carro" to carro)
    }

}