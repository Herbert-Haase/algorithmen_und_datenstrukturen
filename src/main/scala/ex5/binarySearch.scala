package ex5

import scala.annotation.tailrec

@main def start(): Unit =
   val b = new BinarySearch
   println(b.toString)
   b.insert(Array(9,14,25,11))
   b.countAndReset
   b.insert(42)
   println(f"Anweisungen Insert: ${b.countAndReset}")
   println(b.toString)
   b.countAndReset
   println(f"Index von 42: ${b.search(42)}")
   println(f"Anweisungen search: ${b.countAndReset}")
   b.remove(11)
   println(f"Anweisungen remove: ${b.countAndReset}")
   println(b)

// A short overview about everything
sealed trait search:
   def insert(key: Int): Boolean
   def remove(key: Int): Boolean
   def search(key: Int): Int
   def insert(keyArr: Array[Int]): Boolean =
      for key <- keyArr do
         if !insert(key) then return false
      true

class CountedArray:
  private var a: Array[Int] = new Array[Int](0)
  var count: Int = 0
  def arr(indx: Int) = a(indx)
  def setArr(indx: Int, value: Int) =
    count += 1
    a(indx) = value

  def enlarge: Unit =
      val b = new Array[Int](a.length + 1)
      for i <- a.indices do {
        b(i) = a(i)
        count += 1
      }
      a = b

    // Attention! shrink throws the last indx away, so be aware of it!
  def shrink: Unit =
      val b = new Array[Int](a.length - 1)
      for i <- b.indices do
        b(i) = arr(i)
      a = b
      count += b.length

  override def toString: String =
    a.mkString(", ")

  def length: Int = a.length
class BinarySearch extends search:
   val arr : CountedArray = CountedArray()

   override def insert(key: Int): Boolean =
      arr.enlarge
      for i <- (arr.length - 1) to 0 by -1 do
         if i == 0 || arr.arr(i-1) <= key then
            arr.setArr(i, key)
            return true
         else
            arr.setArr(i, arr.arr(i-1))
      false

   override def remove(key: Int): Boolean =
      val indx = search(key)
      if indx == -1 then false
      else
         for i <- indx to arr.length - 2 do
            arr.setArr(i, arr.arr(i+1))
         arr.shrink
         true

   override def search(key: Int): Int =
      val out = search(key, 0, arr.length -1, isInterpol = true)
      if arr.arr(out) == key then out else -1

   @tailrec
   private def search(key: Int, l: Int, r: Int, isInterpol: Boolean): Int =
      if l == r then l
      else
         val mid : Int = if isInterpol then interpolate(l, r) else mean(l,r)
         if arr.arr(mid) > key then search(key, l, mid - 1, isInterpol)
         else if arr.arr(mid) == key then mid
         else search(key, mid+1, r, isInterpol)

   def countAndReset: Int=
      val out = arr.count
      arr.count = 0
      out


   override def toString: String = arr.toString

   private def interpolate(l: Int, r: Int) = {val diff: Int = arr.arr(r) - arr.arr(l);  l + (r-l) * diff / diff}
   private def mean(l: Int, r: Int) = (r-l)/2 + l