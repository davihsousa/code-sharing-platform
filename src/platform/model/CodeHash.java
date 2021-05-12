package platform.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CodeHash {

    private static CodeHash codeHash = null;
    private static Integer id = 0;
    private static HashMap<Integer, Code> codeHashMap = new HashMap<>();

    private CodeHash() {
    }

    public static CodeHash getInstance() {
        if (codeHash == null) {
            codeHash = new CodeHash();
        }
        return codeHash;
    }

    public static String setCodeHashMap(Code code) {
        id += 1;
        codeHashMap.put(id, code);
        return "{ \"id\" : \"" + id + "\" }";
    }

    public static Code getCodeHashMap(Integer id) {
        return codeHashMap.get(id);
    }

    public static List<Code> getLatest() {
        List<Code> codeList = new ArrayList<>();
        int counter = id;
        int counterPrinted = 0;
        while (counter > 0 && counterPrinted < 10) {
            codeList.add(CodeHash.getCodeHashMap(counter));
            counter -= 1;
            counterPrinted += 1;
        }
        return codeList;
    }
}
