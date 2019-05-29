package com.example.subject0513


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_second, container, false)

        val myWebView = view?.findViewById<WebView>(R.id.webView)

        val setting = myWebView?.settings

        setting?.javaScriptEnabled = true
        setting?.domStorageEnabled = true

        myWebView?.webViewClient = WebViewClient()

        myWebView?.loadUrl("https://www.naver.com")
        return view
    }


}
