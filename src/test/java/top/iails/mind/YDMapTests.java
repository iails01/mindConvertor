package top.iails.mind;

import org.junit.Test;
import top.iails.mind.yd.YDMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by iails on 2019-10-24
 */
public class YDMapTests {

    @Test
    public void testToStr() throws URISyntaxException {
        final URL resource = YDMapTests.class.getClassLoader().getResource("youdao.mindmap");
        final YDMap ydMap = new YDMap(Paths.get(resource.toURI()));
        final MDConvertor mdConvertor = new MDConvertor();
        final String str = ydMap.toStr(mdConvertor);
        // hard to test
        assert str != null && str.length() >= 70;
    }

    @Test
    public void testWriteToFile() throws URISyntaxException, IOException {
        final URL resource = YDMapTests.class.getClassLoader().getResource("youdao.mindmap");
        final URL destResource = YDMapTests.class.getResource("testWriteToFile.md");
        final YDMap ydMap = new YDMap(Paths.get(resource.toURI()));
        final MDConvertor mdConvertor = new MDConvertor();
        final Path dest = Paths.get(destResource.toURI());
        ydMap.writeMdTo(dest, mdConvertor);
        final byte[] bytes = Files.readAllBytes(dest);
        final String str = new String(bytes, Charset.defaultCharset());
        // hard to test
        assert str != null && str.length() >= 70;
    }
}
