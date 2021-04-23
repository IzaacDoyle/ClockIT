package doyle.izaac.clockit.Firebase

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import doyle.izaac.clockit.models.AccountModel
import doyle.izaac.clockit.models.ClockInModel
import doyle.izaac.clockit.models.ClockedAccounts
import kotlinx.android.synthetic.main.new_user_create.*


var managercheck : Boolean = false

fun CreateUser(Context: Context, Username: String, Password : Int,Pay : Double,Role: String) {
    val db = FirebaseFirestore.getInstance()
    var user = hashMapOf(

        "Username" to Username.toLowerCase(),
        "Password" to Password,
        "Role" to Role,
        "Pay" to Pay

    )

    if (Role == "Manager") {

        db.collection("Accounts/Users/${Role}").document().set(user)
            .addOnSuccessListener {
                Log.d(
                    "created account with Pay",
                    "Account created with name of ${Username}"
                )
            }
    } else if (Pay == 0.0) {
        db.collection("Accounts/Users/WOP").document().set(user)
            .addOnSuccessListener {
                Log.d(
                    "created account without Pay",
                    "Account created with name of ${Username}"
                )
            }
    } else {
        db.collection("Accounts/Users/WP").document().set(user)
            .addOnSuccessListener {
                Log.d(
                    "created account with Pay",
                    "Account created with name of ${Username}"
                )
            }
    }
}












fun ManagerCheck(username : String, password : Int ){
    val db = FirebaseFirestore.getInstance()

managercheck = true


}

fun checkAccounts(Username: String,password: Int):Boolean{
    val db = FirebaseFirestore.getInstance()
    val accounts = ArrayList<ClockedAccounts>()
    db.collection("Accounts/Users/WOP").get()
            .addOnCompleteListener { snapshot ->
                if (snapshot.isSuccessful){
                    for (document in snapshot.result!!){
                        val username = document.getString("Username").toString()
                        val password = document.get("Password").toString()
                        var Account = ClockedAccounts(username.toString(),password.toString().toInt(),false)
                        accounts?.add(Account)
                    }
                    Log.d("Accounts", accounts.toString())
                }
            }
    if (accounts.contains(ClockedAccounts(Username,password,false))){
        return true
    }
    return false
}
/*
fun CheckAccount(Username: String,Password: Int){
    val db = FirebaseFirestore.getInstance()
    var listofLoginStaff:ArrayList<AccountModel>?=null
    var listofLoginmanager:ArrayList<AccountModel>?=null

        db.collection("Accounts/Users/WOP")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {

                            val staffNumber = document.getString("staffNumber").toString()
                            val usernames = document.getString("Username").toString()
                            var Account = AccountModel(usernames.toString(), staffNumber.toString())
                            listofLoginStaff?.add(Account)

                        }
                        Log.d("Account","$listofLoginStaff")
                    }
                }

        db.collection("Accounts/Users/Manager")
                .get()
                .addOnCompleteListener{
                    task ->
                    if (task.isSuccessful){
                        for (document in task.result!!) {

                            val staffNumber = document.getString("staffNumber")
                            val usernames = document.getString("Username")
                            var Account = AccountModel(usernames.toString(), staffNumber.toString())
                            listofLoginmanager?.add(Account)
                        }
                        Log.d("Account","$listofLoginmanager")

                    }
                }

    db.collection("Accounts/Users/WP")
            .get()
            .addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    for (document in task.result!!) {

                        val staffNumber = document.getString("staffNumber")
                        val usernames = document.getString("Username")
                        var Account = AccountModel(usernames.toString(), staffNumber.toString())
                        listofLoginmanager?.add(Account)
                    }
                    Log.d("Account","$listofLoginmanager")

                }
            }
    }

 */

