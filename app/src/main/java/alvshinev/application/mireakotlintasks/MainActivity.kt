package alvshinev.application.mireakotlintasks

import alvshinev.application.mireakotlintasks.domain.workmanager.NewsUpdateWorker
import alvshinev.application.mireakotlintasks.presentation.ui.MainScreen
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import java.time.Duration
import java.util.UUID
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val newsUpdateRequest = PeriodicWorkRequestBuilder<NewsUpdateWorker>(10, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSIONS,
                0
            )
        }

        scheduleNewsUpdate()

        setContent {

            MainScreen(
                viewModel = viewModel(),
                context = applicationContext
            )

        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(newsUpdateRequest.id).observe(this, Observer { workInfo ->
            when (workInfo.state) {
                WorkInfo.State.ENQUEUED -> Log.d("MyWorkManager", "Работа поставлена в очередь")
                WorkInfo.State.RUNNING -> Log.d("MyWorkManager", "Работа в процессе выполнения")
                WorkInfo.State.SUCCEEDED -> Log.d("MyWorkManager", "Работа завершена успешно")
                WorkInfo.State.FAILED -> Log.d("MyWorkManager", "Работа завершилась с ошибкой")
                WorkInfo.State.CANCELLED -> Log.d("MyWorkManager", "Работа была отменена")
                WorkInfo.State.BLOCKED -> Log.d("MyWorkManager", "Работа была заблокирована")
            }
        })

    }

    override fun onStop() {
        super.onStop()
    }

    private fun scheduleNewsUpdate() {

//        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
//            "MyUniqueWork",
//            ExistingPeriodicWorkPolicy.KEEP,
//            newsUpdateRequest
//        )

        WorkManager.getInstance(applicationContext).enqueue(newsUpdateRequest)

        val workRequest = OneTimeWorkRequestBuilder<NewsUpdateWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)

    }

    private fun hasRequiredPermissions(): Boolean {
        return PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

}

@Serializable
object FirstScreen

@Serializable
data class SecondScreen(
    val arg1 : String?,
    val arg2 : Int
)
