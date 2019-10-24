# mindConvertor

引入依赖

```xml
<dependency>
    <groupId>top.iails</groupId>
    <artifactId>mindConvertor</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

使用

```java
public static void main(String[] args) {
    final YDMap ydMap = new YDMap(Paths.get("youdao.mindmap"));
    final StringConvertor mdConvertor = new MDConvertor();
    final StringConvertor kmConvertor = new KMConvertor();
    ydMap.toStr(mdConvertor);
    ydMap.writeMdTo(Paths.get("baidu.km"), kmConvertor);
}
```

通过实现 `StringConvertor` 可以自定义转换目标，自带 `MDConvertor` 和 `KMConvertor`

```java
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
```