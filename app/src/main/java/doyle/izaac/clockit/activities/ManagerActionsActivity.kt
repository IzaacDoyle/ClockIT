package layout

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import doyle.izaac.clockit.R
import doyle.izaac.clockit.helpers.SaveImage
import doyle.izaac.clockit.helpers.readImage
import doyle.izaac.clockit.helpers.readImageFromPath
import doyle.izaac.clockit.helpers.showImagePicker
import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.activity_main.*

class ManagerActionsActivity: AppCompatActivity() {
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1




    var accounts = AccountModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_main)
        val roleSelect = resources.getStringArray(R.array.RoleSelect)



            val spinner = findViewById<Spinner>(R.id.New_ScrollSelectRole)
            if (spinner != null) {
                val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roleSelect)
                spinner.adapter = adaptor
            }



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
          setContentView(R.layout.new_user_create)

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