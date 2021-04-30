package doyle.izaac.clockit.ViewModel

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import doyle.izaac.clockit.models.AccountModel

class AccountViewModel : ViewModel() {

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var accounts: MutableLiveData<ArrayList<AccountModel>> = MutableLiveData<ArrayList<AccountModel>>()




    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        getProducts()

    }

    fun getProducts() {
        val allAccounts = ArrayList<AccountModel>()

        firestore.collection("Accounts/Users/WP").addSnapshotListener{
            snapshot, e->

            if (e != null){
                Log.d("Account","Failed", e)
                return@addSnapshotListener
            }

            if (snapshot != null){
                val documents =    snapshot.documents
                documents.forEach{
                    val account= it.toObject(AccountModel::class.java)
                    if (account !=null)
                        allAccounts.add(account)
                }
            }
            accounts.value = allAccounts
            Log.d("Account","all prod $allAccounts")
        }
        firestore.collection("Accounts/Users/WOP").addSnapshotListener{
                snapshot, e->

            if (e != null){
                Log.d("Account","Failed", e)
                return@addSnapshotListener
            }

            if (snapshot != null){
                val documents =    snapshot.documents
                documents.forEach{
                    val account= it.toObject(AccountModel::class.java)
                    if (account !=null)
                        allAccounts.add(account)
                }
            }
            accounts.value = allAccounts
            Log.d("Account","all prod $allAccounts")
        }
        firestore.collection("Accounts/Users/Manager").addSnapshotListener{
                snapshot, e->

            if (e != null){
                Log.d("Account","Failed", e)
                return@addSnapshotListener
            }

            if (snapshot != null){
                val documents =    snapshot.documents
                documents.forEach{
                    val account= it.toObject(AccountModel::class.java)
                    if (account !=null)
                        allAccounts.add(account)
                }
            }
            accounts.value = allAccounts
            Log.d("Account","all prod $allAccounts")
        }


    }



    fun SearchProductsName(Username:String, Field:String, Listfrom: String) {
        var allAccounts :ArrayList<AccountModel> = ArrayList<AccountModel>()


        firestore.collection("Accounts/Users/$Listfrom").orderBy(Field)
                .startAt(Username).endAt("$Username\uf8ff")
                .get()
                .addOnSuccessListener { snapshot ->
                    val searchAccount = ArrayList<AccountModel>()
                    Log.d("Here", "1")
                    if (snapshot != null) {
                        Log.d("Here", "2")
                        val documents = snapshot.documents
                        documents.forEach {
                            Log.d("AccountSearch", it.toString())
                            val sproducts = it.toObject(AccountModel::class.java)
                            if (sproducts != null) {
                                searchAccount.add(sproducts)
                                Log.d("SearchsProd", "$sproducts")
                            } else {
                                Log.d("ErrorSearchsProd", "$sproducts")
                            }
                        }
                    } else {
                        Log.d("ErrorSearchsProd", "$snapshot")
                    }
                   // allAccounts.add(AccountModel(searchAccount.toString()))
                    //allAccounts.add()
                    accounts.value = searchAccount

                    //   Log.d("Search", searchAccount.toString())
                    //  Log.d("Search2", accounts.value.toString())

                    if (Username.isEmpty() || Username.isNullOrBlank()) {
                        getProducts()

                    }


                }


        firestore.collection("Accounts/Users/WP").orderBy(Field)
                .startAt(Username).endAt("$Username\uf8ff")
                .get()
                .addOnSuccessListener { snapshot ->
                    val searchAccount = ArrayList<AccountModel>()
                    Log.d("Here", "1")
                    if (snapshot != null) {
                        Log.d("Here", "2")
                        val documents = snapshot.documents
                        documents.forEach {
                            Log.d("AccountSearch", it.toString())
                            val sproducts = it.toObject(AccountModel::class.java)
                            if (sproducts != null) {
                                searchAccount.add(sproducts!!)
                                Log.d("SearchsProd", "$sproducts")
                            } else {
                                Log.d("ErrorSearchsProd", "$sproducts")
                            }
                        }
                    } else {
                        Log.d("ErrorSearchsProd", "$snapshot")
                    }
                   // allAccounts.add(AccountModel(searchAccount.toString()))
                    accounts.value = searchAccount
                    //   Log.d("Search", searchAccount.toString())
                    //  Log.d("Search2", accounts.value.toString())

                    if (Username.isEmpty() || Username.isNullOrBlank()) {
                        getProducts()

                    }
                }
        firestore.collection("Accounts/Users/Manager").orderBy(Field)
                .startAt(Username).endAt("$Username\uf8ff")
                .get()
                .addOnSuccessListener { snapshot ->
                    val searchAccount = ArrayList<AccountModel>()
                    Log.d("Here", "1")
                    if (snapshot != null) {
                        Log.d("Here", "2")
                        val documents = snapshot.documents
                        documents.forEach {
                            Log.d("AccountSearch", it.toString())
                            val sproducts = it.toObject(AccountModel::class.java)
                            if (sproducts != null) {
                                searchAccount.add(sproducts!!)
                                Log.d("SearchsProd", "$sproducts")
                            } else {
                                Log.d("ErrorSearchsProd", "$sproducts")
                            }
                        }
                    } else {
                        Log.d("ErrorSearchsProd", "$snapshot")
                    }
                  //  allAccounts.add(AccountModel(searchAccount.toString()))

                    // accounts.value = searchAccount
                    //   Log.d("Search", searchAccount.toString())
                    //  Log.d("Search2", accounts.value.toString())

                    if (Username.isEmpty() || Username.isNullOrBlank()) {
                        getProducts()

                    }
                }
       // accounts.value = allAccounts
        Log.d("Accounts", allAccounts.toString())

    }







    internal var account: MutableLiveData<ArrayList<AccountModel>>
        get() {return  accounts }
        set(value){ accounts = value}
}




