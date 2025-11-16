package ex5

@main def start(): Unit =
   val b = new BinarySearch
   println(b.toString)
   b.insert(42)
   b.insert(11)
   b.insert(Array(9,14,25))
   println(b.toString)
   println(b.search(42))
   b.remove(11)
   println(b)

class BinarySearch:
   var arr : Array[Int] = new Array[Int](0)

   def insert(keyArr: Array[Int]): Boolean =
      for key <- keyArr do
         if !insert(key) then return false
      true

   def insert(key: Int): Boolean =
      enlarge
      for i <- (arr.length - 1) to 0 by -1 do
         if i == 0 || arr(i-1) <= key then
            arr(i) = key
            return true
         else
            arr(i) = arr(i-1)
      false

   def remove(key: Int): Boolean =
      val indx = search(key)
      if indx == -1 then false
      else
         for i <- indx to arr.length - 2 do
            arr(i) = arr(i+1)
         shrink
         true

   def search(key: Int): Int =
      val out = search(key, 0, arr.length -1, isInterpol = true)
      if arr(out) == key then out else -1

   private def search(key: Int, l: Int, r: Int, isInterpol: Boolean): Int =
      if l == r then l
      else
         val mid : Int = if isInterpol then interpolate(l, r) else mean(l,r)
         if arr(mid) > key then search(key, l, mid - 1, isInterpol)
         else if arr(mid) == key then mid
         else search(key, mid+1, r, isInterpol)


   private def enlarge: Unit =
      val b = new Array[Int](arr.length + 1)
      for i <- arr.indices do
         b(i) = arr(i)
      arr = b

   private def shrink: Unit =
      val b = new Array[Int](arr.length - 1)
      for i <- b.indices do
         b(i) = arr(i)
      arr = b

   override def toString: String =
      arr.mkString(", ")

   private def interpolate(l: Int, r: Int) = l + (r-l) * (arr(r) - arr(l)) / (arr(r) - arr(l))
   private def mean(l: Int, r: Int) = (r-l)/2 + l