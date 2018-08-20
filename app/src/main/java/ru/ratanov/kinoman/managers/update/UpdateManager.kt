package ru.ratanov.kinoman.managers.update

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import ru.ratanov.kinoman.App
import java.io.File

object UpdateManager {

    fun showUpdateDialog(activity: Activity, version: String, releaseNote: String, updateUrl: String) {
        val dialog = AlertDialog.Builder(activity)
                .setTitle("Доступно обновление ($version)")
                .setMessage("Пожалуйста, обновите приложение для продолжения работы\n\n$releaseNote")
                .setPositiveButton("Обновить") { dialog, which -> update(activity, updateUrl) }
                .setNegativeButton("Нет, спасибо") { dialog, which -> activity.finish() }
                .setCancelable(false)
                .create()

        dialog.show()
    }

    private fun update(activity: Activity, updateUrl: String) {
        val progressDialog = ProgressDialog(activity).apply { setMessage("Загрузка обновления") }
        progressDialog.show()

        getPath().mkdirs()
        PRDownloader.download(updateUrl, getPath().absolutePath, "update.apk")
                .build()
                .start(object: OnDownloadListener {
                    override fun onDownloadComplete() {
                        progressDialog.dismiss()
                        install(File(getPath().absolutePath + "/update.apk"))
                    }

                    override fun onError(error: Error?) {
                        Toast.makeText(App.instance(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun getPath() = File(App.instance().getExternalFilesDir(null), "Update")

    private fun install(file: File) {
        val context = App.instance()
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
        context.startActivity(intent)
    }
}