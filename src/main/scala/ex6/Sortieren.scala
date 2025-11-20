package ex6

import scala.reflect.ClassTag

trait Sortieren(private var a:Array[Int]):
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


    def sort(): Array[Int] = {}


    override def toString: String = a.mkString("[", ", ", "]")

