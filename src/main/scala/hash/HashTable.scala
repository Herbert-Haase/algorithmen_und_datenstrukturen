package hash

import scala.reflect.ClassTag

class HashTable[T: ClassTag](val hashFunc: T => Int, length: Int) extends HTable[T]:
  private val arr: Vector[LinkedList[T]] = Vector.fill(length)(LinkedList[T]())

  override def += (value: T): Unit =
    val hash = hashFunc(value) % arr.length
    arr(hash) += Node(value)

  override def search (value: Int): Option[T] =
    val hash = value % arr.length
    arr(hash).get(value, hashFunc)

  override def -= (value: T): Boolean =
    val hash = hashFunc(value) % arr.length
    arr(hash) -= value

  override def toString: String =
    arr.mkString(", ")

class Node[T: ClassTag](val value: T, var next: Option[Node[T]] = None):
  override def toString: String = f"{$value, ${next.toString}}"

class LinkedList[T: ClassTag](var head: Option[Node[T]] = None):
  def += (value: Node[T]): Unit =
    if head == None then
      head = Some(value)
    else
      var node = head.get
      while node.next.nonEmpty do
        node = node.next.get
      node.next = Some(value)

  def get (key: Int, hashFunc: T => Int): Option[T] =
    var node = head
    while node.nonEmpty do
      if key == hashFunc(node.get.value) then
        return Some(node.get.value)
      node = node.get.next
    None

  def -= (value: T): Boolean =
    var node = head
    var prev: Option[Node[T]] = None
    while node.nonEmpty do
      if node.get.value == value then
        if prev.nonEmpty then prev.get.next = node.get.next
        else head = node.get.next
        return true
      prev = node
      node = node.get.next
    false

  override def toString: String = head.toString