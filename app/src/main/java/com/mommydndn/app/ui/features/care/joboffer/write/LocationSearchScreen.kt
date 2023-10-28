package com.mommydndn.app.ui.features.care.joboffer.write

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.net.http.SslError
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.mommydndn.app.data.model.map.EmdItem
import org.json.JSONObject


@Composable
fun LocationSearchScreen(
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
                    addJavascriptInterface(MyJavaScriptInterface(
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
        val roadAddress = jsonObject.optString("roadAddress", "")
        val jibunAddress = jsonObject.optString("jibunAddress", "")
        val zonecode = jsonObject.optString("zonecode", "")

        onReceivedData(jibunAddress)
    }
}