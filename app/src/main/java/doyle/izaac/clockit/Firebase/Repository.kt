package doyle.izaac.clockit.Firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import doyle.izaac.clockit.activities.ManagerActionsActivity
import doyle.izaac.clockit.models.AccountModel
import doyle.izaac.clockit.models.ClockInModel
import doyle.izaac.clockit.models.ClockedAccounts
import kotlinx.android.synthetic.main.new_user_create.*
import kotlin.coroutines.coroutineContext


var managercheck : Boolean = false

fun CreateUser(context: Context, Username: String, Password : Int,Pay : Double,Role: String):Boolean {
    val db = FirebaseFirestore.getInstance()
    var exists :Boolean = false

    var user = hashMapOf(

        "Username" to Username.toLowerCase(),
        "Password" to Password,
        "Role" to Role,
        "Pay" to Pay

    )

   if (!checkAccounts(Username,Password)) {


       if (Role == "Manager") {

           db.collection("Accounts/Users/Manager").document(Password.toString()).get().addOnSuccessListener {

               Toast.makeText(context, "$Username with Staff Number $Password already Exists", Toast.LENGTH_SHORT).show()

           }.addOnFailureListener {
               db.collection("Accounts/Users/Manager").document(Password.toString()).set(user)
                       .addOnSuccessListener {
                           Log.d(
                                   "created account with Pay",
                                   "Account created with name of ${Username}"
                           )
                       }
           }


       } else if (Pay == 0.0) {
           db.collection("Accounts/Users/WOP").document(Password.toString()).get().addOnSuccessListener {
               Toast.makeText(context, "$Username with Staff Number $Password already Exists", Toast.LENGTH_SHORT).show()
           }.addOnFailureListener {
               db.collection("Accounts/Users/WOP").document(Password.toString()).set(user)
                       .addOnSuccessListener {
                           Log.d(
                                   "created account without Pay",
                                   "Account created with name of ${Username}"
                           )
                       }
           }
       } else {
           db.collection("Accounts/Users/WP").document(Password.toString()).get().addOnSuccessListener {
               Toast.makeText(context, "$Username with Staff Number $Password already Exists", Toast.LENGTH_SHORT).show()
           }.addOnFailureListener {
               db.collection("Accounts/Users/WP").document(Password.toString()).set(user)
                       .addOnSuccessListener {
                           Log.d(
                                   "created account with Pay",
                                   "Account created with name of ${Username}"
                           )
                       }
           }
       }
   }else{
       exists = true
   }
    return exists
}

fun UpdateAccount(Username: String, Password : Int,Pay : Double,Role: String):Boolean{
    val db = FirebaseFirestore.getInstance()
    val account = db.collection("Accounts/Users/Manager").document(Password.toString())
    Log.d("accountsSingle",account.toString())
    var isSuccess : Boolean = false

    val map = mutableMapOf<String, Any>()

    if (Username.isNotEmpty()){
        map["Username"] = Username
    }
    if(Password.toString().isNotEmpty()){
        map["Password"] = Password
    }
    if (Role.isNotEmpty()){

        map["Role"] = "Manager"
    }
    if (Pay.toString().isNotEmpty()){
        map["Pay"] = Pay
    }
    Log.d("Map", map.toString())



        db.collection("Accounts/Users/Manager").document(Password.toString()).set(map).addOnSuccessListener {
       // account.delete()
      isSuccess = true
    }

    return isSuccess

}

fun DeleteUser(Username: String, Password : Int,Pay : Double,Role: String):Boolean{
    val db = FirebaseFirestore.getInstance()
    val account = db.collection("Accounts/Users/Manager").document(Password.toString())
    Log.d("accountsSingle",account.toString())
    var isSuccess : Boolean = false

    account.delete().addOnSuccessListener {
        isSuccess = true
    }
    return  isSuccess
}












fun ManagerCheck(username : String, password : Int ){
    val db = FirebaseFirestore.getInstance()

managercheck = true


}



fun checkAccounts(Username: String,Password: Int):Boolean{
    val db = FirebaseFirestore.getInstance()
    var checkTF = false
    var SubDataCheck :String = ""
    repeat(3){
      var count: Int = 0
        if (count == 0){
            SubDataCheck = "WOP"
        }else if (count == 1){
            SubDataCheck = "WP"
        }else if (count == 2){
            SubDataCheck = "Manager"
        }
        db.collection("Accounts/Users/$SubDataCheck").whereEqualTo("Username", Username).whereEqualTo("Password", Password).get()
                .addOnSuccessListener {
                    Log.d("Check", "Account Clocked in ")
                    checkTF = true
                }
                .addOnFailureListener {
                    Log.d("Check", "Account not there ")
                    count ++
                }

    }


    return checkTF
}
