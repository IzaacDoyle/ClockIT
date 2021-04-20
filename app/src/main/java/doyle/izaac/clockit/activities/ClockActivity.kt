package doyle.izaac.clockit.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import doyle.izaac.clockit.Firebase.ManagerCheck
import doyle.izaac.clockit.Firebase.managercheck
import doyle.izaac.clockit.R
import doyle.izaac.clockit.fragments.FragmentClockIn
import doyle.izaac.clockit.fragments.FragmentClockOut
import doyle.izaac.clockit.fragments.MainFragment
import doyle.izaac.clockit.helpers.read
import doyle.izaac.clockit.helpers.readImageFromPath
import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.ClockInModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.manager_screen_login.*


class ClockActivity: AppCompatActivity(), GestureDetector.OnGestureListener {
    lateinit var app: MainApp
    var clockin = ClockInModel()
    val mainFragment = MainFragment()
    val FragClockIn = FragmentClockIn()
    val FragmentClockOut = FragmentClockOut()
    val ft = supportFragmentManager
    var buttonClicks = 0
    var timerActive: Boolean = false


  lateinit var gestureDetector: GestureDetector
    var x2:Float = 0.0f
    var x1:Float = 0.0f
    var y2:Float = 0.0f
    var y1:Float = 0.0f

    companion object{
        const val MIN_DISTANCE = 150
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null){
            ft.beginTransaction().replace(R.id.fragment_Container, mainFragment,"Main").commit()
        }
        setContentView(R.layout.activity_main)
        app = application as MainApp

        val bitmap = read(this)
       // val DBitmap = BitmapFactor
        Log.d("bitmapCA",bitmap)


        ManagerScreen_Image.setImageBitmap(readImageFromPath(this,bitmap))

       /* ClB_Button.setOnClickListener {

            val username = CII_Name.text.toString()
            val password = CII_Password.text.toString()

        }


        CIO_Button.setOnClickListener {

            val username = CIO_Name.text.toString()
            val password = CIO_Password.text.toString()

        }


        */





        ManagerScreen_Image.setOnClickListener {
            buttonClicks++
            if (buttonClicks == 1 || buttonClicks ==2 || buttonClicks == 3 || buttonClicks == 4){
                if (timerActive== false) {
                    val timer = object : CountDownTimer(5000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            Log.d("Timer", "$millisUntilFinished")
                        }

                        override fun onFinish() {
                            buttonClicks = 0
                            timerActive = false
                        }
                    }
                    timerActive = true
                    timer.start()
                }
            }
            if (buttonClicks ==5){
                Log.d("Clicked", "Clicked 3 times")

                val mDialView =
                        LayoutInflater.from(this).inflate(R.layout.manager_screen_login, null)
                val mBuilder = AlertDialog.Builder(this)
                        .setView(mDialView)
                        .setTitle("Manager Login")
                val mAlertDialog = mBuilder.show()

                mAlertDialog.manager_signIn.setOnClickListener {
                    val manager_mp = mAlertDialog.Manager_MP.text.toString()
                    val manager_mu = mAlertDialog.Manager_MU.text.toString()
                    if (manager_mu.toString().isNullOrBlank()) {
                        Log.d("manager", "UserNameError + $manager_mp")
                    } else if (manager_mu.toString().isNullOrBlank()) {
                        Log.d("manager", "UserPasswordError + $manager_mu")
                }else if(manager_mu != null && manager_mp != null){
                        ManagerCheck(manager_mu, manager_mp.toInt())
                    }
                    if (managercheck == true){
                        val intent = Intent(applicationContext, ManagerActionsActivity::class.java)
                        startActivity(intent)
                        mAlertDialog.cancel()
                    }
                }




                buttonClicks = 0
            }



        }






    gestureDetector = GestureDetector(this,this)
        ft.addOnBackStackChangedListener {


        }



    }




    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)

        when(event?.action){
            //when start to swipe
            0 -> {
                x1 = event.x
                y1 = event.y
            }
           // when end the swipe
            1 -> {
                x2 = event.x
                y2 = event.y
                val valueX: Float = x2 - x1
                val valueY: Float = y2 - y1

                if (kotlin.math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {

                        Log.d("Swipe", "Swipe Right")

                        FragmentView(FragClockIn,"Right")






                        /*ClockInSwipeIM.animate().apply {

                           duration = 1000
                            rotationYBy(360f)



                        }.withEndAction {
                            ClockInSwipeIM.rotationY = 0f

                        }

                         */
                    } else {


                        Log.d("Swipe", "Swipe Left")

                        FragmentView(FragmentClockOut,"Left")


                      /*  ClockInSwipeIM.animate().apply {
                            duration = 1000
                            rotationYBy(-360f)
                        }.withEndAction {
                            ClockInSwipeIM.rotationY = 0f
                        }

                       */
                    }


                } else if (kotlin.math.abs(valueY) > MIN_DISTANCE) {
                    if (y2 > y1) {
                        Log.d("Swipe", "Swipe Bottom")
                        FragmentView(mainFragment,"Right")
                    } else {
                        Log.d("Swipe", "Swipe Top")

                    }
                }
            }
        }


        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }
    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }
    override fun onLongPress(e: MotionEvent?) {
    }
    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }



    private fun FragmentView(fragment: Fragment, Swipe: String){
        val ft = ft.beginTransaction()

        if (Swipe=="Right"){
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
            ft.addToBackStack("right")
        }else if (Swipe == "Left"){
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            ft.addToBackStack("left")
        }

        ft.replace(R.id.fragment_Container, fragment).commit()
    }



}