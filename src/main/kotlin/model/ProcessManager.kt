package model

class ProcessManager(
    val processes: List<Process>
) {

    var currentProcessIndex = 0

    fun nexProcess(): Process? {
        for (i in processes.indices) {
            val process = processes[currentProcessIndex]

            currentProcessIndex = if (currentProcessIndex < processes.size - 1) currentProcessIndex + 1 else 0

            if (!process.isTerminated()) {
                return process
            }
        }
        return null
    }

    fun isFinished(): Boolean {
        processes.forEach {
            if (!it.isTerminated()) {
                return false
            }
        }
        return true
    }

}