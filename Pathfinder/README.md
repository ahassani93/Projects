**Author:** Ari Hassani

**Description:** A pathfinder application that uses Dijkstra's algorithm to find the
shortest path and its distance between two locations in a map. The map is represented
by a square matrix in the "distance_matrix" file. Each number in a row and column
represents the distance between the two locations. A "0" is the distance of a location
to itself, and a "999999" means a connection does not exist between the two locations.
Location names come from the "location_codes" file, and the number of location names
must match the number of rows and colums in the matrix file. All aspects of error
checking has been implemented to prevent calculation errors. Input comes from the
keyboard, and it asks the user for a start and end location. After the algorithm
finishes, it prints the shortest path with each location along the path with arrows
between them. It also prints the distance of the shortest path. Output goes to console.
The application repeats by asking the user if they want another path calculated. If no
is selected, the program ends.

**Language:** Java

**References:** Dijkstra's shortest path algorithm
