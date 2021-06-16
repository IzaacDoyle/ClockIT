package doyle.izaac.clockit.models

import android.content.Context

interface AccountStore {
    fun findAll(): List<AccountModel>
    fun Create(context: Context,account: AccountModel)
    fun Delete(account: AccountModel)
    fun Update(account: AccountModel)
}