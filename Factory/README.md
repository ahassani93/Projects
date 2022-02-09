**Author:** Ari Hassani

**Description:** A simulation of a factory production line with three connected conveyor
belts and four workers. There are three types of workers, so the middle two workers
are of the same type. The first worker works on an object and places it on the belt.
The second and third workers retrieve an object, work on it, and place it on the belt.
The last worker retrieves an object and works on it. After that the object is created.
Each section of the belt can hold three objects, so if a section is full, the previous
worker won't place an object until the next worker retrieves an object. Similarly,
if a section is empty, the next worker will wait until an object is placed on the belt
to retrieve it. The conveyor belts and workers are synchronized, and each worker will
work on an object for a random number of seconds. The program uses threads for workers
to make the simulation realistic. Each event appears as a message. Input comes from
the keyboard, and the only input is the number of items the user wishes to be made.
Output goes to the console.

**Language:** Java
