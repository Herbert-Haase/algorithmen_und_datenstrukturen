package graph.shortestPath

import graph.DirectedWeightedNode as Node
import scala.collection.mutable

object MooreFord:
  def shortestPath(graph: Vector[Node], start: Int): (Vector[Int], Vector[Int]) =
    // start
    val shortest: Array[Int] = Array.fill(graph.length)(Int.MaxValue)
    val path: Array[Int] = Array.fill(graph.length)(0)
    val candidates: mutable.Queue[Node] = mutable.Queue()
    candidates ++= graph(start).neighbours.map(i => graph(i.target))
    shortest(start) = 0
    for neighbour <- graph(start).neighbours do
      shortest(neighbour.target) = neighbour.cost

    while candidates.nonEmpty do
      val nextC = candidates.dequeue()
      for neighbour <- nextC.neighbours do
        val wayCost = shortest(nextC.key) + neighbour.cost
        if wayCost < shortest(neighbour.target) then
          shortest(neighbour.target) = wayCost
          path(neighbour.target) = nextC.key
          if !candidates.exists(dwn => dwn.key == neighbour.target) then
            candidates += graph(neighbour.target)

    (shortest.toVector, path.toVector)
