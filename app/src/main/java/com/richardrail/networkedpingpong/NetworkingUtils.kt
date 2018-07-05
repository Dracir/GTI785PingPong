package com.richardrail.networkedpingpong

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.wifi.WifiManager


class NetworkingUtils{
    companion object {
        fun GetDeviceIPv4Adress(context: Context): String {
            val wm = context.getApplicationContext().getSystemService(WIFI_SERVICE) as WifiManager
            val ip = wm.connectionInfo.ipAddress
            return String.format("%d.%d.%d.%d", ip and 0xff, ip shr 8 and 0xff, ip shr 16 and 0xff, ip shr 24 and 0xff)
        }
    }

}