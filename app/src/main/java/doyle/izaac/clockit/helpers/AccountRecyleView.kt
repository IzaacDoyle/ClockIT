package doyle.izaac.clockit.helpers

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import doyle.izaac.clockit.R
import doyle.izaac.clockit.ViewModel.AccountViewModel
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.accountviewcard.view.*

//var productRec: java.util.ArrayList<MainRecycleProd>?=null

class AccountRecycleAdaptor(private val accounts: ArrayList<AccountModel>, private val context: Context):
        RecyclerView.Adapter<AccountRecycleAdaptor.ViewHolder>() {





    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindAccounts(account: AccountModel){
            Log.d("Username",account.Username)
            itemView.RV_Password.text = account.Password.toString()
            itemView.RV_Username.text = account.Username.toString()
            itemView.RV_Role.text = account.Role.toString()

        }

        init {
            itemView.setOnClickListener { v: View ->
                val position = adapterPosition

             //   val intent = Intent(itemView.context, Product_View_Update::class.java)
               // v.context.startActivity(intent)


            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
                R.layout.accountviewcard,
                parent,
                false
        )
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindAccounts(accounts[position])

    }

    override fun getItemCount(): Int {
        return accounts.size
    }




}