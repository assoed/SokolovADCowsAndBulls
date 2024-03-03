package org.example

import kotlin.random.Random

class Game {
    private val secretNumber = generateSecretNumber()

    private fun generateSecretNumber(): String {
        return (1..4).map { Random.nextInt(0, 9) }.joinToString("")
    }

    fun guess(number: String): Pair<Int, Int> {
        var bulls = 0
        var cows = 0

        for (i in secretNumber.indices) {
            if (number[i] == secretNumber[i]) {
                bulls++
            } else if (number[i] in secretNumber) {
                cows++
            }
        }

        return Pair(bulls, cows)
    }

    fun isValidInput(number: String): Boolean {
        return number.length == 4 && number.all { it.isDigit() } && number.toSet().size == 4
    }
}

class Player(val name: String, var score: Int) {

    fun increaseScore(points: Int) {
        score += points
    }

    fun decreaseScore(points: Int) {
        score -= points
    }

    override fun toString(): String {
        return "Игрок: $name, Счет: $score"
    }
}

class GameInterface {
    fun startGame() {
        val game = Game()
        val player = Player("Игрок 1", 0)

        println("Добро пожаловать в игру Быки и Коровы, ${player.name}!")
        println("Я загадал 4-значное число. Попробуйте его угадать.")

        var bulls: Int
        do {
            println("Введите ваше число:")
            val guess: String? = readLine()

            if (!guess.isNullOrBlank() && game.isValidInput(guess)) {
                val result = game.guess(guess)
                bulls = result.first
                val cows = result.second

                if (bulls == 4) {
                    println("Поздравляю, вы угадали число!")
                } else {
                    println("Быки: $bulls, Коровы: $cows")
                }
            } else {
                println("Введенное число недействительно. Пожалуйста, введите 4-значное число, в котором все цифры различны.")
                bulls = 0 // При неверном вводе сбрасываем быков
            }
        } while (bulls != 4)
    }
}

fun main() {
    val gameInterface = GameInterface()
    gameInterface.startGame()
}
