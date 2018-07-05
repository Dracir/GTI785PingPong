package com.richardrail.networkedpingpong

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.richardrail.networkedpingpong.client.WebAsyncTask
import com.richardrail.networkedpingpong.client.WebRequest
import com.richardrail.networkedpingpong.server.PingPongService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,PingPongService::class.java)
        startService(intent)
        Toast.makeText(this, "Web server started!", Toast.LENGTH_LONG).show();

        pingButton.setOnClickListener(View.OnClickListener {
            val request = WebRequest("GET","/test/pingpong","Pong","10.192.177.161",8080)
            val task = WebAsyncTask(applicationContext, request) { onSuccess:String -> ParseCurrentSongInfo(onSuccess) }
            task.execute();
        })

    }

    private fun ParseCurrentSongInfo(payload: String) {
        Log.d("PingPongClient","Response : " + payload)
        Toast.makeText(this, "Responce : " + payload, Toast.LENGTH_LONG).show();
    }
}
