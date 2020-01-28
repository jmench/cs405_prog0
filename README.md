# CS405 Program 0 #
## Author: Jordan Menchen ##
### Description: ###
This program will search through two given .txt files and output the names and salaries of employees that match the city entered by the user on the command line.

### How to Run: ###
To run this program, simply navigate to the directory of the program, and first compile it using the command `javac Main.java`. If you have the correct components installed, this command should run smoothly and create a file in the same directory called *Main.class*.

You can then run the program with the command `java Main CITYNAME` where `CITYNAME` is the city you'd like to search (ex. `java Main Louisville`).

The program may take a while to run, so be patient. The output will either be 3 things:
1. No names found for the city entered...
2. No matches found in the salary file...
3. A list of names and salaries in the format **NAME : SALARY**

### IMPORTANT ###
Please note that the program will not compile/run without the text files in the same directory as the *Main.java* file. These files **MUST** have the names *personnel_addresses.txt* and *personnel_salaries.txt* and **MUST** have the formats **NAME|CITY** and **NAME|SALARY** respectively.
