package model

import kotlin.random.Random
import kotlin.random.nextInt


class Process(
    val pid: Int,
    val totalProcessingTime: Int,
) {
    var executedProcessingTime: Int = 0
    var counter: Int = 1
    var operations: Int = 0
    var cpuUsage: Int = 0
    var state: State = State.NEW

    enum class State(val text: String) {
        NEW("NEW"),
        READY("READY"),
        RUNNING("RUNNING"),
        WAITING("WAITING"),
        TERMINATED("TERMINATED"),
    }

    fun checkIfProcessIsAvailable() {
        val randomNumber = Random.nextInt(100)
        if (randomNumber < 30) {
            setStateReady()
        }
    }

    fun execute() {
        if (isTerminated()) {
            return
        }

        executedProcessingTime++
        counter++

        if (executedProcessingTime == totalProcessingTime) {
            setStateTerminated()
            return
        }

        val randomNumber = Random.nextInt(100)
        if (randomNumber < 5) {
            setStateWaiting()
        }

    }

    fun setStateReady() {
        state = State.READY
    }

    fun setStateRunning() {
        state = State.RUNNING
        cpuUsage++
    }

    fun setStateWaiting() {
        state = State.WAITING
        operations++
    }

    fun setStateTerminated() {
        state = State.TERMINATED
    }

    fun isTerminated() = state == State.TERMINATED

    fun isWaiting() = state == State.WAITING

    fun isRunning() = state == State.RUNNING

    override fun toString(): String {
        return String.format(
            "%-20d%-30d%-20d%-20s%-20d%-20d", pid, executedProcessingTime, counter, state.toString(),
            operations, cpuUsage
        );
    }

}