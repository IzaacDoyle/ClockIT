package doyle.izaac.clockit.helpers

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Log
import java.io.*

val file:String = "HomeImage"

fun SaveImage(context: Context,ImageBitmap: Bitmap){


    val data = ImageBitmap.toString()
    Log.d("bitmap", ImageBitmap.toString())
    try{
        val outputStreamWriter = OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        Log.e("Error: ", "Cannot read file: " + e.toString());
    }
}
fun read(context: Context): String {
    var str = ""
    try {
        val inputStream = context.openFileInput(file)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                var line = bufferedReader.readLine()
                done = (line == null);
                if (line != null) partialStr.append(line);
            }
            inputStream.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        Log.e("Error: ", "file not found: " + e.toString());
    } catch (e: IOException) {
        Log.e("Error: ", "cannot read file: " + e.toString());
    }
    Log.d("FileRead",str)
    return str
}

