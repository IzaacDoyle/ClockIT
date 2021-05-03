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
var checkTF :Boolean = false
private val db = FirebaseFirestore.getInstance()
var PayeeStatus : String = ""

fun CreateUser(context: Context, Username: String, Password : Int,Pay : Double,Role: String):Boolean {

    var exists: Boolean = false
    if (Pay == 0.0){
         PayeeStatus  = "WithOutPay"
    }else{
        PayeeStatus = "WithPay"
    }

    val user = hashMapOf(

            "Username" to Username.toLowerCase(),
            "Password" to Password,
            "Role" to Role,
            "Pay" to Pay,
            "PayStatus" to PayeeStatus

    )

    if (checkAccounts(Username, Password)) {
        db.collection("Accounts/Users/Staff").document(Password.toString()).set(user)
                .addOnSuccessListener {
                    Log.d(
                            "created account ",
                            "Account created with name of ${Username}"
                    )
                }

    }else{
        exists = true
    }
    return exists
    }


/*

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


 */

fun UpdateAccount(Username: String, Password : Int,Pay : Double,Role: String):Boolean{

    var isSuccess : Boolean = false
    var PayStatus = ""

    val map = mutableMapOf<String, Any>()

    if (Username.isNotEmpty()){
        map["Username"] = Username
    }
   /* if(Password.toString().isNotEmpty()){
        map["Password"] = Password.toInt()
        Log.d("MapPassword", Password.toString())
    }
        */
    if (Role.isNotEmpty()){

        map["Role"] = Role
    }
    if (Pay.toString().isNotEmpty()){
        map["Pay"] = Pay
        if (Pay > 0){
            PayStatus = "WithPay"
        }else{
            PayStatus = "WithOutPay"
        }
    }
    if (PayStatus.isNotEmpty()){
        map["PayStatus"] = PayStatus
    }
    Log.d("Map", map.toString())


    db.collection("Accounts/Users/Staff").document(Password.toString()).update(map)
        .addOnSuccessListener {
            // account.delete()
            isSuccess = true
        }

    return isSuccess

}

fun DeleteUser(Username: String, Password : Int):Boolean{
    var isSuccess : Boolean = false
   db.collection("Accounts/Users/Staff").document(Password.toString())
           .delete()
            .addOnSuccessListener {
                isSuccess = true
    }

    Log.d("accountsSingle","$Username Deleted")



    return  isSuccess
}


fun ManagerCheck(username : String, password : Int ):Boolean{

    var checkTF = true

    db.collection("Accounts/Users/Staff").whereEqualTo("Username", username).whereEqualTo("Password", password).get()
            .addOnSuccessListener {
                Log.d("ManagerCheck", "Account Clocked in ")

                checkTF = true
            }
            .addOnFailureListener {
                Log.d("ManagerCheckf", " false Account not there ")



            }
    Log.d("ManagerCheckf", " $checkTF")
    return checkTF
   // return checkTF
}


// fix where check acocunt works
fun checkAccounts(Username: String,Password: Int):Boolean{
        db.collection("Accounts/Users/Staff").document(Password.toString()).get()
                .addOnSuccessListener {
                    if (it["Password"] != Password) {
                        checkTF = true

                        Log.d("Check", "Account is there $checkTF  ${it.toString()}")


                    }
                }
                .addOnFailureListener {
                    Log.d("Check", "Account not there ")



    }

Log.d("checktf","if false no Account if True Accout there $checkTF")
    return checkTF
}
