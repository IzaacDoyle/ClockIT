package doyle.izaac.clockit.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import doyle.izaac.clockit.R
import doyle.izaac.clockit.ViewModel.AccountViewModel
import doyle.izaac.clockit.helpers.AccountRecycleAdaptor

import doyle.izaac.clockit.helpers.SaveImageLocally
import doyle.izaac.clockit.helpers.showImagePicker
import doyle.izaac.clockit.main.MainApp
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.manager_main.*

class ManagerActionsActivity: AppCompatActivity() {
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1





    var accounts = AccountModel()
    private lateinit var viewModel: AccountViewModel
    lateinit var search : MenuItem



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_main)



        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        viewModel.account.observe(this, { it ->

            val myAdaptor = AccountRecycleAdaptor(it,applicationContext)
          //  Account_Recycle_View.layoutManager = LinearLayoutManager(applicationContext)
            Account_Recycle_View.layoutManager = GridLayoutManager(applicationContext , 2)
            Account_Recycle_View.adapter = myAdaptor
           // Account_Recycle_View.adapter!!.notifyDataSetChanged()

            val searchview = search.actionView as SearchView
            searchview.queryHint = "Search Accounts"


            searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(searchview.isEmpty()){
                        viewModel.getProducts()
                    }else{
                        if (query.isNullOrBlank()){
                            Toast.makeText(
                                    applicationContext,
                                    "Error With Search",
                                    Toast.LENGTH_SHORT
                            ).show()
                            viewModel.getProducts()
                        }else{
                            viewModel.SearchProductsName(query.toLowerCase(),"Username","WOP")

                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (searchview.isEmpty()){
                        viewModel.getProducts()
                    }else{
                        if (newText.isNullOrBlank()){
                            viewModel.getProducts()
                        }else{
                            viewModel.SearchProductsName(newText.toLowerCase(),"Username","WOP")
                        }
                    }
                    return true
                }

            })

        }  )

        // TODO Add to Menu so Search account Selection for WOP, WP, Manager







    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_manager, menu)

        search = menu?.findItem(R.id.accounts_search)!!


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

                    SaveImageLocally(this, data.data.toString(),"HomeImage")

                   // readImage(this,resultCode,data)
                }
            }
        }
    }






}