package doyle.izaac.clockit.models

import android.content.Context
import android.util.Log
import doyle.izaac.clockit.Firebase.CreateUser
import doyle.izaac.clockit.Firebase.UpdateAccount
import doyle.izaac.clockit.activities.CreateNewUser
import org.jetbrains.anko.info

class AccountMemStore: AccountStore {
    val Accounts = ArrayList<AccountModel>()


    override fun findAll(): List<AccountModel> {
        return Accounts
    }


    override fun Create(context: Context,account: AccountModel) {
        CreateUser( context,account.Username, account.Password, account.Pay, account.Role)
        Accounts.add(account)
        logAll()
    }

    override fun Delete() {
      //  DeleteUser()
        TODO("Not yet implemented")
    }

    override fun Update(account: AccountModel) {
        UpdateAccount(account.Username,account.Password, account.Pay, account.Role)
    }


    fun logAll() {
        Accounts.forEach { Log.d("accounts","${it}") }
    }
}