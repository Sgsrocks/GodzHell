@echo off
color 0a
title Project GodzHell
java -Xmx1024m -cp bin;./jython.jar;./MySql/mysql-connectora-3.0.08-ga-bin.jar server
pause