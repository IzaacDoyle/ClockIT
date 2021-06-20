package doyle.izaac.clockit.fragments

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import doyle.izaac.clockit.Firebase.ClockInAdd


import doyle.izaac.clockit.R
import doyle.izaac.clockit.helpers.Communicator
import kotlinx.android.synthetic.main.fragment_clock_in.*
import kotlinx.android.synthetic.main.fragment_clock_in.view.*
import java.sql.Time

class FragmentClockIn : Fragment() {

    private lateinit var communicator: Communicator


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val view = inflater.inflate(R.layout.fragment_clock_in, container, false)


        communicator = activity as Communicator



        view.ClB_Button.setOnClickListener {
            val username = CII_Name.text.toString()
            val password = CII_Password.text.toString()
            if (username.isNotEmpty()) {
                if (password.isNotEmpty()) {
                    val time = SystemClock.currentThreadTimeMillis()

                   ClockInAdd(view.context,username,password.toInt(),time,true)

                        communicator.passDataCom(username, password.toInt())
                        CII_Name.text = null
                        CII_Password.text = null
                    /*
                    else{
                        Toast.makeText(context, "Username or Password is incorrect", Toast.LENGTH_SHORT).show()
                        CII_Password.text = null
                    }

                     */
                }
                  /*
                    if (checkAccounts(username, password.toInt())) {

                        Log.d("Account is not there", "Account is not there")
                    }else{
                        //communicator.passDataCom(username, password.toInt())
                        Log.d("Account is there", "Account is there")
                        CII_Name.text = null
                        CII_Password.text = null
                    }
                }

                   */
            }
        }

        return view

    }

}




