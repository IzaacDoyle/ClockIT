package doyle.izaac.clockit.fragments

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import doyle.izaac.clockit.Firebase.ClockInAdd


import doyle.izaac.clockit.R
import doyle.izaac.clockit.helpers.Communicator
import kotlinx.android.synthetic.main.fragment_clock_in.*
import kotlinx.android.synthetic.main.fragment_clock_in.view.*
import kotlinx.android.synthetic.main.fragment_clock_out.*
import kotlinx.android.synthetic.main.fragment_clock_out.view.*


class FragmentClockOut : Fragment() {

    private lateinit var communicator: Communicator


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        //     return super.onCreateView(inflater, container, savedInstanceState)


        val view = inflater.inflate(R.layout.fragment_clock_out, container, false)

        communicator = activity as Communicator


        view.CIO_Button.setOnClickListener {
            val username = CIO_Name.text.toString()
            val password = CIO_Password.text.toString()

            if (username.isNotEmpty()) {
                if (password.isNotEmpty()) {
                    val time = SystemClock.currentThreadTimeMillis()
                   ClockInAdd(view.context,username,password.toInt(), time, false)
                        communicator.passDataCom(username, password.toInt())
                        CIO_Name.text = null
                        CIO_Password.text = null
                    }
                /*else{
                        Toast.makeText(context, "Username or Password is incorrect", Toast.LENGTH_SHORT).show()
                        CIO_Password.text = null
                    }

                 */
                    /*
                   if(checkAccounts(username,password.toInt())){
                       Log.d("Account is not there", "Account is not there")

                   }else{
                       communicator.passDataCom(username,password.toInt())
                       Log.d("Account is there","Account is there")
                       CIO_Name.text = null
                       CIO_Password.text = null
                   }
                }

                     */

                }
            }




        return view

    }



}