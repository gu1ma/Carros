package com.estudo.carros.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.estudo.carros.R
import com.estudo.carros.activity.CarroActivity
import com.estudo.carros.adapter.CarroAdapter
import com.estudo.carros.domain.Carro
import com.estudo.carros.domain.CarroService
import com.estudo.carros.utils.SystemUtils
import com.estudo.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.fragment_carros.*
import kotlinx.android.synthetic.main.include_progress.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskCarros()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun taskCarros(){

        if(!SystemUtils.isNetworkAvaiable(context!!)){
            //Por enquanto mostramos somente um toask, porem futuramente irei criar uma tela para este caso
            Toast.makeText(context, "Sem conexão com a internet no momento...", Toast.LENGTH_LONG).show()
        }
        else{
            //Mostra uma janela de progresso
            progress.visibility = View.VISIBLE

            doAsync {
                //Busca carros
                carros = CarroService.getCarros(tipo)

                uiThread {
                    //atualiza a lista de carros
                    recyclerView.adapter = CarroAdapter(carros){ onClickCarro(it) }
                    //fecha o progress
                    progress.visibility = View.INVISIBLE
                }

            }
        }

    }

    private fun onClickCarro(carro: Carro){
        activity?.startActivity<CarroActivity>("carro" to carro)
    }

}