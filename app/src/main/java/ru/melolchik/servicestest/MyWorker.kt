package ru.melolchik.servicestest

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class MyWorker(context: Context, private val workerParams: WorkerParameters) : Worker(
    context, workerParams
) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParams.inputData.getInt(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Page $page Time: $i")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyWorker: $message")
    }

    companion object {
        const val WORK_NAME = "work_name"
        private const val PAGE = "page"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>().apply {
                setInputData(workDataOf(PAGE to page))
                setConstraints(makeConstraints())
            }
                .build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder()
                //.setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.METERED)
                .build()
        }
    }
}