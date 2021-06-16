package doyle.izaac.clockit.helpers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Log
import androidx.core.content.contentValuesOf
import java.io.*

//saves Image loction route to Internal file so that image can be changed and always displays

fun SaveDataLocally(context: Context,ImageBitmap :String, FileName: String){


    val data = ImageBitmap.toString()
    Log.d("bitmap", ImageBitmap.toString())
    try{
        val outputStreamWriter = OutputStreamWriter(context.openFileOutput(FileName, Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        Log.e("Error: ", "Cannot Write file: " + e.toString())
    }
}

fun SaveDataSharedPref(context: Context,ImageBitmap: String){
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        type = ImageBitmap
    }
    val sharedPreferences = context.getSharedPreferences("ImageBitmap",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.apply(){
        putString("ImageBitMap", ImageBitmap)

    }.apply()
    Log.d("SharedPref","saved")
}

fun ReadDataSharedPref(context: Context): String? {

    val sharedPreferences = context.getSharedPreferences("ImageBitmap",Context.MODE_PRIVATE)
    Log.d("SharedPref", sharedPreferences.getString("ImageBitMap",null).toString())
   return sharedPreferences.getString("ImageBitMap",null)

}





fun readDataLocally(context: Context, FileName: String): String {
    var str = ""
    try {
        val inputStream = context.openFileInput(FileName)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                val line = bufferedReader.readLine()
                done = (line == null)
                if (line != null) partialStr.append(line)
            }
            inputStream.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        Log.e("Error: ", "file not found: " + e.toString())
    } catch (e: IOException) {
        Log.e("Error: ", "cannot read file: " + e.toString())
    }
    Log.d("FileRead",str)
    return str
}

