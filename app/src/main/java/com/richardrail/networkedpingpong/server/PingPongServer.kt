package com.richardrail.networkedpingpong.server

import android.util.Log
import fi.iki.elonen.NanoHTTPD;

class PingPongServer(hostname: String?, port: Int = 8080) : NanoHTTPD(hostname, port) {

    override fun start() {
        super.start()
        Log.d("PingPongServer", "Web server listening on $hostname : $listeningPort")
    }
    override fun serve(session: IHTTPSession?): Response {
        Log.d("PingPongServer", "Web server serving ${session!!.remoteHostName} on uri ${session.uri}")
        var r = newFixedLengthResponse(Response.Status.OK,NanoHTTPD.MIME_PLAINTEXT,"Ping");
        return r
    }
}