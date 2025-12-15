package ex7

import scala.annotation.tailrec


case class AVLTree():
  private var head: Option[AVLNode] = None

  def PreOrder(n: AVLNode, f: AVLNode => String): String =
    f(n)
      + doIfSome_(n.leftChild, (el: AVLNode) => PreOrder(el, f), "")
      + doIfSome_(n.rightChild, (el: AVLNode) => PreOrder(el, f), "")

  def InOrder(n: AVLNode, f: AVLNode => String): String =
    doIfSome_(n.leftChild, (el: AVLNode) => InOrder(el, f), "")
      + f(n)
      + doIfSome_(n.rightChild, (el: AVLNode) => InOrder(el, f), "")

  def PostOrder(n: AVLNode, f: AVLNode => String): String =
    doIfSome_(n.leftChild, (el: AVLNode) => PostOrder(el, f), "")
      + doIfSome_(n.rightChild, (el: AVLNode) => PostOrder(el, f), "")
      + f(n)

  def search(n: AVLNode, key: Int): Option[Int] =
    if key == n.key then Some(n.value)
    else if key < n.key then doIfSome(n.leftChild, (el: AVLNode) => search(el, key))
    else doIfSome(n.rightChild, (el: AVLNode) => search(el, key))

  def insert(n: AVLNode, key: Int, value: Int): Boolean =
    if key == n.key then false
    else
      if key < n.key then
        if n.leftChild.isEmpty then n.leftChild = Some(AVLNode(key, value, Option(n)))
        else insert(n.leftChild.get, key, value)
      else if n.rightChild.isEmpty then n.rightChild = Some(AVLNode(key, value, Option(n)))
      else insert(n.rightChild.get, key, value)
      true

  // Funktion für das Auswählen der zu löschenden Node
  def remove(n: Option[AVLNode], key: Int): Boolean =
    if n.isEmpty then false
    else
      val node = n.get
      if key < node.key then remove(node.leftChild, key)
      else if key > node.key then remove(node.rightChild, key)
      else
        removeThis(node)
        true

  // lösche einen gegebenen Node
  private def removeThis(node: AVLNode): Unit =
    val leftOpt = node.leftChild
    val rightOpt = node.rightChild

    val bypass: Option[AVLNode] =
      if node.leftChild.nonEmpty then
        val left = node.leftChild.get
        Option( if node.rightChild.nonEmpty then
         val right = node.rightChild.get
         val p = if left.height > right.height then max(left) else min(right)
         p.leftChild = node.leftChild
         p.rightChild = node.rightChild; p
        else left)
      else rightOpt
    if node.parent.nonEmpty then
      val parent = node.parent.get
      if node.key < parent.key then parent.leftChild = bypass
      else parent.rightChild = bypass
    else head = bypass

  @tailrec
  private def min(n: AVLNode): AVLNode =
    if n.leftChild.isEmpty then n else min(n.leftChild.get)

  @tailrec
  private def max(n: AVLNode): AVLNode =
    if n.rightChild.isEmpty then n else max(n.rightChild.get)

  private def removeChild(n: AVLNode): Option[AVLNode] = None

  private def doIfSome[T](element: Option[AVLNode], func: AVLNode => Option[T]): Option[T] =
    if element.isEmpty then None else func(element.get)

  private def doIfSome_[T](element: Option[AVLNode], func: AVLNode => T, elseVal: T): T =
    if element.isEmpty then elseVal else func(element.get)

  private def balance(n: AVLNode): Unit =
    val getHeight = (node: AVLNode) => node.height
    val leftHeight = doIfSome_(n.leftChild, getHeight, -1)
    val rightHeight = doIfSome_(n.rightChild, getHeight, -1)
    n.height = leftHeight.max(rightHeight) + 1
    if leftHeight != rightHeight then
      true

  private def turnLeft(node: AVLNode): Unit =
    if node.leftChild.nonEmpty then
      val out = node.leftChild.get



class AVLNode (val key: Int,
               val value: Int,
               var parent: Option[AVLNode],
               var leftChild: Option[AVLNode] = None,
               var rightChild: Option[AVLNode] = None,
               var height: Int = 0)

@main def tests(): Unit =
  println("hi")