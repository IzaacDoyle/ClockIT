package doyle.izaac.clockit.ViewModel

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import doyle.izaac.clockit.Firebase.accountscheck
import doyle.izaac.clockit.models.AccountModel
import java.util.*


class AccountViewModel : ViewModel() {

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var accounts: MutableLiveData<ArrayList<AccountModel>> = MutableLiveData<ArrayList<AccountModel>>()





    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

         getAccounts()

    }

  public  fun getAccounts() :ArrayList<AccountModel> {
      var allAccounts = ArrayList<AccountModel>()


      firestore.collection("Accounts/Users/Staff").addSnapshotListener { snapshot, e ->

          if (e != null) {
              Log.d("Account", "Failed", e)
              return@addSnapshotListener
          }

          if (snapshot != null) {
              val documents = snapshot.documents
              documents.forEach {
                  val account = it.toObject(AccountModel::class.java)
                  if (account != null){
                      allAccounts.add(account)
                      accountscheck.add(account)

                      }

              }
          }

           accounts.value = allAccounts
          Log.d("Account", "all prod $allAccounts")
      }
      return allAccounts
  }



    fun SearchAcconts(Username:String) {
        var allAccounts = ArrayList<AccountModel>()

        firestore.collection("Accounts/Users/Staff").orderBy("Username")
            .startAt(Username).endAt("${Username}\uf8ff")
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
                        Log.d("ErrorSearchsProd", "$snapshot")
                    }
                    accounts.value = searchAccount
                    if (Username.isEmpty()){
                        getAccounts()
                    }


                }
                    }


                }












    internal var account: MutableLiveData<ArrayList<AccountModel>>
        get() {return  accounts }
        set(value){ accounts = value}
}




