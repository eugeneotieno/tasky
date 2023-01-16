data class Task(var date: String, var time: String, var priority: String, var due: String, var tasks: String)

val taskList = mutableListOf<Task>()

fun main() {
}

fun showList() {
    for (i in taskList.indices) {
        val task = taskList[i]
        val num = i + 1
        if (num <= 9) {
            println("$num  ${task.date} ${task.time} ${task.priority} ${task.due}")
            val str = task.tasks.split("\n")
            for (s in str) {
                println("   $s")
            }
            println()
        } else {
            println("$num ${task.date} ${task.time} ${task.priority} ${task.due}")
            val str = task.tasks.split("\n")
            for (s in str) {
                println("   $s")
            }
            println()
        }
    }
}

fun setPriority(): String {
    var priority = ""
    var getPriority = true
    while (getPriority) {
        getPriority = false
        println("Input the task priority (C, H, N, L):")
        val inputP = readln()
        if (inputP.lowercase() == "c") {
            priority = inputP.uppercase()
        } else if (inputP.lowercase() == "h") {
            priority = inputP.uppercase()
        } else if (inputP.lowercase() == "n") {
            priority = inputP.uppercase()
        } else if (inputP.lowercase() == "l") {
            priority = inputP.uppercase()
        } else {
            getPriority = true
        }
    }

    return priority
}