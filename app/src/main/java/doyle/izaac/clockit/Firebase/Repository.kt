package doyle.izaac.clockit.Firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import doyle.izaac.clockit.models.AccountModel
import layout.ManagerActionsActivity


var db = FirebaseFirestore.getInstance()
var managercheck : Boolean = false

public fun CreateUser(account :AccountModel){

var user = hashMapOf(
        "ID" to account.ID,
        "UseerName" to account.Username.toLowerCase(),
        "Password" to account.Password,
        "Role" to account.Role,
        "Pay" to account.Pay

)

    if (account.Pay == 0){
        db.collection("Accounts/Users/${account.Role} Without Pay").document(account.ID).set(user)
                .addOnSuccessListener {
                    Log.d("created account without Pay", "Account created with name of ${account.Username}")
                }
    }else{

    db.collection("Accounts/Users/${account.Role}").document(account.ID).set(user)
            .addOnSuccessListener {
                Log.d("created account with Pay", "Account created with name of ${account.Username}")
            }

        }
}


public fun ManagerCheck(username : String, password : Int ){

managercheck = true


}