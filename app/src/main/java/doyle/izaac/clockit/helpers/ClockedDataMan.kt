package doyle.izaac.clockit.helpers

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import doyle.izaac.clockit.models.ClockedAccounts
import doyle.izaac.clockit.models.ClockedManagmentModel

var Clockeddata= ArrayList<ClockedManagmentModel>()


fun GetClocked(){
    val firestore = FirebaseFirestore.getInstance()
    firestore.collection("Accounts/Users/Clocked").addSnapshotListener{
            snapshot, e->

        if (e != null){
            Log.d("ClockedData","Failed", e)
            return@addSnapshotListener
        }
        val AllClocked = ArrayList<ClockedManagmentModel>()
        if (snapshot != null){
            val documents =    snapshot.documents
            documents.forEach{
                val account= it.toObject(ClockedManagmentModel::class.java)
                if (account !=null)
                    AllClocked.add(account)
            }
        }

        Clockeddata = AllClocked
        Log.d("ClockedData","all prod $AllClocked")
    }



}