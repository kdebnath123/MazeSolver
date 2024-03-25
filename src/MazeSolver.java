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

        while(stc.peek() != maze.getStartCell()){
            //System.out.println(stc.peek().getParent().getRow() + "," + stc.peek().getParent().getRow());
            stc.push(stc.peek().getParent());
        }

        ArrayList<MazeCell> sol = new ArrayList<MazeCell>();

        while(!stc.empty()){
            sol.add(stc.pop());
        }

        return sol;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> stc = new Stack<MazeCell>();
        stc.push(maze.getStartCell());
        DFS(stc);


        return getSolution();
    }


    public void DFS(Stack<MazeCell> stc) {

        stc.peek().setExplored(true);

        if(stc.peek().getRow() == maze.getEndCell().getRow() && stc.peek().getCol() == maze.getEndCell().getCol()){
            return;
        }

        MazeCell temp = stc.peek();
        int row = temp.getRow();
        int col = temp.getCol();

        // West
        if(maze.isValidCell(row - 1, col)) {
           maze.getCell(row - 1, col).setParent(temp);
           stc.push(maze.getCell(row - 1, col));
           DFS(stc);
        }

        // South
        if(maze.isValidCell(row, col - 1)) {
            maze.getCell(row, col - 1).setParent(temp);
            stc.push(maze.getCell(row, col - 1));
            DFS(stc);

        }

        // East
        if(maze.isValidCell(row + 1, col)) {
            maze.getCell(row + 1, col).setParent(temp);
            stc.push(maze.getCell(row + 1, col));
            DFS(stc);
        }

        // North
        if(maze.isValidCell(row, col + 1)) {
            maze.getCell(row, col + 1).setParent(temp);
            stc.push(maze.getCell(row, col + 1));
            DFS(stc);
        }
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> que = new LinkedList<MazeCell>();
        que.add(maze.getStartCell());
        BFS(que);


        return getSolution();

    }

    public void BFS(Queue<MazeCell> que) {

        que.peek().setExplored(true);

        if(que.peek().getRow() == maze.getEndCell().getRow() && que.peek().getCol() == maze.getEndCell().getCol()){
            return;
        }

        MazeCell temp = que.peek();
        int row = temp.getRow();
        int col = temp.getCol();

        // West
        if(maze.isValidCell(row - 1, col)) {
            maze.getCell(row - 1, col).setParent(temp);
            que.add(maze.getCell(row - 1, col));
            BFS(que);
        }

        // South
        if(maze.isValidCell(row, col - 1)) {
            maze.getCell(row, col - 1).setParent(temp);
            que.add(maze.getCell(row, col - 1));
            BFS(que);

        }

        // East
        if(maze.isValidCell(row + 1, col)) {
            maze.getCell(row + 1, col).setParent(temp);
            que.add(maze.getCell(row + 1, col));
            BFS(que);
        }

        // North
        if(maze.isValidCell(row, col + 1)) {
            maze.getCell(row, col + 1).setParent(temp);
            que.add(maze.getCell(row, col + 1));
            BFS(que);
        }
    }



    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);


        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
