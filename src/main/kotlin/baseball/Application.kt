package baseball

import camp.nextstep.edu.missionutils.Randoms

fun main() {

    println("숫자 야구 게임을 시작합니다.")

    while (true) {
        val computer = mutableListOf<Int>()
        generateRandomNumber(computer)

        do {
            val player = mutableListOf<Int>()
            getPlayerNumber(player)

            var strike = countStrike(computer, player)
            var ball = countBall(computer, player)
            printHint(ball, strike)
        } while (strike < 3)

        println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")

        when (getRestartOrNot()) {
            1 -> continue
            2 -> break
            else -> throw IllegalArgumentException()
        }
    }
}

private fun getRestartOrNot(): Int {
    val input = readlnOrNull()
    if (input.isNullOrBlank()) {
        throw IllegalArgumentException()
    }
    try {
        return input.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

private fun printHint(ball: Int, strike: Int) {
    when {
        ball == 0 && strike == 0 -> {
            println("낫싱")
        }

        ball == 0 && strike != 0 -> {
            println("${strike}스트라이크")
        }

        ball != 0 && strike == 0 -> {
            println("${ball}볼")
        }

        else -> {
            println("${ball}볼 ${strike}스트라이크")
        }
    }
}

private fun countBall(
    computer: MutableList<Int>,
    player: MutableList<Int>
): Int {
    var ball = 0
    for (computerElem in computer) {
        if (player.contains(computerElem)) {
            if (player.indexOf(computerElem) != computer.indexOf(computerElem)) {
                ball++
            }
        }
    }
    return ball
}

private fun countStrike(
    computer: MutableList<Int>,
    player: MutableList<Int>
): Int {
    var strike = 0
    for (index in 0 until 3) {
        if (computer[index] == player[index]) {
            strike++
        }
    }
    return strike
}

private fun getPlayerNumber(player: MutableList<Int>) {
    print("숫자를 입력해주세요 : ")
    val input = readlnOrNull()
    if (input.isNullOrBlank()) {
        throw IllegalArgumentException()
    }
    if (input.length != 3) {
        throw IllegalArgumentException()
    }
    var intInput = try {
        input.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }

    while (player.size < 3) {
        val currentNum = intInput % 10
        if (currentNum == 0) {
            throw IllegalArgumentException()
        }
        if (player.contains(currentNum)) {
            throw IllegalArgumentException()
        }
        player.add(currentNum)
        intInput /= 10
    }
    player.reverse()
}

private fun generateRandomNumber(computer: MutableList<Int>) {
    while (computer.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (!computer.contains(randomNumber)) {
            computer.add(randomNumber)
        }
    }
}