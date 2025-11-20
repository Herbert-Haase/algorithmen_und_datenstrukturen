package ex6

import scala.reflect.ClassTag

class Quick(a:Array[Int]) extends SortDivideConquer(a):

  def partition(left: Int, right: Int): Int = 
    var i = left - 1
    var j = right
    val pivot = a(right)
    var continue = true
    
    while (continue) {
        i += 1
        while (apply(i) < pivot) i += 1
        
        j -= 1
        while (j > left && apply(j) > pivot) j -= 1
        if (j == left) continue = false
        
        if (i >= j) continue = false
        
        if (continue) swap(i, j)
    }
    
    swap(i, right)
    i


  override def sort(left:Int, right:Int): Unit = 
    if(right<=left) return;
    var i:Int = partition(left, right)
    sort(left, i-1)
    sort(i+1, right)




    // override def sort(): Array[Int] = 
    // val stack = scala.collection.mutable.Stack[(Int, Int)]()
    // stack.push((0, a.length - 1))
    
    // while (stack.nonEmpty) {
    //     val (left, right) = stack.pop()
        
    //     if (right > left) {
    //         val i = partition(left, right)
    //         stack.push((left, i - 1))
    //         stack.push((i + 1, right))
    //     }
    // }
    
    // countAndReset
    // a