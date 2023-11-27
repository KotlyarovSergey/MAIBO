import java.util.LinkedList;
import java.util.List;

public class Parser {
    public List<String> excludeEntries(List<String> originalLines, List<String> forbiddenEntries){
        List<String> parsedList = new LinkedList<>();
        for (String line : originalLines) {
            Boolean skip = false;
            for (String deprectated : forbiddenEntries) {
                if(line.indexOf(deprectated) == 0){
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            parsedList.add(line);
        }

        return parsedList;
    }
}
