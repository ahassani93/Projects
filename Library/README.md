**Author:** Ari Hassani

**Description:** A program that creates an array of structs
of book records. This program reads in a database of
books from the file "librarydatabase.txt", loads them
into the array of structs, and sorts the array based on
book titles. Then, from a transaction file called
"librarytransactions.txt" it reads in lines of commands
(one command per line) followed by values
(depending on the command) to process the array of books
and output the results for each command. Input comes
from two files that include the database file for
initialization and the transaction file for reading
commands and performing the corresponding actions.
Output goes to the console (depending on the command).
The list of command codes are given below.

P (print database with full book information ordered by title with a blank line between books)
T title (Display title and available copies)
I isbn (Display ISBN and number of copies)
S isbn int (Set number of copies to int; Verify that int is not negative)
R isbn int (Reduce number of copies by int; Count should never be negative)
A isbn int (Add int to number of copies)
N (Add new title – data that follows for one book will be formatted like the initial data file)
W author (List any book titles written by the author)
F title (Display all information about a book, standard format)
B title (Display title and ISBN )

**Language:** C++
