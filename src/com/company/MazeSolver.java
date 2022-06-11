package com.company;

import java.util.Stack;

public class MazeSolver {
    private int[][] maze;
    private int[] priority;
    private boolean[][] visiteds;
    private int x,y;
    private boolean IsSolved;
    private static int rows,columns;
    private Stack<Node> stack;
    public char[][] solution;
    public MazeSolver(int[][] maze, int[] priority) {
        this.maze = maze;
        this.priority = priority;
        this.rows = maze.length;
        this.columns = maze[0].length;
        this.IsSolved = false;
        this.stack = new Stack<Node>();
        this.visiteds = new boolean[maze.length][maze[0].length];
        this.solution = new char[maze.length][maze[0].length];
    }
    //0 is available node
    //1 is not available node
    public boolean solveMaze() {
        x=0;
        y =0;
        visiteds[x][y] = true;
        if (maze[x][y] == 1)
            return false;
        solution[x][y]='*';
        while (!GoalTest(x,y)){
            boolean isChance= true;
            for (int k = 0; k < priority.length; k++) {
                if (!CheckValidity(x,y,priority[k])){
                    isChance = false;
                    continue;
                }else {
                    isChance= true;
                     FixXYValue(x,y,priority[k]);
                     solution[x][y]='*';
                     visiteds[x][y] = true;
                     stack.push(new Node(x,y,true));
                     break;
                }
            }
            if (!isChance){
                if (stack.empty()){
                    break;
                }
                Node node = stack.pop();
                x = node.getX();
                y = node.getY();
            }
        }
        return IsSolved;
    }

    private boolean GoalTest(int m, int n) {
        if (m == rows -1 && n == columns -1 && maze[m][n] == 0) {
            IsSolved =true;
            return true;
        }
        return false;
    }

    private void FixXYValue(int m, int n, int priority) {
        switch (priority){
            case 0:
                x = m-1;
                break;
            case 1:
                x=m-1;
                y= n+1;
                break;
            case 2:
                y =n+1;
                break;
            case 3:
                x=m+1;
                y =n+1;
                break;
            case 4:
                x = m+1;
                break;
            case 5:
                x = m+1;
                y = n-1;
                break;
            case 6:
                y=n-1;
                break;
            default:
                x=m-1;
                y=n-1;
        }
    }

    private boolean CheckValidity(int m, int n, int priority) {
        if (m >= rows || n>= columns){
            return false;
        }
        boolean isValid = true;
        switch (priority){
            case 0:
                if (m-1 < 0 || maze[m-1][n]==1)
                    isValid = false;
                else if (visiteds[m-1][n])
                    isValid = false;
                break;
            case 1:
                if (m-1 < 0 || n+1 >= columns || maze[m-1][n+1]==1)
                    isValid = false;
                else {
                        if(visiteds[m-1][n+1])isValid = false;
                        if (maze[m-1][n] == 1 && maze[m][n+1] == 1) isValid = false;
                    }
                break;
            case 2:
                if (n+1 >= columns || maze[m][n+1]==1)
                    isValid = false;
                else if(visiteds[m][n+1])isValid = false;
                break;
            case 3:
                if (m+1 >= rows || n+1 >= columns || maze[m+1][n+1]==1)
                    isValid = false;
                else {
                    if (visiteds[m + 1][n + 1]) isValid = false;
                    if (maze[m + 1][n] == 1 && maze[m][n + 1] == 1) isValid = false;
                }
                break;
            case 4:
                if (m+1 >= rows || maze[m+1][n]==1)
                    isValid = false;
                else if(visiteds[m+1][n]) isValid = false;
                break;
            case 5:
                if (m+1 >= rows || n-1 < 0 || maze[m+1][n-1]==1)
                    isValid = false;
                else {
                    if (visiteds[m + 1][n - 1]) isValid = false;
                    if (maze[m + 1][n] == 1 && maze[m][n - 1] == 1) isValid = false;
                }
                break;
            case 6:
                if (n-1 < 0 || maze[m][n-1]==1)
                    isValid = false;
                else if(visiteds[m][n-1]) isValid = false;
                break;
            default:
                if (m-1 < 0 || n-1 < 0 || maze[m-1][n-1]==1)
                    isValid = false;
                else {
                    if(visiteds[m-1][n-1]) isValid = false;
                    if (maze[m][n-1] == 1 && maze[m-1][n] == 1) isValid = false;
                }
        }
        return isValid;
    }

}
