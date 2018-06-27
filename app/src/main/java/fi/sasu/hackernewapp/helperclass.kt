package fi.sasu.hackernewapp

import android.content.Context
import android.widget.Toast

class helperclass{
    val userNameForLogging:String ="Sasu"
    fun naytaToast(teksti: String,context:Context) {
        val aika = Toast.LENGTH_LONG
        // applicationContext
        val toast = Toast.makeText(context, teksti, aika)
        toast.show()
    }
}