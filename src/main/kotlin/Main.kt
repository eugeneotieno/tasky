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