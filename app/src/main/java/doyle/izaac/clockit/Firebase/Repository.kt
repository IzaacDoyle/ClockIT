package doyle.izaac.clockit.Firebase

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import doyle.izaac.clockit.models.AccountModel
import kotlinx.android.synthetic.main.new_user_create.*


var managercheck : Boolean = false

public fun CreateUser(Context: Context, Username: String, Password : Int,Pay : Long,Role: String) {
    val db = FirebaseFirestore.getInstance()
    var user = hashMapOf(

        "UserName" to Username.toLowerCase(),
        "Password" to Password,
        "Role" to Role,
        "Pay" to Pay

    )

    db.collection("Accounts/Users/${Role}WPL").document().get()
        .addOnCompleteListener {  task ->
            if (task.result?.exists() == true){
                    Log.d("account Ex", "account Exists with Username")
                } else {
                    if (Pay == 0L) {
                        db.collection("Accounts/Users/${Role}WPL").document().set(user)
                            .addOnSuccessListener {
                                Log.d(
                                    "created account without Pay",
                                    "Account created with name of ${Username}"
                                )
                            }
                    } else if (Role == "Manager") {

                        db.collection("Accounts/Users/${Role}").document().set(user)
                            .addOnSuccessListener {
                                Log.d(
                                    "created account with Pay",
                                    "Account created with name of ${Username}"
                                )
                            }

                    } else {
                        db.collection("Accounts/Users/${Role}").document().set(user)
                            .addOnSuccessListener {
                                Log.d(
                                    "created account with Pay",
                                    "Account created with name of ${Username}"
                                )
                            }
                    }

                }
            }


}








public fun ManagerCheck(username : String, password : Int ){
    val db = FirebaseFirestore.getInstance()

managercheck = true


}