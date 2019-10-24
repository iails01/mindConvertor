package top.iails.mind.yd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iails on 2019-10-24
 */
public class MindTreeNode {
    private List<MindTreeNode> children = new ArrayList<>();
    private top.iails.mind.yd.YDNode YDNode;

    public List<MindTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<MindTreeNode> children) {
        this.children = children;
    }

    public YDNode getYDNode() {
        return YDNode;
    }

    public void setYDNode(YDNode YDNode) {
        this.YDNode = YDNode;
    }
}
