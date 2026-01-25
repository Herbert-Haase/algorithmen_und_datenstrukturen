package topologicSort

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
  val graph = TopologicSort(g.toVector)
  println(graph.isSortable)

def addNode(arr: Array[GraphNode], key: Int, neighbours: List[Int]) =
  arr(key) = GraphNode(key, neighbours)
  arr

def addNode(arr: Array[GraphNode], key: Int)(neighbours: Int*) =
  arr(key) = GraphNode(key, neighbours.toList)
  arr
