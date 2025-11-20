package ex6

@main def run():Unit =
    val selection = new Selection(Array(4,3,2,1))
    selection.sort()
    println(selection)

    val insertion = new Insertion(Array(4,3,2,1))
    insertion.sort()
    println(insertion)