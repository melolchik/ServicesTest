package ru.melolchik.servicestest

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService


class MyJobIntentService : JobIntentService() {

    private val notificationManager by lazy{
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }


    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }
    override fun onHandleWork(intent: Intent) {
        log("onHandleWork")
        val page = intent.getIntExtra(PAGE,0) ?: 0
        //not in main thread
        for (i in 0 until  5) {
            Thread.sleep(1000)
            log("Page: $page Time: $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyJobIntentService: $message")
    }


    companion object{
        private const val PAGE = "page"
        private const val JOB_ID = 1111;

        fun enqueue(context: Context, page: Int){
            JobIntentService.enqueueWork(context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context,page)
                )
        }
        private fun newIntent(context: Context, page : Int) : Intent{
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE,page)
            }
        }
    }
}