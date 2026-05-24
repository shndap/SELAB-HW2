#!/bin/bash

if [ "$1" = "1" ]; then
    javac -d out-step1 $(find Step-01-without-OOD/src -name "*.java")
    java -cp out-step1 SmokeTest

elif [ "$1" = "2" ]; then
    javac -d out-step2 $(find Step-02-with-OOD/src -name "*.java")
    java -cp out-step2 SmokeTest

else
    echo "Usage: $0 [1|2]"
    echo "  1: Run step 1 (without OOD)"
    echo "  2: Run step 2 (with OOD)"
    exit 1
fi