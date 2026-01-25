package topologicSort

import scala.collection.mutable

case class TopologicSort(graph: Vector[GraphNode]):

  def isSortable: Boolean = sortGraph.nonEmpty

  def sortGraph: Option[TopologicSort] =
    val (inDegree, queue) = initSort
    val out: mutable.ListBuffer[GraphNode] = mutable.ListBuffer()
    for node <- queue do
      out += node
      for neighbour <- node.neighbours do
        inDegree(neighbour) -= 1
    if out.length == graph.length then
      Some(TopologicSort(out.toVector))
    else None

  private def initSort: (Array[Int], mutable.Queue[GraphNode]) =
    val inDegree: Array[Int] = Array.fill(graph.length)(0)
    val queue: mutable.Queue[GraphNode] = mutable.Queue()
    for node <- graph do
      for neighbour <- node.neighbours do
        inDegree(neighbour)
    for node <- graph do
      if inDegree(node.key) == 0 then
        queue += node
    (inDegree, queue)

case class GraphNode(key: Int, neighbours: List[Int] = List())