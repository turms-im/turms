package im.turms.plugin.antispam.ac;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * @author James Chen
 */
public class TrieFactory {

    private TrieFactory() {
    }

    @SneakyThrows
    public static AhoCorasickDoubleArrayTrie buildTrie(Path path, String charsetName) {
        List<char[]> list = new LinkedList<>();
        try (InputStream stream = Files.newInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charsetName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.toCharArray());
            }
        }
        return new AhoCorasickDoubleArrayTrie(list);
    }

}
