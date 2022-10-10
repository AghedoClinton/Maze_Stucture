#  Daedalus is ded

Your goal in this assignment is to complete the `DaedalusSolver` class (in the provided intelliJ project) so that your program automatically finds its way out of a maze (as defined in Part 1 of this assignment).

You are expected to add to the `DaedalusSolver` class so that the public `solve` method returns a String containing the path of doors to follow through to find all of the three special objects in the maze:

- `Contents.MINOTAUR`,
- `Contents.SWORD`, and
- `Contents.RING`.

Your path doesn't have to be the shortest or most efficient it just needs to be a valid path that collects all of the objects.

If it is not possible to navigate the maze and find all of the 3 special items then the `solve` method should throw an `IllegalArgumentException`. Example: `throw new IllegalArgumentException("not a valid maze")`

You can assume that there are no Cells that you can enter but never leave, i.e., all Cells have at least 1 exit.

There are a number of tests in the `src/test/java` directory that your solution must pass. Therefore it is important that you do not change the default constructor for the `DaedalusSolver` class nor the method header for the `public String solve(Cell start)` method. You can however add additional methods and instance fields as you require.

If this assignment sounds intimidating don't worry, we will discuss it in class. However, here is a good starting point: Don't start by writing code but rather think about how you solved part 1. What `algorithm` did you use? what information did you keep track of? Once you've translated your own actions into a step-by-step approach, then you can start to think about how that translates into code.

We will demo running the Unit tests in class (so everyone is familiar with how to test their solutions using the given test cases). We will also discuss approaches to solving the assignment.

