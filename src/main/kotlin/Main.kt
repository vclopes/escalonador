import model.Process
import model.ProcessManager

fun main(args: Array<String>) {
    val quantum = 1000;

    val process1 = Process(pid = 1, totalProcessingTime = 8000)
    val process2 = Process(pid = 2, totalProcessingTime = 500)
    val process3 = Process(pid = 3, totalProcessingTime = 3000)
    val process4 = Process(pid = 4, totalProcessingTime = 200)
    val process5 = Process(pid = 5, totalProcessingTime = 6000)
    val process6 = Process(pid = 6, totalProcessingTime = 10000)

    val processManager = ProcessManager(arrayListOf(process1, process2, process3, process4, process5, process6))

    System.out.printf(
        "%-20s%-30s%-20s%-20s%-20s%-20s%s%n", "PID", "Executed Processing Time", "PC", "State", "I/O",
        "CPU", "Transition"
    );
    println()

    while (!processManager.isFinished()) {
        val currentProcess = processManager.nexProcess()

        if (currentProcess != null) {
            if (currentProcess.isWaiting()) {
                currentProcess.checkIfProcessIsAvailable()
                println(currentProcess.toString() + "WAITING -> " + currentProcess.state)
                continue
            }

            currentProcess.setStateRunning()
            println(currentProcess.toString() + "READY -> RUNNING")
            for (i in 0..quantum) {
                currentProcess.execute()
                if (currentProcess.isTerminated() || currentProcess.isWaiting()) {
                    break
                }
            }

            if (currentProcess.isRunning()) {
                currentProcess.setStateReady()
            }

            println(currentProcess.toString() + "RUNNING -> " + currentProcess.state)
        }
    }

}