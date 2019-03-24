package com.estudo.carros.activity

import android.app.ActionBar
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.estudo.carros.R
import kotlinx.android.synthetic.main.fragment_carros.*

class SiteLivroActivity : BaseActivity(){
    private val URL_SOBRE : String = "http:www.livroandroid.com.br/sobre.htm";
    var webView: WebView? = null;
    var progress: ProgressBar? = null;
    var swipeRefresh: SwipeRefreshLayout? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_site_livro);
        //Toolbar
        val actionBar = setUpToolbar(R.id.toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Views
        webView = findViewById(R.id.webview);
        progress = findViewById(R.id.progress);

        //Carrega a pagina
        setWebViewClient(webView);
        webView?.loadUrl(URL_SOBRE);

        //Swipe to refresh
        swipeRefresh = findViewById(R.id.swipeToRefresh);
        swipeRefresh?.setOnRefreshListener { webView?.reload() }

        swipeRefresh?.setColorSchemeResources(
            R.color.refresh_progress_1,
            R.color.refresh_progress_2,
            R.color.refresh_progress_3
        )

    }

    //Controla o evento do WebView
    private fun setWebViewClient(webView: WebView?){
        webView?.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //liga a barra de progresso
                progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //desliga a barra de progresso
                progress?.visibility = View.INVISIBLE
                swipeRefresh?.isRefreshing = false;
            }
        }
    }

}