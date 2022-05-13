package com.kdh.diarydodo.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.kdh.diarydodo.App
import com.kdh.diarydodo.common.ApplicationContextWrapper
import java.io.File


object DataUtil {

    fun getPath(uri: Uri): String {
        var path: String = ""
        val uriExternal: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var imageId: Long
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val columnIndexID: Int
        val cursor: Cursor? = ApplicationContextWrapper.instance.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )


        try {
            if (cursor != null) {
                columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                if (cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        imageId = cursor.getLong(columnIndexID)
                        val uriImage = Uri.withAppendedPath(uriExternal, "" + imageId)
                        Log.d("dodo55", "imageId e: ${imageId} ")
                        Log.d("dodo55", "uriImage e: ${uriImage} ")
                        //            val path: String? = cursor.getString(cursor.getColumnIndex("_data"))
//                        path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
                        path =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))

                    }

                }
            }
        } catch (e: Exception) {
            Log.d("dodo55", "getPath e: ${e} ")
        } finally {
            cursor?.close()
        }
        Log.d("dodo55", "getPath: path ${path}")
        return path
    }

    //Uri -> Path(파일경로)
    fun uri2path(contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
        val cursor: Cursor? = ApplicationContextWrapper.instance.contentResolver.query(contentUri, proj, null, null, null)
        var path = ""
        var uri = ""
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                     path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
                     uri = Uri.fromFile(File(path)).toString()
                Log.d("dodo55", "uri2path path :${path} ")
                Log.d("dodo55", "uri2path uri :${uri} ")
            }
        }

        cursor?.close()

        return path
    }

}