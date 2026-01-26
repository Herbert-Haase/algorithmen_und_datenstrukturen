package graph.topologicSort

import graph.GraphNode
import scala.collection.mutable

object TopologicSort:

  def isSortable(graph: Vector[GraphNode]): Boolean = sortGraph(graph).nonEmpty

  def sortGraph(graph: Vector[GraphNode]): Option[Vector[GraphNode]] =
    val (inDegree, queue) = initSort(graph)
    val out: mutable.ListBuffer[GraphNode] = mutable.ListBuffer()
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

  private def initSort(graph: Vector[GraphNode]): (Array[Int], mutable.Queue[GraphNode]) =
    val inDegree: Array[Int] = Array.fill(graph.length)(0)
    val queue: mutable.Queue[GraphNode] = mutable.Queue()
    for node <- graph do
      for neighbour <- node.neighbours do
        inDegree(neighbour) += 1
    for node <- graph do
      if inDegree(node.key) == 0 then
        queue += node
    (inDegree, queue)