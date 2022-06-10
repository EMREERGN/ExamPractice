package questions

import kotlin.math.max
import kotlin.math.min

class TrafficLights {

    private var roadLenght = 0 // RoadLenght
    private val locations = arrayListOf<Int>()

    /*  Example
    position max((i – locations[i]), 1) to min((i + locations[i]), n )
      n = 3
      locations[] = {0, 2, 1 }then
      For position 1: locations[1] = 0,
       max((1 – 0),1)  to mini (1+0), 3) gives range = 1 to 1

      For position 2: locations[2] = 2,
       max((2-2),1) to min( (2+2), 3) gives range = 1 to 3

      For position 3: locations[3] = 1,
      max( (3-1),1) to min( (3+1), 3) gives range = 2 to 3

      For the entire length of this road to be covered,
      only the light at position 2 needs to be activated.

      */

    fun solve() {
        getLocations()
        calculateMaxMin()
    }

    private fun calculateMaxMin() {
        for (index in 1 until locations.size) {
            // max((i – locations[i]), 1) to min((i + locations[i]), n )
            val max = max(index - locations[index], 1)
            val min = min(index + locations[index], roadLenght)
            println("${index}. max :$max to min :$min")

        }
    }


    private fun getLocations() {
        print("Road Lenght(n) :")
        roadLenght = readLine()!!.toInt()
        locations.add(-1)
        for (index in 0 until roadLenght) {
            print("location[${index+1}] :")
            locations.add(readLine()!!.toInt())
        }
        println()
    }


}