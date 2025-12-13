abstract class Sortieren(arr: Array[Int]) {

  protected val a: Array[Int] = arr.clone()
  protected val size: Int = a.length
  protected var count: Int = 0

  def sort(): Unit

  def countAndReset(): Unit = {
    println(s"Anzahl Elementvergleiche: $count")
    count = 0
  }

  def get(idx: Int): Int = {
    count += 1
    a(idx)
  }

  def swap(i: Int, j: Int): Unit = {
    val t = a(i)
    a(i) = a(j)
    a(j) = t
  }

  def rawGet(index: Int): Int = a(index)
  def rawSet(index: Int, value: Int): Unit = a(index) = value

  override def toString: String = {
    a.mkString("[", ", ", "]")
  }
}