# Scala ChessBoardSolvingAlgorithm #

To run the application you can use IDE with simple run command or `sbt run` task.

Application will prompt user for input  of width, height and chess pieces sequence.

Width and height are simple integer values whereas pieces are one string value containing of multiple space separated letters :

`K for King`, `Q for Queen`, "N for Knight", "B for Bishop" and "R for Rook"

If user will give no input or input will have improper format, default values will be used that are :

`width=7"
`height=7`
`pieces= "K K Q Q B B N"`, which gives 2 Kings, 2 Queens, 2 Bishops and 1 Knight.
