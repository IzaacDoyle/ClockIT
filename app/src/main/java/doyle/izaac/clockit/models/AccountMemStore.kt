package doyle.izaac.clockit.models

import android.util.Log
import doyle.izaac.clockit.Firebase.CreateUser
import org.jetbrains.anko.info

class AccountMemStore: AccountStore {
    val Accounts = ArrayList<AccountModel>()


    override fun findAll(): List<AccountModel> {
        return Accounts
    }


    override fun Create(account: AccountModel) {
        Accounts.add(account)
        logAll()
    }

    override fun Delete() {
        TODO("Not yet implemented")
    }

    override fun Save(account: AccountModel) {
        TODO("Not yet implemented")
    }
    fun logAll() {
        Accounts.forEach { Log.d("accounts","${it}") }
    }
}