package ex6

@main def run():Unit =
    val unsorted: Array[Int] = Array(4,3,2,1)
    val selection = new Selection(unsorted)
    selection.sort()
    println(selection)

    val insertion = new Insertion(unsorted)
    insertion.sort()
    println(insertion)

    val quick = new Quick(unsorted)
    quick.sort(unsorted(0),unsorted(unsorted.length-1))
    quick.countAndReset
    println(quick)