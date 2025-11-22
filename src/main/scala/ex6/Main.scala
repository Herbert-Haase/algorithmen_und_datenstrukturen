import scala.util.Random

class Selection(arr: Array[Int]) extends Sortieren(arr) {
  override def sort(): Unit = {
    for (i <- 0 until size - 1) {
      var min = i
      for (j <- i + 1 until size) {
        if (get(j) < get(min)) {
          min = j
        }
      }
      swap(i, min)
    }
  }

  override def countAndReset(): Unit = {
    println(s"Anzahl Elementvergleiche: ${count / 2}")
    count = 0
  }
}

class Insertion(arr: Array[Int]) extends Sortieren(arr) {
  override def sort(): Unit = {
    for (i <- 1 until size) {
      val x = a(i)
      var j = i - 1

      var continue = true
      while (j >= 0 && continue) {
        if (x < get(j)) {
          a(j + 1) = a(j)
          j -= 1
        } else {
          continue = false
        }
      }
      a(j + 1) = x
    }
  }
  
  override def countAndReset(): Unit = {
    println(s"Anzahl Elementvergleiche: $count")
    count = 0
  }
}

class Quick(arr: Array[Int]) extends Sortieren(arr) {
  override def sort(): Unit = {
    if (size > 0) {
      impSort(0, size - 1)
    }
  }

  private def impSort(left: Int, right: Int): Unit = {
    if (right <= left) return
    val i = partition(left, right)
    impSort(left, i - 1)
    impSort(i + 1, right)
  }

  private def partition(left: Int, right: Int): Int = {
    var i = left - 1
    var j = right
    val pivot = a(right)

    while (true) {
      i += 1
      while (get(i) < pivot) {
        i += 1
      }

      j -= 1
      var continueInner = true
      while (continueInner) {
         if (get(j) > pivot) {
           if (j == left) {
             continueInner = false
           } else {
             j -= 1
           }
         } else {
           continueInner = false
         }
      }

      if (i >= j) {
        swap(i, right)
        return i
      }
      
      swap(i, j)
    }
    i 
  }

  override def countAndReset(): Unit = {
    println(s"Anzahl Elementvergleiche: $count")
    count = 0
  }
}

class Merge(arr: Array[Int]) extends Sortieren(arr) {
  override def sort(): Unit = {
    if (size > 0) {
      impSort(0, size - 1)
    }
  }

  private def impSort(left: Int, right: Int): Unit = {
    if (right <= left) return
    val middle = (left + right) / 2
    impSort(left, middle)
    impSort(middle + 1, right)
    merge(left, middle, right)
  }

  private def merge(li: Int, mi: Int, re: Int): Unit = {
    val aux = new Array[Int](re + 1)
    
    var i = mi + 1
    while (i > li) {
      aux(i - 1) = a(i - 1)
      i -= 1
    }

    var j = mi
    while (j < re) {
      aux(re + mi - j) = a(j + 1)
      j += 1
    }

    var ptrI = li
    var ptrJ = re 
    
    for (k <- li to re) {
      if (getE(aux, ptrJ) < getE(aux, ptrI)) {
        a(k) = aux(ptrJ)
        ptrJ -= 1
      } else {
        a(k) = aux(ptrI)
        ptrI += 1
      }
    }
  }

  private def getE(arr: Array[Int], idx: Int): Int = {
    count += 1
    arr(idx)
  }

  override def countAndReset(): Unit = {
    println(s"Anzahl Elementvergleiche: ${count/2}")
    count = 0
  }
}

object Main {
  val N = 1000

  def main(args: Array[String]): Unit = {
    val unsorted = new Array[Int](N)
    // val unsorted: Array[Int] = Array(9,8,7,6,5,4,3,2,1,0)
    
    print("[")
    for (i <- 0 until N) {
      unsorted(i) = Random.nextInt()
      print(unsorted(i))
      if (i < N - 1) print(", ")
    }
    println("]\n")

    // Selection
    val selection = new Selection(unsorted)
    selection.sort()
    print("Selection: \n")
    selection.countAndReset() // 45

    // Insertion
    val insertion = new Insertion(unsorted)
    insertion.sort()
    print("Insertion: \n")
    insertion.countAndReset() // 45

    // Quick
    val quick = new Quick(unsorted)
    quick.sort()
    print("Quick: \n")
    quick.countAndReset() // 58

    // Merge
    val merge = new Merge(unsorted)
    merge.sort()
    print("Merge: \n")
    merge.countAndReset() // 34
  }
}
