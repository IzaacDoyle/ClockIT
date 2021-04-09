package doyle.izaac.clockit.models

class ClockMemStore: ClockStore {

    val clockin = ArrayList<ClockInModel>()

    override fun findAll(): List<ClockInModel> {
        return clockin

    }

    override fun create(ClockIn: ClockInModel) {
        clockin.add(ClockIn)
    }

    override fun Delete() {
        TODO("Not yet implemented")
    }
}