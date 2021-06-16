package doyle.izaac.clockit.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import doyle.izaac.clockit.R
import doyle.izaac.clockit.models.ClockedAccounts
import kotlinx.android.synthetic.main.clocked_account.view.*


class ClockInRecycleView (private val clockedAccounts: ArrayList<ClockedAccounts>,private val context: Context):
        RecyclerView.Adapter<ClockInRecycleView.ViewHolder>(){



    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        fun bindAccounts(clockedAccounts: ClockedAccounts){
            itemView.clocked_Username.text = clockedAccounts.Username

            if (clockedAccounts.clocked == true){
                itemView.clocked_Clockedstat.setText("Clocked In")
            }else
            {
                itemView.clocked_Clockedstat.setText("Clocked Out")
            }


        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClockInRecycleView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
                R.layout.clocked_account,parent,false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ClockInRecycleView.ViewHolder, position: Int) {
        holder.bindAccounts(clockedAccounts[position])
    }

    override fun getItemCount(): Int {
       return clockedAccounts.size
    }

}