package com.workhard.httpurlconnection

import android.util.Log

/**
 * 创建时间：2019/12/31
 *
 * 作者：Mr.Liu
 *
 * 描述：日志
 **/
object LogUtil {

    var tag = true

    fun loge(content:String){
        if (tag){
            Log.e("tag",content)
        }
    }

    fun logw(content:String){
        if (tag){
            Log.w("tag",content)
        }
    }

    fun logi(content:String){
        if (tag){
            Log.i("tag",content)
        }
    }

    fun logd(content:String){
        if (tag){
            Log.d("tag",content)
        }
    }
}