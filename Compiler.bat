@echo off
color 0A
title GodzHell Compiler
"C:\Program Files\Java\jdk1.8.0_171\bin\javac.exe" -cp deps/netty.jar; -d bin src\*.java  src\clip\*.java src\clip\region\*.java
pause 