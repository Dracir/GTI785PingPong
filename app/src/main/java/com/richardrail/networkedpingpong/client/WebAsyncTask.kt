package com.richardrail.networkedpingpong.client

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import java.net.HttpURLConnection


class WebAsyncTask(private val context: Context, private val request: WebRequest, private val taskSuccessCallback: (payload:String) -> Unit) : AsyncTask<String, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg strings: String): String {

        try {
            Log.d("WebAsyncTask", "WebAsyncTask::Request " + " - " + request.actionURI)

            val status = request.Request(false)

            Log.d("WebAsyncTask", "WebAsyncTask::Request " + status + " - " + request.responseBody)
            return "OK"
        } catch (e: Exception) {
            Log.e("WebAsyncTask", "WebAsyncTask::DoInBackground()" + e.message)
            return "ERROR"
        }

    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        Log.d("WebAsyncTask", "WebAsyncTask::onPostExecute() " + request.responseCode + " - " + request.responseBody)

        if (request.responseCode === HttpURLConnection.HTTP_OK) {
            if (this.taskSuccessCallback != null) {
                this.taskSuccessCallback(request.Payload)
            }
        }else{
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG);
            //TODO add on errorcallbacks to display error (toast or whatever)
        }


    }


}