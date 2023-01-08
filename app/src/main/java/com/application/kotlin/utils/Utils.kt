package com.application.kotlin.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.WindowManager
import com.application.kotlin.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

object Utils {

    private var dialog: Dialog? = null
    fun showProgress(activity: Activity?) {
        if (dialog != null && dialog!!.isShowing) {
            return
        }
        dialog = Dialog(activity!!)
        dialog!!.setContentView(R.layout.layout_progress)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val params = dialog!!.window!!
            .attributes
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog!!.window!!.setDimAmount(0.5f)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    fun hideProgress() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    @Suppress("DEPRECATION")
    fun isNetworkConnected(ctx: Context): Boolean {
        var result = false
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    fun makeJSONRequestBody(jsonObject: JSONObject): RequestBody {
        return jsonObject.toString().toRequestBody(Const.APPLICATION_JSON.toMediaTypeOrNull())
    }

    fun makeStringRequestBody(string: String): RequestBody {
        return string.toRequestBody(Const.APPLICATION_JSON.toMediaTypeOrNull())
    }

    fun getRandomColor() : String{
        val col = listOf("FF008A","FBC21C","E73F32","269746","F15A24",
            "BC5A2B","B47C2E","8842D0","B25D1F","1B8996","58696F","82C7C5",
            "F2C8F0","F1BF8C")
        var r  = (col.indices).random()
        return col[r]
    }

}