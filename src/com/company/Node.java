package com.company;

public class Node {
    private int x,y;
    private boolean IsVisited;
    public Node(int x, int y,boolean IsVisited) {
        this.x = x;
        this.IsVisited = IsVisited;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
