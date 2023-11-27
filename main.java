import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class main {

    static String inputFileName = "from_web.txt";
    static String resultFileName = "result.txt";

    static List<String> deprecatedStrings = new LinkedList<>(
        Arrays.asList(
            "Ничего ", 
            "  0", 
            "Отлично "
            )
        );

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker();

        List<String> allLines = fileWorker.readLines(inputFileName);
        System.out.printf("Прочитано строк: %s\n", allLines.size());

        Parser parser = new Parser();
        List<String> parsedLines = parser.excludeEntries(allLines, deprecatedStrings);
        System.out.printf("Осталось строк: %s\n", parsedLines.size());

        boolean writeFileIsOK = fileWorker.writeLines(resultFileName, parsedLines);
        if(writeFileIsOK){
            System.out.printf("Файл %s создан\n", resultFileName);
        }else{
            if (showResult()) showAllLines(parsedLines);
        }
    }

   
    private static boolean showResult(){
        Scanner scanner = new Scanner(System.in, "Cp866");
        System.out.print("Показать результат? [да\\нет] ");
        String ansver = scanner.nextLine();
        scanner.close();
        if(ansver.equals("да")){
            return true;
}
        return false;
    }

    private static void showAllLines(List<String> list){
        System.out.println("Print: ");
        for (String line : list) {
            System.out.println(line);
        }
    }
}


