package ex6

@main def run():Unit =
    val unsorted: Array[Int] = Array(4,3,2,1)
    val selection = new Selection(unsorted)
    selection.sort()
    println(selection) //Zugriffe 6

    val insertion = new Insertion(unsorted)
    insertion.sort()
    println(insertion) //Zugriffe 6

    val quick = new Quick(unsorted)
    quick.sort(0, unsorted.length-1)
    quick.countAndReset
    println(quick) //Zugriffe ?