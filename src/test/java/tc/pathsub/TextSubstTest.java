package tc.pathsub;

import org.junit.Test;

import java.util.LinkedHashMap;

public class TextSubstTest {
    private final String[] LINES = {
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ",
        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris ",
        "nisi ut aliquip ex ea commodo consequat. ",
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum ",
        "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, ",
        "sunt in culpa qui officia deserunt mollit anim id est laborum."
    };
    private final String TEXT = String.join("\n", LINES);

    @Test
    public void testTransform() {
        LinkedHashMap<String, String> xfm = new LinkedHashMap<>();
        xfm.put("dolor", "SADNESS");
        xfm.put("id est laborum.", "ad infinitum!");
        xfm.put("Lorem ipsum", "Via dolorosa... ");
        TextSubst ps = new TextSubst(xfm);
        String found = ps.transform(TEXT);
        System.err.println(found);
    }
}
