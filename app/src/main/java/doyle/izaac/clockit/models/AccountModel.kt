package doyle.izaac.clockit.models

data class AccountModel( var Username: String = "", var Password : Int = 0, var Pay : Double = 0.0, var Role: String = ""){
    constructor(): this("",0,0.0,"")
}