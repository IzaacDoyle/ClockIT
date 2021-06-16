package doyle.izaac.clockit.models

import java.util.*

class ClockMemStore: ClockStore {

    val clockin = ArrayList<ClockInModel>()

    override fun findAll(): List<ClockInModel> {
        return clockin

    }

    override fun create(ClockIn: ClockInModel) {
        clockin.add(ClockIn)
    }

    override fun Delete() {

    }
}