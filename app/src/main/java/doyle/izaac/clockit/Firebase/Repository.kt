package doyle.izaac.clockit.Firebase


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import doyle.izaac.clockit.helpers.GetClocked
import doyle.izaac.clockit.models.AccountModel
import java.io.File
import java.net.URI
import java.time.LocalDate


var managercheck : Boolean = false
var bitmap: Bitmap? = null

public var accountscheck = ArrayList<AccountModel>()
public var check:Boolean = false
private val db = FirebaseFirestore.getInstance()
var PayeeStatus : String = ""
public var checkTF :Boolean = false
lateinit var info :String







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


        db.collection("Accounts/Users/Staff").document(Password.toString()).set(user)
                .addOnSuccessListener {
                    Log.d(
                            "created account ",
                            "Account created with name of ${Username}"
                    )
                    exists = true
                }


    return exists

    }


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
/*
fun checkAccounts(Username: String,Password: Int,Role: String,Pay: Double):String {
    info = "false"
    checkTF = !accountscheck.contains(AccountModel(Username, Password, Pay, Role))
    Log.d("accountcheck", accountscheck.toString() )
return info
}


 */
fun checkAccount(Username: String,Password: Int,Role: String,Pay: Double){


   val account = db.collection("Accounts/Users/Staff").document(Password.toString()).get()

        .addOnSuccessListener { doc ->
            if (!doc.exists()){
                check = true
                checkTF = false
                Log.d("CAccount!Exist",doc.toString())

            }
            if (doc.exists()){
                Log.d("CAccountExists",doc.toString())
                check = false
                checkTF = true

            }
        }
    Log.d("AccountCheck",check.toString())



}


fun ClockInAdd(context: Context,Username: String,Password: Int, Time: Long, ClockedIn :Boolean){

    val Account = db.collection("Accounts/Users/Staff").document(Password.toString())
    var timeIn: Long? =null
    var timeOut: Long? = null
    var data = LocalDate.now()

    val map = mutableMapOf<String, Any>()
    Account.get().addOnSuccessListener { doc ->
        if (doc.exists()) {
            var clockedin = doc.get("ClockedInTime")
            if (ClockedIn) {
                if (clockedin.toString().isBlank()) {
                    Toast.makeText(context, "Account Already clocked In", Toast.LENGTH_SHORT).show()
                } else {
                    timeIn = Time
                    val clocked = hashMapOf(
                        "Username" to Username,
                        "ClockedInTime" to timeIn,
                        "ClockedOutTime" to timeOut,
                        "clocked" to ClockedIn,
                        "Date" to data.toString()
                    )
                    //${Password.toString()}/${data.toString()}
                    db.collection("Accounts/Users/Clocked").document(Password.toString()).set(clocked)
                        .addOnSuccessListener {
                            Log.d("clocked", "added")
                        }
                }
            } else {
                timeOut = Time
                map["ClockedOutTime"] = timeOut!!
                map["clocked"] = ClockedIn
                db.collection("Accounts/Users/Clocked").document(Password.toString()).update(map)
                    .addOnSuccessListener {
                        Log.d("clocked", "added")
                    }
            }


        }
    }
}

fun UploadImageFB(FilePath:Uri) {
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("Image/MainScreenImage.jpg")

    storageRef.putFile(FilePath)
}

fun GetImageFB(): Bitmap?{
    val storageRef: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://clockit-127a2.appspot.com/Image/MainScreenImage.jpg")
    val localfile = File.createTempFile("MainScreenImage","jpg")

     storageRef.getFile(localfile).addOnSuccessListener {
          bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
     }
         .addOnFailureListener{
             bitmap = null
         }

return bitmap

}



