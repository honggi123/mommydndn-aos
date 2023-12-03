package com.mommydndn.app.ui.features.care.deprecated.joboffer.write

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import org.json.JSONObject


@Composable
fun JobOfferLocationSearchScreen(
    navController: NavHostController,
    viewModel: JobOfferWriteViewModel
) {

    val webViewClient = AccompanistWebViewClient()
    val webChromeClient = AccompanistWebChromeClient()
    val webViewState = rememberWebViewState("https://honggi123.github.io/hosting_test/Address.html")
    val webViewNavigator = rememberWebViewNavigator()

    Box {
        WebView(
            state = webViewState,
            navigator = webViewNavigator,
            client = webViewClient,
            chromeClient = webChromeClient,
            onCreated = { webView ->
                with(webView) {
                    settings.run {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        javaScriptCanOpenWindowsAutomatically = false
                    }
                    addJavascriptInterface(
                        MyJavaScriptInterface(
                        onReceivedData = { address ->
                            address?.let {
                                viewModel.searchLocationByAddress(it)
                            }
                            Handler(Looper.getMainLooper()).post {
                                navController.popBackStack()
                            }
                        },
                    ), "Android")
                }
            },
            modifier = Modifier
        )
    }
}


class MyJavaScriptInterface(
    private val onReceivedData: (String?) -> Unit,
) {
    @JavascriptInterface
    fun onReceivedPostMessage(data: String?) {
        val jsonObject = JSONObject(data)
        Log.e("messs",data.toString())
        val roadAddress = jsonObject.optString("roadAddress", "")
        val jibunAddress = jsonObject.optString("jibunAddress", "")
        val zonecode = jsonObject.optString("zonecode", "")

        onReceivedData(jibunAddress)
    }
}