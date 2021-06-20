package doyle.izaac.clockit.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.common.io.LineReader
import doyle.izaac.clockit.R
import doyle.izaac.clockit.ViewModel.AccountViewModel
import doyle.izaac.clockit.ViewModel.ClockedViewModel
import doyle.izaac.clockit.activities.ClockActivity
import doyle.izaac.clockit.helpers.AccountRecycleAdaptor
import doyle.izaac.clockit.helpers.ClockInRecycleView
import doyle.izaac.clockit.models.ClockedAccounts
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.manager_main.*


/*

add Mainfrag to back stack
create recyclerview to display info
create account - Id - password - clockinTime - ClockOutTime- Date -ClockedIN(True,False) - Pay - hours
Create function to Email report -
create timetable function - auto determin hours and pay



 */



open class MainFragment : Fragment(){

    private lateinit var viewModel:ClockedViewModel



    private var inputText: String? = ""





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)


        inputText = arguments?.getString("Username")


      //  val myAdaptor = ClockInRecycleView(,requireContext())

        viewModel = ViewModelProviders.of(this).get(ClockedViewModel::class.java)
        viewModel.account.observe(viewLifecycleOwner, { it ->

            val myAdaptor = ClockInRecycleView(it,view.context)
            clocked_Accounts.layoutManager = LinearLayoutManager(view.context)
            clocked_Accounts.adapter = myAdaptor
            clocked_Accounts.adapter!!.notifyDataSetChanged()
            clocked_Accounts.scrollToPosition(0)
        }
        )





        return view




    }



}

