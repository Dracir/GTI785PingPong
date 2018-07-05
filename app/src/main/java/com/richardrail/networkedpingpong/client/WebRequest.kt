package com.richardrail.networkedpingpong.client

import android.R.attr.port
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class WebRequest {
    var Method: String
    var actionURI: String
    var Payload: String
    var ip: String
    var port: Int = 0

    var responseCode = 0
    var responseBody: String? = null
    var responseHeader: String? = null

    constructor(method: String, uri: String, payload: String, ip: String, port: Int) {
        this.Method = method
        this.actionURI = uri
        this.Payload = payload
        this.ip = ip
        this.port = port
    }

    fun getRequestURL(https: Boolean): URL? {
        val baseUrl = StringBuilder()
        if(https)
            baseUrl.append("https://");
        else
            baseUrl.append("http://")

        baseUrl.append(ip);
        if(port != 80)
            baseUrl.append(":" + port);

        try {
            return URL(URL(baseUrl.toString()), actionURI)
        } catch (e: MalformedURLException) {
            Log.e("Client", "WebRequest::getRequestURL()" + e.message)
        }

        return null
    }

    fun Request(https: Boolean): Int {
        var conn: HttpURLConnection? = null

        try {
            conn = getRequestURL(https)!!.openConnection() as HttpURLConnection
            conn!!.setReadTimeout(6000)
            conn!!.setConnectTimeout(6000)
            conn!!.setRequestMethod(this.Method)

            if (this.Method == "PUT" || Method == "POST") {
                conn!!.addRequestProperty("content-type", "application/json")
                conn!!.setDoOutput(true)
                conn!!.setDoInput(true)

                val os = conn!!.getOutputStream()
                os.write(Payload.toByteArray())
                os.close()
            }
            responseCode = conn!!.getResponseCode()

            if (conn!!.getContentType().equals("application/json")) {
                val inputReader = BufferedReader(InputStreamReader(conn!!.getInputStream()))
                responseBody = inputReader.readLine()
            } else {
                responseBody = conn!!.getResponseMessage()
            }//elseif TODO streamed data bytes

            conn!!.disconnect()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return responseCode
    }
}