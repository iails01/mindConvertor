package top.iails.mind;

import top.iails.mind.yd.MindTreeNode;

import java.util.List;

/**
 * Created by iails on 2019-10-24
 */
public class MDConvertor implements StringConvertor {

    @Override
    public String convert(MindTreeNode root) {
        final StringBuilder sb = new StringBuilder();
        printMd(root, 1, sb);
        return sb.toString();
    }

    private void printMd(MindTreeNode root, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append('#');
        }
        sb.append(' ');
        sb.append(root.getYDNode().getTopic());
        sb.append("\n");
        final List<MindTreeNode> children = root.getChildren();
        for (MindTreeNode child : children) {
            printMd(child, depth + 1, sb);
        }
    }

}
