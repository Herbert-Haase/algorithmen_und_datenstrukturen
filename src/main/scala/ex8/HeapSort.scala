package ex8

import scala.annotation.tailrec
import ex6.Sorter

@main def start =
  var arr = HeapSort.mkHeap(Array(10, 11, 16, 13, 2, 14, 15))
  var heap = HeapSort.heapSort(arr)
  println(heap)
  heap.countAndReset()

object HeapSort:
  def mkHeap(ints: Array[Int]): Heap = Heap(ints.map(i => Node(i)))

  def heapSort(a: Heap): Heap =
    build_heap(a, a.length)
    for indx <- 0 until a.length do
      val backwards = a.length - indx - 1
      a.swap(0,backwards)
      heapifyDown(a,0,backwards)
    a

  def build_heap(a: Heap, heapSize: Int ): Heap =
    for i <- 1 until heapSize do
      heapifyUp(a, i, i)
    a

  private def heapifyDown(a: Heap, node: Int, heapSize: Int): Heap =
    val leftID = node * 2 + 1
    if leftID < heapSize then
      val rightID = leftID + 1
      if rightID < heapSize then
        if a > (leftID, rightID) && a > (leftID, node) then
          a.swap(node, leftID)
          heapifyDown(a, leftID, heapSize)
        else if a > (rightID, node) then
          a.swap(node, rightID)
          heapifyDown(a, rightID, heapSize)
      else if a > (leftID, node) then
        a.swap(node, leftID)
        heapifyDown(a, leftID, heapSize)
    a

  private def heapifyUp(a: Heap, node: Int, newNode: Int): Heap =
    val parentID = getParentID(node)
    parentID match
      case Some(parent) =>
        if a < (parent, newNode) then
          heapifyUp(a, parent, newNode)
      case None =>
    a.swap(newNode, node)


  def getParentID(id: Int): Option[Int] =
    if id <= 0 then None
    else Some((id - 1)/ 2)

case class Node(key: Int):
  override def toString: String = f"$key"

case class Heap(private var arr: Array[Node]):
  var count = 0

  def swap(id: Int, id2: Int): Heap =
    if !(id == id2 || id < 0 || id2 < 0) then
      val mem = arr(id2)
      arr(id2) = arr(id)
      arr(id) = mem
    this

  def countAndReset(): Unit =
    println(f"Anzahl Vergleiche ${count}")
    count = 0

  def <(indx: Int, indx2: Int): Boolean =
    count += 1
    arr(indx).key < arr(indx2).key

  def >(indx: Int, indx2: Int): Boolean =
    count += 1
    arr(indx).key > arr(indx2).key

  override def toString: String =
    arr.mkString(", ")

  def length = arr.length

