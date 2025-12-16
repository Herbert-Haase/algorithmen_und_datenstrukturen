package ex7

import scala.annotation.tailrec

@main def test: Unit =
  val tree = AVLTree()
  tree.insert(Vector(10,1,3,7,19,22,11))

case class AVLTree():
  private var head: Option[AVLNode] = None

  def insert(value: Int): Boolean =
    if head.nonEmpty then insert(head.get, value, value) else
    head = Option(AVLNode(value, value, None)); true

  def insert(arrVals: Vector[Int]): Boolean =
    !arrVals.forall((v: Int) => insert(v))

  def remove(value: Int): Boolean = remove(head, value)

  def search(value: Int): Boolean = if head.nonEmpty then search(head.get, value).nonEmpty else false

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
    changeParentLink(node, bypass)

  @tailrec
  private def min(n: AVLNode): AVLNode =
    if n.leftChild.isEmpty then n else min(n.leftChild.get)

  @tailrec
  private def max(n: AVLNode): AVLNode =
    if n.rightChild.isEmpty then n else max(n.rightChild.get)

  private def removeChild(n: AVLNode): Option[AVLNode] = None

  private def doIfSome[T](element: Option[AVLNode], func: AVLNode => Option[T]): Option[T] =
    if element.isEmpty then None else func(element.get)

  private def doIfSome_[T](node: Option[AVLNode], func: AVLNode => T, elseVal: T) = AVLTree.doIfSome_(node, func, elseVal)

  private def balance(n: AVLNode): Unit =
    n.updateHeight()
    if n.balance < -1 then // n ist linkslastig
      val left = n.leftChild.get // linkes Kind muss wegen linkslastig existieren
      left.balance match
        case 0 | -1 => rotateRight(n) // A1
        case 1 => {rotateLeft(left); rotateRight(n)} // A2
    else if n.balance > 1 then
      val right = n.rightChild.get
      right.balance match
        case 0 | 1 => rotateLeft(n) // B1
        case -1 => {rotateRight(right); rotateLeft(n)} // B2
    if n.parent.nonEmpty then balance(n.parent.get)

  private def rotateRight(node: AVLNode): Unit =
    if node.leftChild.nonEmpty then
      val out = node.leftChild.get
      out.leftChild = Option(node)
      out.leftChild.get.updateHeight()
      out.updateHeight()
      changeParentLink(node, Option(out))

  private def rotateLeft(node: AVLNode): Unit =
    if node.rightChild.nonEmpty then
      val out = node.rightChild.get
      out.rightChild = Option(node)
      out.rightChild.get.updateHeight()
      out.updateHeight()
      changeParentLink(node, Option(out))


  private def changeParentLink(node: AVLNode, value: Option[AVLNode]): Unit =
    if node.parent.nonEmpty then
      val parent: AVLNode = node.parent.get
      if node.key < parent.key then parent.leftChild = value else parent.rightChild = value
    else head = value

object AVLTree:
  def doIfSome_[T](element: Option[AVLNode], func: AVLNode => T, elseVal: T): T =
    if element.isEmpty then elseVal else func(element.get)

class AVLNode(val key: Int,
              val value: Int,
              var parent: Option[AVLNode],
              var leftChild: Option[AVLNode] = None,
               var rightChild: Option[AVLNode] = None,
               var height: Int = 0):
  def balance: Int =
    val getHeight = (node: AVLNode) => node.height
    AVLTree.doIfSome_(this.rightChild, getHeight, -1) - AVLTree.doIfSome_(this.leftChild, getHeight, -1)

  def updateHeight(): Unit =
    val getHeight = (node: AVLNode) => node.height
    val leftHeight = AVLTree.doIfSome_(leftChild, getHeight, -1)
    val rightHeight = AVLTree.doIfSome_(rightChild, getHeight, -1)
    height = leftHeight.max(rightHeight) + 1
