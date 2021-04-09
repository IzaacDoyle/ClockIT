package doyle.izaac.clockit.models

class AccountMemStore: AccountStore {
    val Accounts = ArrayList<AccountModel>()


    override fun findAll(): List<AccountModel> {
        return Accounts
    }


    override fun Create(account: AccountModel) {

        Accounts.add(account)
    }

    override fun Delete() {
        TODO("Not yet implemented")
    }

    override fun Save(account: AccountModel) {
        TODO("Not yet implemented")
    }
}