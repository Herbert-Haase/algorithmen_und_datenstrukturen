package ex5

import scala.annotation.tailrec
import scala.util.boundary
import boundary.break
import scala.language.postfixOps
import scala.reflect.ClassTag

// generic variant without Interpol because its difficult to implement generic

@main def starts(): Unit =
   val b = new BinarySearchT[Integer]
   println(b.toString)
   b.insert(42)
   b.insert(11)
   b.inserts(Array(9, 14, 25))
   println(b.toString)
   println(b.search(42))
   b.remove(11)
   println(b)

// A short overview about everything
sealed trait searchT[T <: Comparable[T]: ClassTag]:
   def insert(key: T): Boolean
   def remove(key: T): Boolean
   def search(key: T): Int
   def get(indx: Int): T
   def inserts(keyArr: Array[T]): Boolean =
     boundary:
      for key <- keyArr do
         if !insert(key) then break(false)
      true

class BinarySearchT[T<: Comparable[T]: ClassTag] extends searchT:
   var arr : Array[T] = new Array[T](0)

   override def insert(key: T): Boolean =
      enlarge
      boundary:
        for i <- (arr.length - 1) to 0 by -1 do
          if i == 0 || arr(i-1).compareTo(key) < 0 then {
            arr(i) = key
            break(true)
          } else
            arr(i) = arr(i-1)
        false

   override def remove(key: T): Boolean =
      val indx = search(key)
      if indx == -1 then false
      else
         for i <- indx to arr.length - 2 do
            arr(i) = arr(i+1)
         shrink
         true

   override def search(key: T): Int =
      val out = search(key, 0, arr.length -1)
      if arr(out).compareTo(key) == 0 then out else -1
      
   override def get(indx: Int): T = arr(indx)

   @tailrec
   private def search(key: T, l: Int, r: Int): Int =
      if l == r then l
      else
         val mid : Int = mean(l,r)
         val v = arr(mid).compareTo(key)
         if v > 0 then search(key, l, mid - 1)
         else if v == 0 then mid
         else search(key, mid+1, r)


   private def enlarge: Unit =
      val b = new Array[T](arr.length + 1)
      for i <- arr.indices do
         b(i) = arr(i)
      arr = b

   // Attention! shrink throws the last indx away, so be aware of it!
   private def shrink: Unit =
      val b = new Array[T](arr.length - 1)
      for i <- b.indices do
         b(i) = arr(i)
      arr = b

   override def toString: String =
      arr.mkString(", ")

   private def mean(l: Int, r: Int) = (r-l)/2 + l