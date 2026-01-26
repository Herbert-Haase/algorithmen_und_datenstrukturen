package graph

import graph.topologicSort.TopologicSort as Topo
import graph.shortestPath.Dijkstra as Dijk

@main def main(): Unit =
  val g: Array[GraphNode] = Array.ofDim(13)
  addNode(g, 11)()
  addNode(g, 10)(11)
  addNode(g, 9)(11)
  addNode(g, 6)(9)
  addNode(g, 4)(6)
  addNode(g, 8)(10)
  addNode(g, 5)(6, 8)
  addNode(g, 12)(5, 8)
  addNode(g, 7)(5, 9, 10)
  addNode(g, 3)(4, 5)
  addNode(g, 2)(3)
  addNode(g, 1)(2, 3)
  addNode(g, 0)(1)
  println(Topo.isSortable(g.toVector))
  println(Topo.sortGraph(g.toVector).get.mkString(", "))
  val d: Array[DirectedWeightedNode] = Array.ofDim(9)

def addNode(arr: Array[GraphNode], key: Int)(neighbours: Int*) =
  arr(key) = GraphNode(key, neighbours.toList)
  arr

def addNode(arr: Array[DirectedWeightedNode], key: Int)(neighbours: WeightEdge*) =
  arr(key) = DirectedWeightedNode(key, neighbours.toList)
  arr

case class GraphNode(key: Int, neighbours: List[Int] = List()):
  override def toString: String = key.toString

case class DirectedWeightedNode(key: Int, neighbours: List[WeightEdge])

case class WeightEdge(target: Int, cost: Int)
