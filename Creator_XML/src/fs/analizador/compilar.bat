cd ..
cd ..
cd ..
java -jar lib\jflex-1.6.1.jar src\fs\analizador\fsScanner.jflex
java -jar lib\java-cup-11b.jar -parser fsParser -symbols sym src\fs\analizador\fsParser.cup
move fsParser.java src\fs\analizador
move sym.java src\fs\analizador
