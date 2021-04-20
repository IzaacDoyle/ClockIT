package doyle.izaac.clockit.main

import android.app.Application
import doyle.izaac.clockit.models.AccountMemStore
import doyle.izaac.clockit.models.AccountStore

import doyle.izaac.clockit.models.ClockMemStore

class MainApp : Application() {
    val clockin = ClockMemStore()
    val account = AccountMemStore()

}