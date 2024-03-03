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
