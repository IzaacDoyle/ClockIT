package doyle.izaac.clockit.ViewModel

import android.icu.util.LocaleData
import doyle.izaac.clockit.models.ClockedAccounts



import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings


import doyle.izaac.clockit.models.AccountModel
import java.time.Instant.now
import java.util.*
import kotlin.collections.ArrayList


class ClockedViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    private var accounts: MutableLiveData<ArrayList<ClockedAccounts>> = MutableLiveData<ArrayList<ClockedAccounts>>()



    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        getClocked()

    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun getClocked() {
        // var pw =  accountscheck.get
        firestore.collection("Accounts/Users/Clocked").addSnapshotListener{
            snapshot, e->

            if (e != null){
                Log.d("Account","Failed", e)
                return@addSnapshotListener
            }
            val AllClocked = ArrayList<ClockedAccounts>()
            if (snapshot != null){
                val documents =    snapshot.documents
                documents.forEach{
                    val account= it.toObject(ClockedAccounts::class.java)
                    if (account !=null)
                        AllClocked.add(account)
                }
            }

            accounts.value = AllClocked
            Log.d("Account","all prod $AllClocked")
        }


    }




    internal var account: MutableLiveData<ArrayList<ClockedAccounts>>
        get() {return  accounts }
        set(value){ accounts = value}
}


