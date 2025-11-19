package ex6

@main def run():Unit =
    val selection = new Selection(Array(4,3,2,1))
    selection.sort()
    print(selection)