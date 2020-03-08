package rogue

interface GridPrinter<T> {

    fun print(value: T)

    fun newLine()
}

class StdoutGridPrinter<T> : GridPrinter<T> {
    override fun newLine() {
        System.out.println()

    }

    override fun print(value: T) {
        System.out.print(value)
    }

}

