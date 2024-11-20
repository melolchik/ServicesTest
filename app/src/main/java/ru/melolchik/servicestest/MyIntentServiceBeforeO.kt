package ru.melolchik.servicestest

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log


class MyIntentServiceBeforeO : IntentService(NAME) {

    private val notificationManager by lazy{
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
        setIntentRedelivery(true)
        log("onCreate")
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE,0) ?: 0
        //not in main thread
        for (i in 0 until  10) {
            Thread.sleep(1000)
            log("Page: $page Time: $i")
        }
        //stopped automatically
    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyIntentServiceBeforeO: $message")
    }


    companion object{
        private const val NAME = "MyIntentServiceBeforeO"
        private const val PAGE = "page"
        fun newIntent(context: Context, page : Int) : Intent{
            return Intent(context, MyIntentServiceBeforeO::class.java).apply {
                putExtra(PAGE,page)
            }
        }
    }
}