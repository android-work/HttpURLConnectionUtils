package com.workhard.httpurlconnection

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.time.measureTimedValue

/**
 * 创建时间：2019/12/31
 *
 * 作者：Mr.Liu
 *
 * 描述：网络请求类
 **/
object HttpUrlConnectionUtil {

    lateinit var urlConnection: HttpURLConnection
    private var baseUrl:String = ""

    /**
     * 设置baseUrl
     * */
    fun setBaseUrl(base:String){
        baseUrl = base
    }

    /**
     * 设置请求头
     * */
    fun addHead(headMap:MutableMap<String,String>){

        for (entry in headMap.entries){
            val key = entry.key
            val value = entry.value
            urlConnection.addRequestProperty(key,value)
        }

        urlConnection.connectTimeout = 60000
        urlConnection.readTimeout = 60000
    }

    /**
     * get请求方法
     * */
    fun getMethed(url:String,paramsMap:MutableMap<String,String>?){

        GlobalScope.launch (Dispatchers.Main){
            withContext(Dispatchers.IO){
                quest1(url, paramsMap)
            }
        }

    }

   suspend fun quest1(
        url: String,
        paramsMap: MutableMap<String, String>?
    ) {
        var getUrl = url
        if (paramsMap != null && paramsMap.isNotEmpty()) {

            val sb = StringBuffer("?")
            var i = 0
            for (entry in paramsMap.entries) {
                i++
                val key = entry.key
                val value = entry.value
                sb.append("$key=$value")
                if (i < paramsMap.size) {
                    sb.append("&")
                }
            }
            getUrl = String.format("%s,%s", getUrl, Uri.encode(sb.toString()))
        }

        getUrl = String.format("%s,%s", baseUrl, getUrl)
        LogUtil.loge("getUrl:$getUrl")

        urlConnection = URL(getUrl).openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        if (urlConnection.responseCode == 200) {
            val inputStream: InputStream? = urlConnection.inputStream
            val getBody = StreamUtils.stream2Str(inputStream ?: return)
            LogUtil.loge("getBody:$getBody")


        }
    }

    /**
     * 非get请求
     * */
    private fun httpMethod() {


    }
}