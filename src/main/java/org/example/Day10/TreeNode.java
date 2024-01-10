package org.example.Day10;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    private Integer x;
    private Integer y;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    private List<TreeNode> childNodes;

    public TreeNode(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.childNodes = new LinkedList<>();
    }

    public void addChild(TreeNode childNode) {
        this.childNodes.add(childNode);
    }


    public List<TreeNode> getChildNodes() {
        return childNodes;
    }
}
