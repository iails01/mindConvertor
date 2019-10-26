package top.iails.mind.yd;

import com.alibaba.fastjson.JSON;
import top.iails.mind.StringConvertor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by iails on 2019-10-24
 */
public class YDMap {

    private Path mindmapPath;

    private MindMap mindMap;

    private MindTreeNode rootMindTreeNode;

    public YDMap(Path mindmapPath) {
        this.mindmapPath = mindmapPath;
    }

    public String toStr(StringConvertor convertor) {
        if (rootMindTreeNode == null) {
            parse(mindmapPath);
        }
        return convertor.convert(rootMindTreeNode);
    }

    public void writeStrTo(Path dest, StringConvertor convertor) {
        if (rootMindTreeNode == null) {
            parse(mindmapPath);
        }
        try (final BufferedWriter bw = Files.newBufferedWriter(dest)) {
            bw.write(convertor.convert(rootMindTreeNode));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(Path mindmapPath) {
        this.mindmapPath = mindmapPath;
        parse(mindmapPath);
    }

    private void parse(Path mindmapPath) {
        try {
            final byte[] bytes = Files.readAllBytes(mindmapPath);
            mindMap = JSON.parseObject(bytes, MindMap.class);
            if (mindMap == null || mindMap.getNodes() == null) {
                throw new RuntimeException("mindmap format incorrect");
            }
            rootMindTreeNode = buildMdTree();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MindTreeNode buildMdTree() {
        final Map<String, YDNode> map = mindMap.getNodes().stream()
            .collect(Collectors.toMap(YDNode::getId, n -> n));
        final YDNode root = map.get("root");
        if (root == null) {
            return null;
        }
        final MindTreeNode rootMindTreeNode = new MindTreeNode();
        rootMindTreeNode.setYDNode(root);
        buildMdTree(rootMindTreeNode);
        return rootMindTreeNode;
    }

    private void buildMdTree(MindTreeNode root) {
        final List<YDNode> children = findChildren(root.getYDNode());
        final List<MindTreeNode> rootChildren = root.getChildren();
        for (YDNode child : children) {
            final MindTreeNode treeNode = new MindTreeNode();
            treeNode.setYDNode(child);
            buildMdTree(treeNode);
            rootChildren.add(treeNode);
        }
    }

    private List<YDNode> findChildren(YDNode YDNode) {
        return mindMap.getNodes().stream()
            .filter(n -> n.getParentid() != null && n.getParentid().equals(YDNode.getId()))
            .collect(Collectors.toList());
    }
}
