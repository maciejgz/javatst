#!/bin/bash
# set the STRING variable
STRING="Hello World!"

function pause(){
 read -s -n 1 -p "Press any key to continue . . ."
 echo ""
}

# print the contents of the variable on screen
echo "$STRING"

pause
