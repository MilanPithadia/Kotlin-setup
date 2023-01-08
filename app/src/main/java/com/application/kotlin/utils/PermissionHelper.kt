package com.application.kotlin.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.kotlin.BuildConfig
import com.google.android.material.snackbar.Snackbar

object PermissionHelper {

    private var permission = ""
    private val permissionResult = MutableLiveData<Boolean>()
    fun requestPermission(activity: Activity, permission: String): LiveData<Boolean> {
        this.permission = permission;
        val permissionCheck = ContextCompat.checkSelfPermission(activity, permission)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                Snackbar.make(
                    activity.window.decorView,
                    "Permission Required Go setting and Allow permission",
                    Snackbar.LENGTH_LONG
                ).setAction("Setting") {
                    activity.startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                        )
                    )
                }.show()

                permissionResult.postValue(false)
            } else {
                permissionResult.postValue(false)
                ActivityCompat.requestPermissions(activity, arrayOf(permission), 101)
            }
        } else {
            permissionResult.postValue(true)
        }
        return permissionResult
    }
    fun submitResult(activity: Activity) {

        val permissionCheck = ContextCompat.checkSelfPermission(activity, permission)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
        {
            permissionResult.postValue(true)
        }else{
            requestPermission(activity, permission)
        }
    }
}