package ex6

import scala.reflect.ClassTag

class Selection(private var a:Array[Int]) extends Sortieren:

    override def sort(): Array[Int] =
        for (i <- 0 until a.length) {
            var min: Int = i
            for (j <- i+1 until a.length) do
                if (apply(j) < apply(min)) then min = j
            swap(i, min)
        }
        countAndReset
        return a


