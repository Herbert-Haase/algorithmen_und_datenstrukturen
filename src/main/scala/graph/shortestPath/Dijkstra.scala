package graph.shortestPath

import graph.DirectedWeightedNode as Node
import scala.collection.mutable

object Dijkstra:

  def shortestPath(graph: Vector[Node], start: Int, target: Int): (Int, Vector[Int]) =
    // start
    val shortest: Array[Int] = Array.fill(graph.length)(Int.MaxValue)
    val path: Array[Int] = Array.fill(graph.length)(Int.MaxValue)
    val candidates: mutable.Queue[Node] = mutable.Queue()
    candidates ++= graph(start).neighbours.map(i => graph(i.target))
    shortest(start) = 0
    for neighbour <- graph(start).neighbours do
      shortest(neighbour.target) = neighbour.cost

    while candidates.nonEmpty do
      val nextC = candidates.minBy(f => shortest(f.key))
      candidates -= nextC
      for neighbour <- nextC.neighbours do 
        val wayCost = shortest(nextC.key) + neighbour.cost
        if wayCost < shortest(neighbour.target) then shortest(neighbour.target) = wayCost

    (shortest(target), path.toVector)