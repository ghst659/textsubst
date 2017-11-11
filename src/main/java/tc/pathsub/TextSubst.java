package tc.pathsub;

import java.util.*;

public class TextSubst {
    private LinkedHashMap<String, String> tbl = new LinkedHashMap<>();
    public TextSubst(LinkedHashMap<String,String> substitutions) {
        tbl.putAll(substitutions);
    }
    private static class Delta {
        public final int beginIndex;
        public final int endIndex;
        public final String newText;
        public Delta(int beg, int end, String text) {
            assert beg < end;
            assert text != null;
            beginIndex = beg;
            endIndex = end;
            newText = text;
        }
        public String toString() {
            return String.format("(%d, %d, %s)", beginIndex, endIndex, newText);
        }
    }
    private List<Delta> study(String inputText) {
        List<Delta> result = new ArrayList<>();
        for (Map.Entry<String, String> kv: tbl.entrySet()) {
            String searchText = kv.getKey();
            int scanStart = 0;
            for (int beg = inputText.indexOf(searchText, scanStart);
                 beg >= 0;
                 beg = inputText.indexOf(searchText, scanStart)) {
                scanStart = beg + searchText.length();
                result.add(new Delta(beg, scanStart, kv.getValue()));
            }
        }
        result.sort((a, b) -> Integer.compare(a.beginIndex, b.beginIndex));
        return result;
    }
    private String applyDeltas(String inputText, List<Delta> deltas) {
        if (deltas == null || deltas.size() == 0) {
            return inputText;
        }
        StringBuffer buf = new StringBuffer();
        if (inputText != null && inputText.length() > 0) {
            int L = inputText.length();
            int currentStart = 0;
            for (Delta d: deltas) {
                assert d.beginIndex >= currentStart;
                if (d.endIndex <= L) {
                    buf.append(inputText.substring(currentStart, d.beginIndex));
                    buf.append(d.newText);
                    currentStart = d.endIndex;
                } else {
                    break;
                }
            }
            if (currentStart < L) {
                buf.append(inputText.substring(currentStart, L));
            }
        }
        return buf.toString();
    }
    public String transform(String inputText) {
        List<Delta> changes = study(inputText);
        return applyDeltas(inputText, changes);
    }
}
