cd src/main/java/dmk
~/ragel/ragel/ragel -J ProtocolHandler.ragel -o ProtoHandler.java
~/graphviz-2.26.3/cmd/dot/dot_builtins -Tpng test.dot >test.png
~/ragel/ragel/ragel -J ProtocolHandler.ragel -V > test.dot
open test.png
cd ../../../..
mvn install
