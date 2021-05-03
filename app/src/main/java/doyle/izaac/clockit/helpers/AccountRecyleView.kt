package doyle.izaac.clockit.helpers

import android.app.Activity
import android.app.backup.BackupManager.dataChanged
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import doyle.izaac.clockit.Firebase.DeleteUser
import doyle.izaac.clockit.Firebase.UpdateAccount
import doyle.izaac.clockit.Firebase.checkAccounts
import doyle.izaac.clockit.R
import doyle.izaac.clockit.ViewModel.AccountViewModel
import doyle.izaac.clockit.activities.ManagerActionsActivity
import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.account_u_d.*
import kotlinx.android.synthetic.main.accountviewcard.view.*
import kotlinx.android.synthetic.main.new_user_create.*
import kotlin.coroutines.coroutineContext

//var productRec: java.util.ArrayList<MainRecycleProd>?=null

 var Updated:Boolean = false

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

//allows Each recycler view items to be clickable and this passes data into diolog box to allow data change and deletable

            itemView.setOnClickListener { v: View ->


                val username = v.RV_Username.text.toString()
                val password = v.RV_Password.text.toString()
                val role = v.RV_Role.text.toString()
                val pay = v.Account_Pay.text.toString()
                var spinnerText = ""


                val mDialView =
                        LayoutInflater.from(v.context).inflate(R.layout.account_u_d, null)
                val mBuilder = AlertDialog.Builder(v.context)
                        .setView(mDialView)
                        .setTitle("Account Edit")
                val mAlertDialog = mBuilder.show()

                mAlertDialog.Accounts_UPDEL_Username.setText(username)
                mAlertDialog.Accounts_UPDEL_Role_Data.setText(role)
                mAlertDialog.Accounts_UPDEL_Pay.setText(pay)
                mAlertDialog.Accounts_UPDEL_Password.setText(password)

                val Adapter = ArrayAdapter(v.context,android.R.layout.simple_spinner_dropdown_item,v.resources.getStringArray(R.array.RoleSelect))
                mAlertDialog.Accounts_UPDEL_Role.adapter = Adapter
                mAlertDialog.Accounts_UPDEL_Role.isEnabled = false



                val index = 0

                //  mAlertDialog.Accounts_UPDEL_Role  Pass Role into Spinner and check index and be able to cheange spinner and select new role if required
                Log.d("TextExchange",role)

                mAlertDialog.Account_Edit.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        mAlertDialog.Accounts_UPDEL_Username.isEnabled = true
                      //  mAlertDialog.Accounts_UPDEL_Password.isEnabled = true
                        mAlertDialog.Accounts_UPDEL_Role.isEnabled = true
                        mAlertDialog.Accounts_UPDEL_Pay.isEnabled = true
                        // if Editable is active data can be change or deleted
                        mAlertDialog.Accounts_UPDEL_Role.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener{

                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                if (parent != null) {
                                    if (position == 0) {
                                        return
                                    } else {
                                        spinnerText = parent.getItemAtPosition(position).toString()
                                        Log.d("spinner", spinnerText)
                                        if (mAlertDialog.Accounts_UPDEL_Role_Data.text.toString() != spinnerText){

                                            Log.d("spinnerText","$spinnerText")
                                            mAlertDialog.Accounts_UPDEL_Role_Data.setText( spinnerText)
                                        }

                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }
                        }
                        mAlertDialog.Account_Delete.setOnClickListener {
                            val username = mAlertDialog.Accounts_UPDEL_Username.text.toString()
                            val  Password = mAlertDialog.Accounts_UPDEL_Password.text.toString()

                            if (username.isBlank()){
                                Toast.makeText(mAlertDialog.context,"Username is Invalid Make sure all Information is Entered",Toast.LENGTH_SHORT).show()
                            }else if (Password.isBlank()){
                                Toast.makeText(mAlertDialog.context,"Staff Number is Invalid Make sure all Information is Entered",Toast.LENGTH_SHORT).show()
                            }else if (v.RV_Password.text.toString() !== Password){
                                if (checkAccounts(username,Password.toInt())) {
                                    Toast.makeText(mAlertDialog.context,"Staff Number is already in Use Please Enter Unused Staff Number ",Toast.LENGTH_SHORT).show()
                                }else{
                                    if (!DeleteUser(username, Password.toInt())){
                                        Updated = true
                                        mAlertDialog.cancel()
                                    }
                                }
                            }
                        }
                    }
                    if (!isChecked){
                        mAlertDialog.Accounts_UPDEL_Username.isEnabled = false
                        mAlertDialog.Accounts_UPDEL_Password.isEnabled = false
                        mAlertDialog.Accounts_UPDEL_Role.isEnabled = false
                        mAlertDialog.Accounts_UPDEL_Pay.isEnabled = false
                    }
                }


    //Account Update

                mAlertDialog.Accout_update.setOnClickListener  {

                    val username = mAlertDialog.Accounts_UPDEL_Username.text.toString()
                    val Role = mAlertDialog.Accounts_UPDEL_Role_Data.text.toString()
                    val  Password = mAlertDialog.Accounts_UPDEL_Password.text.toString()
                    val Pay  = mAlertDialog.Accounts_UPDEL_Pay.text.toString()

                    if (username.isBlank()){
                        Toast.makeText(mAlertDialog.context,"Username is Invalid Make sure all Information is Entered",Toast.LENGTH_SHORT).show()
                    }else if (Password.isBlank()){
                        Toast.makeText(mAlertDialog.context,"Staff Number is Invalid Make sure all Information is Entered",Toast.LENGTH_SHORT).show()
                    }else if (role !== Password){
                        if (checkAccounts(username,Password.toInt())) {
                            if (!UpdateAccount(username, Password.toInt(), Pay.toDouble(), Role)){
                                Updated = true
                                mAlertDialog.cancel()

                            }
                        }
                    }else{
                        Toast.makeText(mAlertDialog.context,"Staff Number is already in Use Please Enter Unused Staff Number ",Toast.LENGTH_SHORT).show()
                    }

                }



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

public fun AccountsUpdated():Boolean{


    return Updated

}