package doyle.izaac.clockit.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import doyle.izaac.clockit.R
import doyle.izaac.clockit.activities.ClockActivity
import kotlinx.android.synthetic.main.fragment_main.*

/*

add Mainfrag to back stack
create recyclerview to display info
create account - Id - password - clockinTime - ClockOutTime- Date -ClockedIN(True,False) - Pay - hours
Create function to Email report -
create timetable function - auto determin hours and pay



 */
open class MainFragment : Fragment(R.layout.fragment_main),GestureDetector.OnGestureListener{

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

        gestureDetector = GestureDetector(requireContext(),this)


    }

    fun onTouchEvent(event: MotionEvent?): Boolean {

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

                       // FragmentView(FragmentClockOut)



                        /*
                        ClockInSwipeIM.animate().apply {

                            duration = 1000
                            rotationYBy(360f)



                        }.withEndAction {
                            ClockInSwipeIM.rotationY = 0f

                        }

                         */
                    } else {


                        Log.d("Swipe", "Swipe Left")

                       // FragmentView(FragClockIn)

/*
                        ClockInSwipeIM.animate().apply {
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
                    } else {
                        Log.d("Swipe", "Swipe Top")

                    }
                }
            }
        }


        return onTouchEvent(event)
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


}