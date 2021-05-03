package doyle.izaac.clockit.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import doyle.izaac.clockit.R
import doyle.izaac.clockit.ViewModel.AccountViewModel
import doyle.izaac.clockit.helpers.*


import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.manager_main.*
import org.jetbrains.anko.intentFor

class ManagerActionsActivity: AppCompatActivity() {

    lateinit var app: MainApp
    private val IMAGE_REQUEST = 1
    private val CREATE_USER_REQUEST = 2
    var accounts = AccountModel()
    private lateinit var viewModel: AccountViewModel
   // lateinit var search : MenuItem




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_main)
        app = application as MainApp



        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        viewModel.account.observe(this, { it ->

            val myAdaptor = AccountRecycleAdaptor(it,applicationContext)
          //  Account_Recycle_View.layoutManager = LinearLayoutManager(applicationContext)

            Account_Recycle_View.layoutManager = GridLayoutManager(applicationContext , 2)
            Account_Recycle_View.adapter = myAdaptor
            Account_Recycle_View.adapter!!.notifyDataSetChanged()


            if (AccountsUpdated()){
                viewModel.getAccounts()
                Account_Recycle_View.adapter!!.notifyDataSetChanged()
                Updated = false
            }






/*
            val  searchview = viewModel.search?.actionView as SearchView?
            if (searchview != null) {
                searchview.queryHint = "Search Accounts"
            }

            searchview!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(searchview.isEmpty()){
                        viewModel.getAccounts()
                    }else{
                        if (query.isNullOrBlank()){
                            Toast.makeText(
                                    applicationContext,
                                    "Error With Search",
                                    Toast.LENGTH_SHORT
                            ).show()
                            viewModel.getAccounts()
                        }else{
                         //   viewModel.SearchProductsName(query.toLowerCase(),"Username","WOP")

                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (searchview.isEmpty()){
                        viewModel.getAccounts()
                    }else{
                        if (newText.isNullOrBlank()){
                            viewModel.getAccounts()
                        }else{
                         //   viewModel.SearchProductsName(newText.toLowerCase(),"Username","WOP")
                        }
                    }
                    return true
                }

            })

 */

        }  )

        // TODO Add to Menu so Search account Selection for WOP, WP, Manager







    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_manager, menu)

       // viewModel.search = menu?.findItem(R.id.accounts_search)!!


        return true



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when(item.itemId){
        R.id.Manager_Set_Main_Image -> {
            showImagePicker(this, IMAGE_REQUEST)
        }
        R.id.AddUser -> {
            startActivityForResult(intentFor<CreateNewUser>(),CREATE_USER_REQUEST)
            finish()

        }
        R.id.Manager_Email_Data -> {

        }

    }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    SaveDataLocally(this, data.data.toString(), "HomeImage")

                }
            }
            CREATE_USER_REQUEST -> {
                if (data != null) {
                    // viewModel.getAccounts()
                    Log.d("CREATE_USER_REQUEST","Return data")
                    Account_Recycle_View.adapter!!.notifyDataSetChanged()
                }else{
                    Log.d("CREATE_USER_REQUEST","Return null")
                }
            }
        }
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("Image","ReadImage")
        setResult(Activity.RESULT_OK,resultIntent)
        finishActivity(RESULT_OK)
        
        super.onBackPressed()


      // val intent = Intent(this,ClockActivity::class.java)
       // startActivity(intent)
    }






}