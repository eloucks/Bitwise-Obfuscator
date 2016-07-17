# Bitwise-Obsfucator
A program that non-destructively alters binary structure of a file using tunable parameters.

This was a practice project for me to remember bitwise manipulation. It works well, but has the side-effect of moderately increased file size. Currently, this program is set up to operate on strings, but writing methods to turn any file into a viable input would be trivial.

The operating principle behind this program is searching for redundent binary combinations. It works by searching ahead in a binary string for any duplicates of the first block of binary (both block size and search distance are tunable). Any time a duplicate is found, it is represented by a 1 in a new string of binary of length search distance. The origin block being searched for is appended to the result, along with the resulting string of binary from the search, and any instances of the origin block that were found are trimmed out of the original binary string. This process repeats with the next binary block, until the end of the fiel. The whole process can (and likely should) be repeated.
