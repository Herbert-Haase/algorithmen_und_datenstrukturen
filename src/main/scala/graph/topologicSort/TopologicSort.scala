package graph.topologicSort

import graph.DirectedNode
import scala.collection.mutable

object TopologicSort:

  def isSortable(graph: Vector[DirectedNode]): Boolean = sortGraph(graph).nonEmpty

  def sortGraph(graph: Vector[DirectedNode]): Option[Vector[DirectedNode]] =
    val (inDegree, queue) = initSort(graph)
    val out: mutable.ListBuffer[DirectedNode] = mutable.ListBuffer()
    while queue.nonEmpty do
      val node = queue.dequeue()
      out += node
      for neighbour <- node.neighbours do
        inDegree(neighbour) -= 1
        if inDegree(neighbour) == 0 then
          queue += graph(neighbour)
    if out.length == graph.length then
        Some(out.toVector)
    else None

  private def initSort(graph: Vector[DirectedNode]): (Array[Int], mutable.Queue[DirectedNode]) =
    val inDegree: Array[Int] = Array.fill(graph.length)(0)
    val queue: mutable.Queue[DirectedNode] = mutable.Queue()
    for node <- graph do
      for neighbour <- node.neighbours do
        inDegree(neighbour) += 1
    for node <- graph do
      if inDegree(node.key) == 0 then
        queue += node
    (inDegree, queue)