package com.mommydndn.app.ui.deprecated.write

/*
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
 */
