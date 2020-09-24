import kotlin.system.exitProcess

class FibIterable(private val n: Int): Iterable<Int> {
    var firstElement = 0
    var secondElement = 0
    var current = 1
    override fun iterator(): Iterator<Int> {
        return FibIterator(current, firstElement, secondElement, 1, n)
    }
}

class FibIterator(var current: Int, var firstElement: Int, var secondElement: Int, var currentNum: Int, private val finalNum: Int): Iterator<Int> {
    override fun next(): Int {
        currentNum += 1
        firstElement = secondElement
        secondElement = current
        current = firstElement + secondElement
        return current
    }

    override fun hasNext(): Boolean {
        return currentNum <= finalNum
    }
}

class FibCollection(private val n: Int): Collection<Int> {
    override val size: Int = n

    var firstElement = 0
    var secondElement = 0
    var current = 1

    override fun contains(element: Int): Boolean {
        val fib = FibIterable(n)
        var cont = false
        for (i in fib)
            if (element == i) {
                cont = true
                break
            }
        return cont
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun containsAll(elements: Collection<Int>): Boolean {
        for (i in elements)
            if (!contains(i))
                return false
        return true
    }

    override fun iterator(): Iterator<Int> {
        return FibIterator(current, firstElement, secondElement, 1, n)
    }
}

class FibList(val p: List<Int>): List<Int> by p {
    constructor(n: Int): this(FibIterable(n).toList())
}

fun main() {
    println("Print n")
    var a = readLine()!!
    val fib = FibIterable(a.toInt())
    for (i in fib) {
        println(i)
    }
    println("-----------------------------------------")
    val fib2 = FibCollection(a.toInt())
    for (i in fib2) {
        println(i)
    }
    println("-----------------------------------------")
    val fib3 = FibList(a.toInt())
    for (i in fib3) {
        println(i)
    }
}