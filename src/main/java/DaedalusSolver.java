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
        if(start.doors.isEmpty() && (!foundRing && !foundSword && !foundMinotaur) ) {
            throw new IllegalArgumentException("not a valid maze");
        }
        if (!foundRing || !foundSword || !foundMinotaur  ) {

            checkContent(start.contents); //

            if(!checkDoorsInCell(start.doors)) {
                checkCell(start.doors);
            }

        }

        System.out.println(path);
        return path;
    }

    private boolean checkDoorsInCell(Map<Door, Cell> doors) {
        boolean allCellsAlreadyVisited=true;
        for (Map.Entry<Door, Cell> entry : doors.entrySet()) {
            Cell value = entry.getValue();

            if (!visitedCells.contains(value)) {
                allCellsAlreadyVisited = false;
                break;
            }
        }
        return allCellsAlreadyVisited;
    }

    private void checkCell(Map<Door, Cell> doors) {
        for (Map.Entry<Door, Cell> entry : doors.entrySet()) {
            String door = String.valueOf(entry.getKey());
            Cell value = entry.getValue();

            if(!visitedCells.contains(value)) {
                if (!foundRing || !foundSword || !foundMinotaur  ) {

                    visitedCells.push(value);
                    char currentPath = door.charAt(0);
                    path = path + currentPath;
                    System.out.println(path);
                    solve(value);
                }
            }
        }

    }

    private void checkContent(Contents contents) {
        if(contents == Contents.SWORD){
            if(!foundSword) {
                visitedCells.clear();
            }
            foundSword=true;
            System.out.println("Sword found");
        }
        else if(contents == Contents.RING){
            if(!foundRing) {
                visitedCells.clear();
            }
            foundRing=true;
            System.out.println("Ring found");

        }
        else if(contents == Contents.MINOTAUR){
            if(!foundMinotaur) {
                visitedCells.clear();
            }
            foundMinotaur=true;
            System.out.println("Minotaur found");
        }
    }

}
