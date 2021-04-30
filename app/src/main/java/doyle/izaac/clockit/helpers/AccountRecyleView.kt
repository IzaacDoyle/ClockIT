package doyle.izaac.clockit.helpers

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import doyle.izaac.clockit.Firebase.UpdateAccount
import doyle.izaac.clockit.R
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.account_u_d.*
import kotlinx.android.synthetic.main.accountviewcard.view.*

//var productRec: java.util.ArrayList<MainRecycleProd>?=null

class AccountRecycleAdaptor(private val accounts: ArrayList<AccountModel>, private val context: Context):
        RecyclerView.Adapter<AccountRecycleAdaptor.ViewHolder>() {






    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindAccounts(account: AccountModel){
            Log.d("Username", account.Username)
            itemView.RV_Password.text = account.Password.toString()
            itemView.RV_Username.text = account.Username.toString()
            itemView.RV_Role.text = account.Role.toString()
            itemView.Account_Pay.text = account.Pay.toString()

        }

        init {



            itemView.setOnClickListener { v: View ->
                val position = adapterPosition

                val username = v.RV_Username.text.toString()
                val password = v.RV_Password.text.toString()
                val Role = v.RV_Role.text.toString()
                val pay = v.Account_Pay.text.toString()


                val mDialView =
                        LayoutInflater.from(v.context).inflate(R.layout.account_u_d, null)
                val mBuilder = AlertDialog.Builder(v.context)
                        .setView(mDialView)
                        .setTitle("Account Edit")
                val mAlertDialog = mBuilder.show()



                mAlertDialog.Accounts_UPDEL_Username.setText(username)
                mAlertDialog.Accounts_UPDEL_Password.setText(password)
                val index = 0

              //  mAlertDialog.Accounts_UPDEL_Role  Pass Role into Spinner and check index and be able to cheange spinner and select new role if required

                mAlertDialog.Accounts_UPDEL_Role
                mAlertDialog.Accounts_UPDEL_Pay.setText(pay)

                mAlertDialog.Account_Edit.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked){
                            mAlertDialog.Accounts_UPDEL_Username.isEnabled = true
                            mAlertDialog.Accounts_UPDEL_Password.isEnabled = true
                            mAlertDialog.Accounts_UPDEL_Role.isEnabled = true
                            mAlertDialog.Accounts_UPDEL_Pay.isEnabled = true
                        }

                }

                mAlertDialog.Accout_update.setOnClickListener {

                   val username = mAlertDialog.Accounts_UPDEL_Username.text.toString()
                    val Role = mAlertDialog.Accounts_UPDEL_Role.toString()
                    val  Password = mAlertDialog.Accounts_UPDEL_Password.text.toString()
                    val Pay  = mAlertDialog.Accounts_UPDEL_Pay.text.toString()

                    if (UpdateAccount(username, Password.toInt(), Pay.toDouble(), Role)){
                        mAlertDialog.cancel()
                    }



                }





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