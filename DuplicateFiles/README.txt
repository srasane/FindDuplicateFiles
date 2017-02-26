Problem Statement: Recursively find all duplicate files in a folder and return them in a data structure that facilitates efficiently looking up the duplicates

This project finds duplicate files in a root directory provided by the user. 
The files of same types are considered duplicates if they have same contents. This is achieved by calculating the 
hash values based on the MD5 algorithm. The data from the files is read if it evaluates to same value calculated by the MD5 algorithm then 
those files are duplicates. 