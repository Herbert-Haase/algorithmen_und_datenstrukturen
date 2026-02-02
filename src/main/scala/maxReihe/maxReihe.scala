package maxReihe

object maxReihe:
  def maxReihe(arr: Seq[Int]): (Int, Int, Int) =
    maxReihe(arr, 0, arr.size-1)

  def maxReihe(arr: Seq[Int], l: Int, r: Int): (Int, Int, Int) =
    if r-l > 0 then
      val m: Int = (r + l) / 2
      val left = maxReihe(arr, l, m)
      val right = maxReihe(arr, m+1, r)
      if left._1 > right._1 then left else right
    else
      var lastVal = 0
      val out: Seq[(Int, Int, Int)] =
        for i <- l until arr.size yield
            lastVal += arr(i)
            (lastVal, l, i)
      out.maxBy(_._1)



@main def main =
  val arr = Seq(0, 1, -3, 2, 5, 2, 1, -2, 10, 3,-6, 5)
  val maxR = maxReihe.maxReihe(arr)
  println(f"Max-Reihe: von Index: ${maxR._2} bis ${maxR._3}\nWert: ${maxR._1}")