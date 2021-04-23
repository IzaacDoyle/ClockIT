package doyle.izaac.clockit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doyle.izaac.clockit.Firebase.checkAccounts
import doyle.izaac.clockit.R
import kotlinx.android.synthetic.main.fragment_clock_in.*
import kotlinx.android.synthetic.main.fragment_clock_in.view.*
import kotlinx.android.synthetic.main.fragment_clock_out.*
import kotlinx.android.synthetic.main.fragment_clock_out.view.*


class FragmentClockOut : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //     return super.onCreateView(inflater, container, savedInstanceState)


        val view = inflater.inflate(R.layout.fragment_clock_out, container, false)

        view.CIO_Button.setOnClickListener {
            val username = CIO_Name.text.toString()
            val password = CIO_Password.text.toString().toInt()

            if (username.isNotEmpty()){
                if (username.isNotEmpty()){
                    checkAccounts(username,password)
                }

            }

        }


        return view

    }



}