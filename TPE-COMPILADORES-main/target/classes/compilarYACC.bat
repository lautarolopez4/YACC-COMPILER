.\yacc.exe -v -J gramatica_final.y
del .\AnalizadorLexico\AnalizadorSintactico\Parser.java
del .\AnalizadorLexico\AnalizadorSintactico\ParserVal.java
move .\Parser.java .\AnalizadorLexico\AnalizadorSintactico
move .\ParserVal.java .\AnalizadorLexico\AnalizadorSintactico
exit