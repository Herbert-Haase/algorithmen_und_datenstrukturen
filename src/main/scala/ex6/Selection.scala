package ex6

import scala.reflect.ClassTag

class Selection(private var a:Array[Int]):
    var count: Int = 0

    def countAndReset: Unit =
        println(f"Anzahl Elementvergleiche: ${count/2}")
        count = 0
        return;

    def apply(idx: Int):Int = 
        count += 1
        a(idx)

    def swap(i:Int, j:Int): Unit =
        val t:Int = a(i)
        a(i) = a(j)
        a(j) = t


    def sort(): Array[Int] =
        for (i <- 0 until a.length) {
            var min: Int = i
            for (j <- i+1 until a.length) do
                if (apply(j) < apply(min)) then min = j
            swap(i, min)
        }
        countAndReset
        return a
    
    
    override def toString: String = a.mkString("[", ", ", "]")

