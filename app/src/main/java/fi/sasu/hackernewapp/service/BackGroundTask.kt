package fi.sasu.hackernewapp.service

import android.app.IntentService
import android.content.Intent
import fi.sasu.hackernewapp.method.RequestApi

class BackGroundTask : IntentService("BackGroundTask") {

    val requestApi=RequestApi()
    override fun onHandleIntent(p0: Intent?) {

    }
    //val queue = Volley.newRequestQueue(this)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return super.onStartCommand(intent, flags, startId)


    }

}