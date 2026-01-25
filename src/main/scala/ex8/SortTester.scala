package ex8
import ex6._
import scala.util.Random


@main def sortTester =
  val N = 1000
  val unsorted = new Array[Int](N)
  // val unsorted: Array[Int] = Array(9,8,7,6,5,4,3,2,1,0)

  for (i <- unsorted.indices)
    unsorted(i) = Random.nextInt
  println(unsorted.mkString(", "))
  
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

  // Heap-Sort
  val heap = HeapSort.mkHeap(unsorted)
  HeapSort.heapSort(heap)
  print("Heap-Sort: \n")
  heap.countAndReset() // 34
