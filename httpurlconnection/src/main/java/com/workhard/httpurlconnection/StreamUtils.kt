package com.workhard.httpurlconnection

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

/**
 * 创建时间：2019/12/31
 *
 * 作者：Mr.Liu
 *
 * 描述：流转字符串
 **/
object StreamUtils {

    /**
     * 网络请求的流转为字符串
     * */
    fun stream2Str(inputStream:InputStream):String{

        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuffer()
        var line = bufferedReader.readLine()
        try {

            while (line != null){
                sb.append(line)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            bufferedReader.close()
            inputStream.close()
        }
        return sb.toString()
    }
}