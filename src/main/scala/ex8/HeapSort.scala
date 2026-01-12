package ex8

import scala.annotation.tailrec

@main def start =
  var arr = HeapSort.mkNodeArr(Array(10, 11, 16, 13, 2, 14, 15))
  var heap = HeapSort.build_heap(arr, arr.length)
  println(heap.mkString(", "))
  println(HeapSort.getParentID(2))

object HeapSort:
  def mkNodeArr(ints: Array[Int]): Array[Node] = ints.map(i => Node(i))

  def heapSort(a: Array[Node]): Array[Node] =
    build_heap(a, a.length)

  def build_heap(a: Array[Node], heapSize: Int ): Array[Node] =
    for i <- 1 until heapSize do
      heapify(a, i, i)
    a

  private def heapify(a: Array[Node], node: Int, newNode: Int): Array[Node] =
    val parentID = getParentID(node)
    parentID match
      case Some(parent) =>
        if a(parent).key < a(newNode).key then
          heapify(a, parent, newNode)
      case None =>
    swap(a, newNode, node)


  private def swap(a: Array[Node], id: Int, id2: Int): Array[Node] =
    if !(id == id2 || id < 0 || id2 < 0) then
      val mem = a(id2)
      a(id2) = a(id)
      a(id) = mem
    a

  def getParentID(id: Int): Option[Int] =
    if id <= 0 then None
    else Some((id - 1)/ 2)

case class Node(key: Int):
  override def toString: String = f"$key"

