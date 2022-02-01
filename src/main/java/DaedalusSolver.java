import upei.cs2920.daedalus.*;

import java.util.*;

/**
 * Solve a daedalus maze to produce a door path leading to all three
 * items of interest: Minotaur, Ring and Sword
 */
public class DaedalusSolver {
    boolean foundRing = false;
    boolean foundSword = false;
    boolean foundMinotaur = false;
    Stack <Cell> visitedCells= new Stack<>();
    String path="";



    /**
     * Construct a DaedalusSolver object
     */
    public DaedalusSolver() {

    }

    /**
     * return a String representing the path of doors to go through that
     * leads from the starting cell of a maze such that the path will lead through
     * cells containing all 3 of the items of interest (Minotaur, Ring and Sword)
     * @param start the starting cell or entry point into the maze
     * @return A String giving the sequence of doors on the path
     * @throws IllegalArgumentException if a path leading to all 3 special items cannot be found
     */
    public String solve(Cell start) {
        //complete this method
            if (!foundRing || !foundSword || !foundMinotaur  ) {
                checkContent(start.contents); //check if start.contents contains the item we are looking for
                checkDoorsInCell(start.doors); //check all the doors to see if we have visited their cell previously
                checkCell(start);//navigate through the doors
            }

        return path;
    }

    /**
     * doesnt return anything void
     * takes in the cell and navigate through the doors if they are not in the visitedCell stack
     * @param start the starting cell or the next cell to navigate
     */

    private void checkCell(Cell start) {
        for (Map.Entry<Door, Cell> entry : start.doors.entrySet()) {
            if (!foundRing || !foundSword || !foundMinotaur  ) {
            String door = String.valueOf(entry.getKey());
            Cell value = entry.getValue();

            //System.out.println("DOORS: "+start.doors);

            if(!visitedCells.contains(value)) { //if we have not visited the particular cell before
                    visitedCells.push(value); //add it to the visited cell stack
                    char currentPath = door.charAt(0);
                    path = path + currentPath; //add the door first character to the path
                    // System.out.println(path);
                    solve(value); // recall the solve function with the cell
                }
            }
        }

    }

    /**
     * doesnt return anything void
     * takes in the doors to check if all the cells in the doors have been visited
     * @param doors the doors in a cell
     */

    private void checkDoorsInCell(Map<Door, Cell> doors) {
        boolean allCellsAlreadyVisited=true;
        if (doors.size() ==0) { //if they are not doors, throw an error
            throw new IllegalArgumentException("not a valid maze");

        }
        for (Map.Entry<Door, Cell> entry : doors.entrySet()) {
            Cell value = entry.getValue();

            if (!visitedCells.contains(value)) { //if there is a cell that hasnt been entered
                allCellsAlreadyVisited = false;
                break;
            }
        }
        if(allCellsAlreadyVisited) { //if all cell have been visited, remove them from the stack to continue the search

            for (Map.Entry<Door, Cell> entry : doors.entrySet()) {
                visitedCells.remove(entry.getValue());
            }
        }
    }


    /**
     * doesnt return anything void
     * takes in content and compare the value with the item we are meant to find
     * @param contents the doors in a cell
     */

    private void checkContent(Contents contents) {
        if(contents == Contents.SWORD){
            if(!foundSword) { // once an item is found for the first time, reset the stack
                visitedCells.clear();
            }
            foundSword=true;
            //System.out.println("Sword found");
        }
        else if(contents == Contents.RING){
            if(!foundRing) { // once an item is found for the first time, reset the stack
                visitedCells.clear();
            }
            foundRing=true;
             //System.out.println("Ring found");

        }
        else if(contents == Contents.MINOTAUR){
            if(!foundMinotaur) { // once an item is found for the first time, reset the stack
                visitedCells.clear();
            }
            foundMinotaur=true;
             //System.out.println("Minotaur found");
        }
    }

}
