import kotlinx.datetime.*

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

fun setDate(): String {
    var date = ""
    var due = ""
    var getDate = true
    while (getDate) {
        getDate = false
        println("Input the date (yyyy-mm-dd):")
        val dateInput = readln().split("-")
        if (dateInput.size == 3) {
            try {
                val d = LocalDate(dateInput[0].toInt(), dateInput[1].toInt(), dateInput[2].toInt())
                val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
                val numberOfDays = currentDate.daysUntil(d)
                due = if (numberOfDays == 0) "T"
                else if (numberOfDays > 0) "I"
                else "O"
                date = d.toString()
            } catch (e: IllegalArgumentException) {
                println("The input date is invalid")
                getDate = true
            } catch (e: RuntimeException) {
                println("The input date is invalid")
                getDate = true
            }
        } else {
            println("The input date is invalid")
            getDate = true
        }
    }

    return "$date $due"
}

fun setTime(date: String): String {
    var time = ""
    var getTime = true
    while (getTime) {
        getTime = false
        println("Input the time (hh:mm):")
        val timeInput = readln().split(":")
        if (timeInput.size == 2) {
            try {
                val d = date.split("-")
                val t = LocalDateTime(
                    d[0].toInt(),
                    d[1].toInt(),
                    d[2].toInt(),
                    timeInput[0].toInt(),
                    timeInput[1].toInt()
                ).toString().split("T")
                time = t[1]
            } catch (e: IllegalArgumentException) {
                println("The input time is invalid")
                getTime = true
            } catch (e: RuntimeException) {
                println("The input time is invalid")
                getTime = true
            }
        } else {
            println("The input time is invalid")
            getTime = true
        }
    }

    return time
}