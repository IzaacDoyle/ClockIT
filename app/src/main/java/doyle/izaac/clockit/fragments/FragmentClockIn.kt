package doyle.izaac.clockit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import doyle.izaac.clockit.Firebase.checkAccounts
import doyle.izaac.clockit.R
import doyle.izaac.clockit.helpers.Communicator
import kotlinx.android.synthetic.main.fragment_clock_in.*
import kotlinx.android.synthetic.main.fragment_clock_in.view.*

class FragmentClockIn : Fragment() {

    private lateinit var communicator: Communicator


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val view = inflater.inflate(R.layout.fragment_clock_in, container, false)


        communicator = activity as Communicator



        view.ClB_Button.setOnClickListener {
            val username = CII_Name.text.toString()
            val password = CII_Password.text.toString().toInt()
            if (username.isNotEmpty()){
                if (username.isNotEmpty()){
                    if (checkAccounts(username,password) ==true){
                        communicator.passDataCom(username)
                        
                    }
                }

            }

        }


        return view

    }




}


