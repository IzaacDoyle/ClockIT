package doyle.izaac.clockit.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import doyle.izaac.clockit.Firebase.CreateUser
import doyle.izaac.clockit.R
import doyle.izaac.clockit.helpers.SaveImage
import doyle.izaac.clockit.helpers.showImagePicker
import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.new_user_create.*
import org.jetbrains.anko.toast

class ManagerActionsActivity: AppCompatActivity() {
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1




    var accounts = AccountModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_main)












            /*
            if (New_Username != null){
                if (New_Staff_Num != null){


                   app.account.Create(a)
                }else{
                    Toast.makeText(this,"Please Enter Staff number",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please Enter Username",Toast.LENGTH_SHORT).show()
            }


        }

             */


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_manager, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when(item.itemId){
        R.id.Manager_Set_Main_Image -> {
            showImagePicker(this, IMAGE_REQUEST)
        }
        R.id.AddUser -> {
            val Intent = Intent(this,CreateNewUser::class.java)
            startActivity(Intent)
        }

    }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            IMAGE_REQUEST -> {
                if (data != null){

                    SaveImage(this, data.data.toString())

                   // readImage(this,resultCode,data)
                }
            }
        }
    }






}