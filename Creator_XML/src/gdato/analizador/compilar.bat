cd ..
cd ..
cd ..
java -jar lib\jflex-1.6.1.jar src\gdato\analizador\gdatoScanner.jflex
java -jar lib\java-cup-11b.jar -parser gdatoParser -symbols sym src\gdato\analizador\gdatoParser.cup
move gdatoParser.java src\gdato\analizador
move sym.java src\gdato\analizador
