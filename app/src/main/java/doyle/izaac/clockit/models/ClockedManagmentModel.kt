package doyle.izaac.clockit.models

import java.sql.Date
import java.sql.Time

data class ClockedManagmentModel( val Username: String,val ClockedInTime : Int,val ClockedOutTime :Int, val Date: String){
    constructor(): this("",0,0,"")
}