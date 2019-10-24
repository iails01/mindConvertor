package top.iails.mind;

import com.alibaba.fastjson.JSON;
import top.iails.mind.yd.MindTreeNode;
import top.iails.mind.yd.YDNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度脑图
 * Created by iails on 2019-10-24
 */
public class KMConvertor implements StringConvertor {

    // 默认参数
    private String template = "right";
    private String theme = "fresh-blue";

    private final String version = "1.4.43";

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getVersion() {
        return version;
    }

    class KMMindMap {
        private KMTreeNode root;
        private String template = KMConvertor.this.template;
        private String theme = KMConvertor.this.theme;
        private String version = KMConvertor.this.version;

        public KMTreeNode getRoot() {
            return root;
        }

        public void setRoot(KMTreeNode root) {
            this.root = root;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    class KMTreeNode {
        private Map<String, String> data = new HashMap<>();
        private List<KMTreeNode> children = new ArrayList<>();

        public Map<String, String> getData() {
            return data;
        }

        public void setData(Map<String, String> data) {
            this.data = data;
        }

        public List<KMTreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<KMTreeNode> children) {
            this.children = children;
        }
    }

    @Override
    public String convert(MindTreeNode root) {
        final KMMindMap kmMindMap = new KMMindMap();
        kmMindMap.root = toKMTree(root);
        return JSON.toJSONString(kmMindMap);
    }

    public KMTreeNode toKMTree(MindTreeNode mindTreeNodeRoot) {
        final YDNode ydNode = mindTreeNodeRoot.getYDNode();
        final KMTreeNode kmTreeNode = new KMTreeNode();
        kmTreeNode.data.put("text", ydNode.getTopic());
        final List<MindTreeNode> children = mindTreeNodeRoot.getChildren();
        for (MindTreeNode child : children) {
            kmTreeNode.children.add(toKMTree(child));
        }
        return kmTreeNode;
    }
}

