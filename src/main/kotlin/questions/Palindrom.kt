package questions

import kotlin.random.Random


class Palindrom {

    fun showPalindromNumbers() {
        /*val randomNumber = Random.nextInt(0, 99999)
        println("Number :$randomNumber")
        println("Palindrom? :${isPalindromNumber(randomNumber)}")*/

        while (true){
            print("Sayi Giriniz :")
            val inputNumber = readLine()!!.toInt()
            println("Palindrom? :${isPalindromNumber(inputNumber)}")
            println()
        }

    }

    private fun isPalindromNumber(randomNumber: Int): Boolean {
        val number = randomNumber.toString()
        var reversedNumber = ""
        for (index in number.length - 1 downTo 0) {
            reversedNumber += number[index]
        }
        return number == reversedNumber
    }

}