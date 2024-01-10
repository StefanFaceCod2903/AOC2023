package org.example.Day8;

public class Node {
    private String left;
    private String right;

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "left='" + left + '\'' +
                ", right='" + right + '\'' +
                '}';
    }

    public Node(String left, String right) {
        this.left = left;
        this.right = right;
    }
}
