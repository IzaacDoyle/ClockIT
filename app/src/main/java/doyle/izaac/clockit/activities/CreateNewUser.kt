package doyle.izaac.clockit.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import doyle.izaac.clockit.Firebase.CreateUser
import doyle.izaac.clockit.Firebase.checkAccounts
import doyle.izaac.clockit.R
import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.new_user_create.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class CreateNewUser: AppCompatActivity() {
    var accounts = AccountModel()
    lateinit var app: MainApp
    var spinnerText : String = ""
    val MANGER_RESULT = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_user_create)

        app = application as MainApp

        val roleSelect = resources.getStringArray(R.array.RoleSelect)
        val spinner = findViewById<Spinner>(R.id.New_ScrollSelectRole)
        if (spinner != null) {
            val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roleSelect)
            spinner.adapter = adaptor

        }

        New_ScrollSelectRole.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent != null) {
                    if (position == 0) {
                        return
                    } else {
                        spinnerText = parent.getItemAtPosition(position).toString()
                        Log.d("spinner", spinnerText)

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        New_user_Add.setOnClickListener {

            val context = applicationContext
            accounts.Username = New_Username.text.toString()
            accounts.Password = New_Staff_Num.text.toString().toInt()
            accounts.Role = spinnerText.toString()
            accounts.Pay = New_Pay.text.toString().toDouble()
            Log.d("Check", accounts.Password.toString())

            if (accounts.Username.isBlank()) {
                toast("Please Enter Username")
            }else if (accounts.Password.toString().isBlank()) {
                toast("Please Enter Staff ID")
            }else if (checkAccounts(accounts.Username,accounts.Password)) {
                if (accounts.Role.isBlank()) {
                    toast("Please Select staff Role ${accounts.Role}")
                } else if (accounts.Pay.toString().isBlank()) {
                    accounts.Pay = 0.0
                    app.account.Create(applicationContext, accounts.copy())
                    startActivityForResult(intentFor<ManagerActionsActivity>(), MANGER_RESULT)
                    finish()
                } else {
                    app.account.Create(applicationContext, accounts.copy())
                    startActivityForResult(intentFor<ManagerActionsActivity>(), MANGER_RESULT)
                    finish()
                }
            }else{
                toast("Staff Number already in use")
            }
        }

    }

    override fun onBackPressed() {
        startActivityForResult(intentFor<ManagerActionsActivity>(),MANGER_RESULT)
        finish()
        super.onBackPressed()
    }



}