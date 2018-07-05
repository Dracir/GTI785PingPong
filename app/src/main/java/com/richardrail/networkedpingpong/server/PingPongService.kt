package com.richardrail.networkedpingpong.server

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.util.Log
import com.richardrail.networkedpingpong.NetworkingUtils


class PingPongService : IntentService("Ping Pong service") {

    override fun onHandleIntent(intent: Intent?) {
        val context = getApplicationContext();
        val ip = NetworkingUtils.GetDeviceIPv4Adress(context);

        Log.d("PingPongServer","Ping Pong Service Starting...")
        val newServeur = PingPongServer(ip);
        newServeur.start()
        Log.d("PingPongServer","Ping Pong Service Started!")
        //FIXME Peu retourner plusieurs serveur sur le meme port, ce n'est pas parfait.
    }


    inner class PingPongServiceBinder(val _server: PingPongServer) : Binder() {

        internal val service: PingPongService
            get() = this@PingPongService
    }

}