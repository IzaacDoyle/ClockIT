package doyle.izaac.clockit.models

interface AccountStore {
    fun findAll(): List<AccountModel>
    fun Create(account: AccountModel)
    fun Delete()
    fun Save(account: AccountModel)
}