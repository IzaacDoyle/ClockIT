package doyle.izaac.clockit.activities

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import doyle.izaac.clockit.Firebase.GetImageFB
import doyle.izaac.clockit.R
import doyle.izaac.clockit.helpers.readDataLocally
import doyle.izaac.clockit.helpers.readImageFromPath
import kotlinx.android.synthetic.main.clockit_splashscreen.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import kotlin.math.max

class StartUpActivity : AppCompatActivity() {

    private var SPLASH_SCREEN_TIME : Long = 3500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.clockit_splashscreen)
        supportActionBar?.hide()
        val imageData = GetImageFB()







        if (checkInternet()){
            Re_Try.visibility = View.GONE
            Handler(Looper.myLooper()!!).postDelayed({

               val intent = Intent(applicationContext,ClockActivity::class.java)
                intent.putExtra("ImageSource",imageData)
                Log.d("Image2", imageData.toString())
                startActivity(intent)
              //  startActivity(intentFor<ClockActivity>())
                finish()

            },SPLASH_SCREEN_TIME)
          //  progressBar.max = 1000
         //   val currentProgress = 600
         //   ObjectAnimator.ofInt(progressBar,"progress",currentProgress)
        //        .setDuration(2000)
         //       .start()


            Re_Try.setOnClickListener{
                recreate()
            }



             //  startActivity(intentFor<ClockActivity>())


        }else{
            Re_Try.visibility = View.VISIBLE
            toast("Please Enable an Internet Connection")
        }

    }


 /*   fun checkPermission(context: Context,permissionArray :Array<String>):Boolean {
        return false
    }

  */


    private fun checkInternet(): Boolean{
        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager

        val networkinfo = manager.activeNetworkInfo

        if(null != networkinfo){
            return networkinfo.type == ConnectivityManager.TYPE_WIFI || networkinfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }





}