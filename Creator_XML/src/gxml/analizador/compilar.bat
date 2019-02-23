cd ..
cd ..
cd ..
java -jar lib\jflex-1.6.1.jar src\gxml\analizador\gxmlScanner.jflex
java -jar lib\java-cup-11b.jar -parser gxmlParser -symbols sym src\gxml\analizador\gxmlParser.cup
move gxmlParser.java src\gxml\analizador
move sym.java src\gxml\analizador