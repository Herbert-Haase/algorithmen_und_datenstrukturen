package ex6

import scala.reflect.ClassTag

class Insertion(a:Array[Int]) extends Sortieren(a):

    override def sort(): Array[Int] = 
        for (i <- 0 until a.length) {
            var x: Int = a(i)
            var j = i-1
          while (j>=0 && x<apply(j)) j-=1

          for (k <- i to j+2 by -1) a(k) = a(k-1)
          a(j+1) = x
        }
        countAndReset
        a

    override def countAndReset: Unit =
        println(f"Anzahl Elementvergleiche: ${count}")
        count = 0
        return;