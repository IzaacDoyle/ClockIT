package doyle.izaac.clockit.models

interface ClockStore {
    fun findAll(): List<ClockInModel>
    fun create(ClockIn : ClockInModel)
    fun Delete()
}