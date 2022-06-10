package questions

import kotlin.random.Random

class Question1 {

    fun maximumPassengers() {
        /*
        Problem Açıklaması -:
        Bir taksi aynı anda birden fazla yolcuyu tren istasyonuna götürebilir.
        Başlangıç noktasına geri dönerken, taksi şoförü havaalanına bir sonraki
        seyahati için ilave yolcu alabilir. oluşturulmuş, bir kare matris olarak temsil edilmiştir.

        Matris hücrelerle doldurulur ve her hücre aşağıdaki gibi bir başlangıç değerine sahip olacaktır:
        Sıfırdan büyük veya sıfıra eşit bir değer bir yolu temsil eder.
        1'e eşit bir değer, bir yolcuyu temsil eder.
        -1'e eşit bir değer, bir engeli temsil eder.


        Taksi hareket kuralları aşağıdaki gibidir:
        Taksi şoförü (0,0)'da başlar ve tren istasyonu (n-1,n-1)'dedir.
        Tren istasyonuna doğru hareket,geçerli yol hücreleri aracılığıyla sağa veya aşağıdır.
        (n-1,n-1)'e ulaştıktan sonra taksi şoförü geçerli yol hücrelerinden sola veya yukarı hareket ederek (0,0)'a geri döner.
        Yolcu içeren bir yol hücresinden geçerken yolcu alınır. Yolcu alındıktan sonra hücre boş bir yol hücresi olur.
        (0,0) ile (n-1,n-1) arasında geçerli bir yol yoksa yolcu seçilemez.
        Amaç, sürücünün kazancını en üst düzeye çıkarabilmesi için mümkün olduğunca çok yolcu toplamaktır.

        Örneğin, aşağıdaki ızgarayı düşünün,
         0 1
        -1 0

        Sol üst köşeden başlayın. Bir yolcu alarak sağa hareket edin. Hedefe bir aşağı gidin.
        Hücre (1,0) bloke oldu, Bu yüzden dönüş yolu havaalanı yolunun tersidir.
        Tüm Yollar araştırıldı ve bir yolcu toplandı.

        İadeler:
        Int : toplanabilecek maksimum yolcu sayısı.

        Örnek
        --Giriş 0--
        4 -> size n = 4
        4 -> size m = 4

        0 0 0 1
        1 0 0 0
        0 0 0 0
        0 0 0 0

        --Çıkış 0--
        2
         */

        // Solution
        // Matris Boyutu girilir

        while (sizeMatris < 2) {
            try {
                print("Kare Matris Boyutu Girin (En Az 2 boyutlu olmak zorunda) :")
                sizeMatris = readLine()!!.toInt()
            } catch (_: Exception) {
                println("Sayı Giriniz !!!")
            }
        }

        // Rastgele matris atanır
        // Ekrana basılır
        val matris = getRandomMatris(sizeMatris)
        printMatris(matris)

        // yol bilgileri listesi tutulur
        val nodeList = mutableListOf<Nodes>()
        for (x in 0 until sizeMatris) {
            for (y in 0 until sizeMatris) {
                nodeList.add(
                    Nodes(
                        nodeName = "($x,$y)",
                        nextNodeList = findRoadsAndShow(x, y, matris, sizeMatris)
                    )
                )
            }
        }
        println("Liste")
        nodeList.forEach {
            println("${it.nodeName} -> ${it.nextNodeList}")
        }

        var stepCount = (sizeMatris - 1) * 2

        roadList.add("(0,0)")
        searchRoad(nodeList[0].nodeName, nodeList)

        roadList.forEach {
            print("$it ")
            if (it == "(${sizeMatris - 1},${sizeMatris - 1})")
                println()

        }


    }
    var sizeMatris = 0
    val roadList = mutableListOf<String>()
    fun searchRoad(nodeName: String, nodeList: MutableList<Nodes>) {
        findIndexOfNode(nodeName, nodeList)?.let { indexOfNode ->
            nodeList[indexOfNode].nextNodeList.forEach {
                roadList.add(it)
                searchRoad(it, nodeList)
            }
        }
    }

    fun findIndexOfNode(nodeName: String, nodeList: MutableList<Nodes>): Int? {
        val index = nodeList.indexOfFirst { it.nodeName == nodeName }
        return if (index == -1)
            null
        else
            index
    }

    fun getRoadsOfIndex(indexOfNode: Int, nodeList: MutableList<Nodes>): List<String> {
        return nodeList[indexOfNode].nextNodeList
    }
    data class Nodes(
        var nodeName: String,
        var nextNodeList: List<String> = emptyList()
    )
    fun findRoadsAndShow(x: Int, y: Int, matris: Array<IntArray>, sizeMatris: Int): List<String> {
        val list = mutableListOf<String>()
        val newX = x + 1
        val newY = y + 1

        if (newX == sizeMatris) {
            if (newY == sizeMatris) {
                return emptyList()
            }
            return list.apply {
                add("(${x},${y + 1})")
            }
        }
        if (newY == sizeMatris) {
            return list.apply {
                add("(${x + 1},${y})")
            }
        }
        return list.apply {
            add("(${x + 1},${y})")
            add("(${x},${y + 1})")
        }
    }
    fun getRandomMatris(sizeMatris: Int): Array<IntArray> {
        val wrongWaycount = sizeMatris.div(2).minus(-1)
        var wrongWaycounter = 0

        val matris = Array(sizeMatris) { IntArray(sizeMatris) } // 2 boyutlu dizi
        matris.forEachIndexed { indexColumn, ints ->
            println()
            ints.forEachIndexed { indexRow, _ ->
                matris[indexColumn][indexRow] = Random.nextInt(
                    if (wrongWaycounter >= wrongWaycount) 0 else -1,
                    2
                )
                val matrisValue =
                    if (indexRow == sizeMatris - 1 || indexRow == 0)
                        0
                    else
                        matris[indexColumn][indexRow]
                matris[indexColumn][indexRow] = matrisValue

                if (matrisValue == -1)
                    wrongWaycounter++
            }
        }
        return matris
    }
    fun printMatris(matris: Array<IntArray>) {
        println("----Matris-----")
        matris.forEachIndexed { indexColumn, ints ->
            ints.forEachIndexed { indexRow, _ ->
                val matrisValue = matris[indexColumn][indexRow]
                if (matrisValue < 0)
                    print("$matrisValue ")
                else
                    print(" $matrisValue ")
            }
            println()
        }
        println("----Matris-----")
    }
    fun printMatrisWhere(matris: Array<IntArray>, x: Int, y: Int) {
        matris.forEachIndexed { indexColumn, ints ->
            println()
            ints.forEachIndexed { indexRow, _ ->
                val matrisValue = matris[indexColumn][indexRow]
                if (indexColumn == x && indexRow == y) {
                    print("[*]")
                } else if (matrisValue < 0)
                    print("$matrisValue ")
                else
                    print(" $matrisValue ")
            }
        }
        println()

    }

}