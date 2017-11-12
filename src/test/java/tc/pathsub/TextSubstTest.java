package tc.pathsub;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TextSubstTest {
    private static final String[] LINES = {
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, ",
        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris ",
        "nisi ut aliquip ex ea commodo consequat. ",
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum ",
        "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, ",
        "sunt in culpa qui officia deserunt mollit anim id est laborum."
    };
    private static final String TEXT = String.join("\n", LINES);

    @Test
    public void testTransform() {
        Map<String, String> xfm = new HashMap<>();
        xfm.put("dolor", "SADNESS");
        xfm.put("id est laborum.", "ad infinitum!");
        xfm.put("Lorem ipsum", "Via dolorosa... ");
        xfm.put("sunt in culpa qui officia deserunt ", "");
        TextSubst ps = new TextSubst(xfm);
        String found = ps.transform(TEXT);
        System.err.println(found);
    }
}
