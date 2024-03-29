/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam, Kirin Debnath
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {

        // Should be from start to end cells
        Stack<MazeCell> stc = new Stack<MazeCell>();
        stc.push(maze.getEndCell());

        // Traces back through cells adding parents to stack
        while(stc.peek() != maze.getStartCell()){
            stc.push(stc.peek().getParent());
        }

        // Adds each element from stack to ArrayList thus reversing the order
        ArrayList<MazeCell> sol = new ArrayList<MazeCell>();
        while(!stc.empty()) {
            sol.add(stc.pop());
        }

        return sol;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        Stack<MazeCell> stc = new Stack<MazeCell>();

        // Pushes first cell
        stc.push(maze.getStartCell());
        maze.getStartCell().setExplored(true);

        // Calls recursive helper function
        DFS(stc);

        return getSolution();


    }

    /**
     * Recursively performs a Depth-First Search assigning next/parent cell relationships
     * Precondition: stc must have at least one element
     * @param stc stack used to store possible branches to explore
     **/
    public void DFS(Stack<MazeCell> stc) {

        if(stc.peek().equals(maze.getEndCell())) {
            return;
        }

        // Cell that becomes parent for next cells
        MazeCell parent = stc.pop();
        int currentRow;
        int currentCol;

        // Adds NORTH, EAST, SOUTH, WEST directions to stack if valid
        // Calculations done using sin/cos trig functions
        for (double i = 0; i < 2* Math.PI; i += Math.PI / 2.0){
            currentRow = parent.getRow() + (int)Math.round(Math.cos(i));
            currentCol = parent.getCol() + (int)Math.round(Math.sin(i));

            if(maze.isValidCell(currentRow, currentCol)) {
                maze.getCell(currentRow, currentCol).setExplored(true);
                maze.getCell(currentRow, currentCol).setParent(parent);
                stc.push(maze.getCell(currentRow, currentCol));
            }
        }

        DFS(stc);
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {

        Queue<MazeCell> que = new LinkedList<MazeCell>();
        que.add(maze.getStartCell());
        que.peek().setExplored(true);

        BFS(que);

        return getSolution();

    }


    /**
     * Recursively performs a Breadth-First Search assigning next/parent cell relationships
     * Precondition: stc must have at least one element
     * @param que queue used to store possible branches to explore
     **/
    public void BFS(Queue<MazeCell> que) {

        if(que.peek().equals(maze.getEndCell())){
            return;
        }

        // Cell that becomes parent for next cells
        MazeCell parent = que.remove();
        int currentRow;
        int currentCol;


        // Adds NORTH, EAST, SOUTH, WEST directions to stack if valid
        // Calculations done using sin/cos trig functions
        for (double i = 0; i < 2* Math.PI; i += Math.PI / 2.0){
            currentRow = parent.getRow() + (int)Math.round(Math.cos(i));
            currentCol = parent.getCol() + (int)Math.round(Math.sin(i));

            if(maze.isValidCell(currentRow, currentCol)) {
                maze.getCell(currentRow, currentCol).setExplored(true);
                maze.getCell(currentRow, currentCol).setParent(parent);
                que.add(maze.getCell(currentRow, currentCol));
            }
        }

        BFS(que);

    }



    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");


        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Separate each Maze
        System.out.println("~~~~~~~~~~~~~~~");

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Separate each Maze
        System.out.println("~~~~~~~~~~~~~~~");

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
