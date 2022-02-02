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
    LinkedHashMap<Cell, List<String>> remainingCells = new LinkedHashMap<Cell, List<String>>();


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

            if ((!foundRing || !foundSword || !foundMinotaur)) {
                System.out.println(remainingCells);
                addCellToRemainingCells(start);
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
        if(!checkIfAllCellsAreVisited()) {
            throw new IllegalArgumentException("not a valid maze");
        }
        for (Map.Entry<Door, Cell> entry : start.doors.entrySet()) {
            if (!foundRing || !foundSword || !foundMinotaur  ) {
            String door = String.valueOf(entry.getKey());
            Cell value = entry.getValue();

            System.out.println("DOORS: "+start.doors);

                System.out.println("visited: "+start.contents);
            if(!visitedCells.contains(value)) { //if we have not visited the particular cell before
                    visitedCells.push(value); //add it to the visited cell stack

                    char currentPath = door.charAt(0);
                    path = path + currentPath; //add the door first character to the path
                     System.out.println(path);
                    removeCellFromRemainingCells(start,door);

                    solve(value); // recall the solve function with the cell
                }
            }
        }

    }


    /**
     * return valueStillPresent boolean
     * check if all the cell a=have been visited
     */
    private boolean checkIfAllCellsAreVisited() {
        boolean valueStillPresent=false;

        if(remainingCells.size() > 2) {
            for (Map.Entry<Cell, List<String>> entry : remainingCells.entrySet()) {
                if (entry.getValue().size() > 0) {
                    valueStillPresent = true;
                    break;
                }
            }
        }
        else {
            valueStillPresent=true;
        }

        return valueStillPresent;
    }

    /**
     * return doesnt have a return value
     * add cell and doors to the remaining cells linkedHashMap
     */
    private void addCellToRemainingCells(Cell start) {
        if(!remainingCells.containsKey(start)){
            List<String> doorString= new ArrayList<String>();
            for (Map.Entry<Door, Cell> entry : start.doors.entrySet()) {
                doorString.add(String.valueOf(entry.getKey()));
                remainingCells.put(start,doorString);// put the cell and the doors in the cell

            }
        }
    }

    /**
     * return doesnt have a return value
     * remove door from the cell when the door is entered
     */
    private void removeCellFromRemainingCells(Cell cell, String door) {
        for (Map.Entry<Cell, List<String>> entry : remainingCells.entrySet()) {
            if(entry.getKey()==cell) {
                entry.getValue().removeIf(i -> i == door); //remove the door that was entered from the hashmap
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
                System.out.println("reached1");
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
            foundSword=true;
            System.out.println("Sword found");
        }
        else if(contents == Contents.RING){
            foundRing=true;
            System.out.println("Ring found");

        }
        else if(contents == Contents.MINOTAUR){
            foundMinotaur=true;
             System.out.println("Minotaur found");
        }
    }

}
