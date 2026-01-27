package graph

import graph.topologicSort.TopologicSort as Topo
import graph.shortestPath.Dijkstra as Dijk

@main def main(): Unit =
  val g = exampleTopo
  println(Topo.isSortable(g.toVector))
  println(Topo.sortGraph(g.toVector).get.mkString(", "))

  val d = exampleDijkstra
  val (distance, lastNode) = Dijk.shortestPath(d.toVector, 0)
  println(f"Distances: $distance")
  println(f"last Node: $lastNode")

case class DirectedNode(key: Int, neighbours: List[Int] = List()):
  override def toString: String = key.toString

case class DirectedWeightedNode(key: Int, neighbours: List[WeightEdge]):
  override def toString: String = key.toString

case class WeightEdge(target: Int, cost: Int)

def exampleTopo: Array[DirectedNode] =
  val g: Array[DirectedNode] = Array.ofDim(13)
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
  g

def exampleDijkstra =
  val d: Array[DirectedWeightedNode] = Array.ofDim(9)
  addNode(d, 0)((1, 1), (3, 2), (2, 6), (4, 2))
  addNode(d, 1)((0, 1), (2, 4), (5, 5))
  addNode(d, 2)((3, 3), (0, 6), (1, 4), (7, 8))
  addNode(d, 3)((2, 3), (0, 2))
  addNode(d, 4)((6, 1), (5, 6), (0, 2))
  addNode(d, 5)((1, 5), (4, 6), (8, 7))
  addNode(d, 6)((4, 1), (7, 1))
  addNode(d, 7)((6, 1), (2, 8), (8, 1))
  addNode(d, 8)((5, 7), (7, 1))
  d

def addNode(arr: Array[DirectedNode], key: Int)(neighbours: Int*) =
  arr(key) = DirectedNode(key, neighbours.toList)
  arr

def addNode(arr: Array[DirectedWeightedNode], key: Int)(neighbours: (Int, Int)*) =
  arr(key) = DirectedWeightedNode(key, neighbours.map(a => WeightEdge(a._1, a._2)).toList)
  arr