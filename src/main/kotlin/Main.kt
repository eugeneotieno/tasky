import kotlinx.datetime.*

data class Task(var date: String, var time: String, var priority: String, var due: String, var tasks: String)

val taskList = mutableListOf<Task>()

fun main() {
    var end = false
    do {
        println("Input an action (add, print, edit, delete, end):")
        when(readln().lowercase()) {
            "add" -> {
                val priority = setPriority()
                val d = setDate().split(" ")
                val date = d[0]
                val due = d[1]
                val time = setTime(date)
                val content = setTask()
                if (content.isBlank()) {
                    println("The task is blank")
                } else {
                    taskList.add(Task(date, time, priority, due, content))
                }
            }
            "print" -> {
                if (taskList.isEmpty()) {
                    println("No tasks have been input")
                } else {
                    showList()
                }
            }
            "edit" -> {
                if (taskList.isEmpty()) {
                    println("No tasks have been input")
                } else {
                    showList()
                    var getTaskNum = true
                    while (getTaskNum) {
                        getTaskNum = false
                        println("Input the task number (1-${taskList.size}):")
                        val n = readln()
                        try {
                            val num = n.toInt()
                            if (num >= 1 && num <= taskList.size) {
                                var getAction = true
                                while (getAction) {
                                    getAction = false
                                    println("Input a field to edit (priority, date, time, task):")
                                    when(readln()) {
                                        "priority" -> {
                                            val priority = setPriority()
                                            taskList[num - 1].priority = priority
                                            println("The task is changed")
                                        }
                                        "date" -> {
                                            val d = setDate().split(" ")
                                            val date = d[0]
                                            val due = d[1]
                                            taskList[num - 1].date = date
                                            taskList[num - 1].due = due
                                            println("The task is changed")
                                        }
                                        "time" -> {
                                            val time = setTime(taskList[num - 1].date)
                                            taskList[num - 1].time = time
                                            println("The task is changed")
                                        }
                                        "task" -> {
                                            val content = setTask()
                                            if (content.isBlank()) {
                                                println("The task is blank")
                                            } else {
                                                taskList[num - 1].tasks = content
                                                println("The task is changed")
                                            }
                                        }
                                        else -> {
                                            getAction = true
                                            println("Invalid field")
                                        }
                                    }
                                }
                            } else {
                                println("Invalid task number")
                                getTaskNum = true
                            }
                        } catch (e: NumberFormatException) {
                            println("Invalid task number")
                            getTaskNum = true
                        }
                    }
                }
            }
            "delete" -> {
                if (taskList.isEmpty()) {
                    println("No tasks have been input")
                } else {
                    showList()
                    var getTaskNum = true
                    while (getTaskNum) {
                        getTaskNum = false
                        println("Input the task number (1-${taskList.size}):")
                        val n = readln()
                        try {
                            val num = n.toInt()
                            if (num >= 1 && num <= taskList.size) {
                                taskList.removeAt(num - 1)
                                println("The task is deleted")
                            } else {
                                println("Invalid task number")
                                getTaskNum = true
                            }
                        } catch (e: NumberFormatException) {
                            println("Invalid task number")
                            getTaskNum = true
                        }
                    }
                }
            }
            "end" -> {
                println("Tasklist exiting!")
                end = true
            }
            else -> println("The input action is invalid")
        }
    } while (!end)
}

fun showList() {
    println("+----+------------+-------+---+---+--------------------------------------------+")
    println("| N  |    Date    | Time  | P | D |                   Task                     |")
    println("+----+------------+-------+---+---+--------------------------------------------+")
    for (i in taskList.indices) {
        val task = taskList[i]
        val num = i + 1
        val p: String = when (task.priority) {
            "C" -> "\u001B[101m \u001B[0m"
            "N" -> "\u001B[102m \u001B[0m"
            "H" -> "\u001B[103m \u001B[0m"
            else -> "\u001B[104m \u001B[0m"
        }

        val d: String = when (task.due) {
            "O" -> "\u001B[101m \u001B[0m"
            "I" -> "\u001B[102m \u001B[0m"
            else -> "\u001B[103m \u001B[0m"
        }

        if (num <= 9)  print("| $num  |")
        else print("| $num |")

        print(" ${task.date} |")

        print(" ${task.time} |")

        print(" $p |")

        print(" $d |")

        val tasks = task.tasks.split("\n")
        for (j in tasks.indices) {
            val chunk = tasks[j].chunked(44)
            for (k in chunk.indices) {
                if (k == 0 && j == 0) {
                    val rem = 44 - chunk[k].length
                    print(chunk[k])
                    repeat(rem) {
                        print(" ")
                    }
                    print("|")
                } else {
                    println()
                    val rem = 44 - chunk[k].length
                    print("|    |            |       |   |   |${chunk[k]}")
                    repeat(rem) {
                        print(" ")
                    }
                    print("|")
                }
            }
        }

        println()
        println("+----+------------+-------+---+---+--------------------------------------------+")
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

fun setTask(): String {
    var content = ""
    println("Input a new task (enter a blank line to end):")
    var inputList = true
    while (inputList) {
        val taskInput = readln().trim()
        if (taskInput.isBlank()) {
            inputList = false
        } else {
            if (content.isBlank())  content = taskInput
            else {
                content += "\n$taskInput"
            }
        }
    }

    return content
}
