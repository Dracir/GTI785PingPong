package com.richardrail.networkedpingpong.server

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

class ServerServiceConnection : ServiceConnection{

    var pingPongService : PingPongService? = null;

    override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?) {
        pingPongService = binder as PingPongService;
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}