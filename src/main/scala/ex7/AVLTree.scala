package ex7

import scala.annotation.tailrec

@main def test(): Unit =
  val tree = AVLTree()
  tree.insert(Vector(10,5,20,3,9,15,25,4,13,18,26,17,19))
  println(tree)
  tree.remove(5)
  println(tree)
  println("Pre-Order: " + tree.PreOrder(tree.head.get, _.value.toString + ", "))
  println("In-Order: " + tree.InOrder(tree.head.get, _.value.toString + ", "))
  println("Post-Order: " + tree.PostOrder(tree.head.get, _.value.toString + ", "))
  println(f"Suche nach 27: ${tree.search(27).nonEmpty}")


case class AVLTree():
  var head: Option[AVLNode] = None

  def insert(value: Int): Boolean =
    if head.nonEmpty then insert(head.get, value, value) else
    head = Option(AVLNode(value, value, None)); true

  def insert(arrVals: Vector[Int]): Boolean =
    arrVals.map((v: Int) => insert(v)).filter(_ == false).isEmpty

  def remove(value: Int): Boolean = remove(head, value)

  def search(value: Int): Option[Int] = if head.nonEmpty then search(head.get, value) else None

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

  def insert(n: AVLNode, key: Int, value: Int): Boolean = {
    val out = if key == n.key then false
    else
      if key < n.key then
        if n.leftChild.isEmpty then
          n.leftChild = Some(AVLNode(key, value, Option(n))); balance(n); true
        else insert(n.leftChild.get, key, value)
      else if n.rightChild.isEmpty then
        n.rightChild = Some(AVLNode(key, value, Option(n))); balance(n); true
      else insert(n.rightChild.get, key, value)
    if out then balance(n)
    out
  }


  // Funktion für das Auswählen der zu löschenden Node
  def remove(n: Option[AVLNode], key: Int): Boolean =
    if n.isEmpty then false
    else
      val node = n.get
      val out = (if key < node.key then remove(node.leftChild, key)
      else if key > node.key then remove(node.rightChild, key)
      else
        removeThis(node)
        true)
      if out then balance(node)
      out

  // lösche einen gegebenen Node
  private def removeThis(node: AVLNode): Unit =
    val leftOpt = node.leftChild
    val rightOpt = node.rightChild

    val bypass: Option[AVLNode] =
      if node.leftChild.nonEmpty then
        val left = node.leftChild.get
        Option(
          if node.rightChild.nonEmpty then
            val right = node.rightChild.get
            val p = if left.height > right.height then max(left) else min(right)
            removeThis(p)
            p.leftChild = node.leftChild
            p.rightChild = node.rightChild; p
          else left)
      else rightOpt
    if bypass.nonEmpty then
      val bp = bypass.get
      bp.parent = node.parent
      if bp.leftChild.nonEmpty then bp.leftChild.get.parent = bypass
      if bp.rightChild.nonEmpty then bp.rightChild.get.parent = bypass
    changeParentLink(node, bypass)

  @tailrec
  private def min(n: AVLNode): AVLNode =
    if n.leftChild.isEmpty then n else min(n.leftChild.get)

  @tailrec
  private def max(n: AVLNode): AVLNode =
    if n.rightChild.isEmpty then n else max(n.rightChild.get)

  private def doIfSome[T](element: Option[AVLNode], func: AVLNode => Option[T]): Option[T] =
    if element.isEmpty then None else func(element.get)

  private def doIfSome_[T](node: Option[AVLNode], func: AVLNode => T, elseVal: T) = AVLTree.doIfSome_(node, func, elseVal)

  private def balance(n: AVLNode): Unit =
    n.updateHeight()
    if n.balance < -1 then // n ist linkslastig
      val left = n.leftChild.get // linkes Kind muss wegen linkslastig existieren
      left.balance match
        case a if a <= 0 => rotateRight(n) // A1
        case _ => {rotateLeft(left); rotateRight(n)} // A2
    else if n.balance > 1 then
      val right = n.rightChild.get
      right.balance match
        case a if a >= 0 => rotateLeft(n) // B1
        case -1 => {rotateRight(right); rotateLeft(n)} // B2

  private def rotateRight(node: AVLNode): Unit =
    if node.leftChild.nonEmpty then
      val out = node.leftChild.get
      node.leftChild = out.rightChild
      if out.rightChild.nonEmpty then
        out.rightChild.get.parent = Some(node)
      out.rightChild = Some(node)
      out.parent = node.parent
      node.parent = Some(out)
      changeParentLink(out, Some(out))
      node.updateHeight()
      out.updateHeight()

  private def rotateLeft(node: AVLNode): Unit =
    if node.rightChild.nonEmpty then
      val out = node.rightChild.get
      node.rightChild = out.leftChild
      if out.leftChild.nonEmpty then
        out.leftChild.get.parent = Some(node)
      out.leftChild = Some(node)

      out.parent = node.parent
      node.parent = Some(out)
      changeParentLink(out, Some(out))
      node.updateHeight()
      out.updateHeight()


  private def changeParentLink(node: AVLNode, value: Option[AVLNode]): Unit =
    if node.parent.nonEmpty then
      val parent: AVLNode = node.parent.get
      if node.key < parent.key then parent.leftChild = value else parent.rightChild = value
    else head = value

  override def toString: String =
    head.toString

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

  override def toString: String =
    val leftS = if leftChild.nonEmpty then leftChild.get.toString else ""
    val rightS = if rightChild.nonEmpty then rightChild.get.toString else ""
    f"Node: $key \n  - balance: $balance\n  - left: ${optionString(leftChild)} \n  - right: ${optionString(rightChild)} \n$leftS\n$rightS"

  private def optionString(opt: Option[AVLNode]): String = if opt.nonEmpty then opt.get.key.toString else "None"
