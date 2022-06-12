package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //read 2-D array from file
        // C:\Users\Asus\IdeaProjects\Maze\src\com\company\array.txt
        String mazePath = System.getProperty("user.dir")+"\\src\\com\\company\\array.txt";
        String priorityPath =System.getProperty("user.dir")+"\\src\\com\\company\\priority.txt";
        int [][] myArray = ReadMaze(mazePath);
        PrintMaze(myArray);
        int[] priority = ReadPriority(priorityPath);
        //System.out.println(Arrays.deepToString(new int[][]{priority}));
        MazeSolver ms = new MazeSolver(myArray,priority);
        boolean solved = ms.solveMaze();
        if (solved)
            printSolution(ms.solution);
        System.out.println(solved);
    }
    static void printSolution(char sol[][])
    {
        System.out.println("--------solution---------");

        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[0].length; j++) {
                if (sol[i][j] != '*')
                    sol[i][j] = '-';
                System.out.print(
                        " " + sol[i][j] + " ");
            }
            System.out.println();
        }


    }
    static void PrintMaze(int maze[][])
    {
        System.out.println("-------Maze--------");

        char sol[][] = new char[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 0)
                    sol[i][j] = '-';
                else sol[i][j] = '#';
                System.out.print(
                        " " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static int[] ReadPriority(String priorityPath) {
        Scanner sc = null;
        try {
            FileReader proReader = new FileReader(priorityPath) ;
            BufferedReader proBReader = new BufferedReader(proReader);
            sc = new Scanner(proBReader);
        }catch (Exception e){
            e.printStackTrace();

        }
        String[] proT={};
        while(sc.hasNextLine()) {
            proT = sc.nextLine().trim().split(" ");
        }
        int [] priority = new int[proT.length];
        for (int i = 0; i < proT.length; i++) {
            switch (proT[i]){
                case "N":
                    priority[i] = 0;
                    break;
                case "NE":
                    priority[i] = 7;
                    break;
                case "E":
                    priority[i] = 6;
                    break;
                case "SE":
                    priority[i] = 5;
                    break;
                case "S":
                    priority[i] = 4;
                    break;
                case "SW":
                    priority[i] = 3;
                    break;
                case "W":
                    priority[i] = 2;
                    break;
                default:
                    priority[i] = 1;
            }
        }
        return priority;
    }

    public static int[][] ReadMaze(String filePath) {
        Scanner sc = null;
        int row = 0;
        int character=0;
        try {
            FileReader columnReader = new FileReader(filePath) ;
            BufferedReader counter = new BufferedReader(columnReader);
            String line = "";
            while ((line = counter.readLine()) != null) {character=line.length();  row++;}
            FileReader fileReader = new FileReader(filePath) ;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            sc = new Scanner(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rows = row;
        int columns = (character/2)+1;
        int [][] myArray = new int[rows][columns];
        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    myArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return myArray;
    }

}
