import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



/**
 * main
 */
public class main {
    //static String[] deprecatedStrings = new String[]{"Ничего ", "  0", "Отлично "};
    static List<String> deprecatedStrings = new LinkedList<>(
        Arrays.asList(
            "Ничего ", 
            "  0", 
            "Отлично "
            )
        );

    public static void main(String[] args) {
        List<String> allLines = readLines("from_web.txt");
        System.out.printf("Прочитано строк: %s\n", allLines.size());

        List<String> parsedLines = parseList(allLines);
        System.out.printf("Осталось строк: %s\n", parsedLines.size());

        String outFileName = "result.txt";
        if(writeToFile(outFileName, parsedLines)){
            System.out.printf("Файл %s создан\n", outFileName);
        }else{
            System.out.println("Error! файл не записан");
            if (showResult()) showAllLines(parsedLines);
        }
    }

    private static List<String> readLines(String fileName){
        List<String> list = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(Exception exception){
            System.out.println("File Read Error!");
        }
        //System.out.println("строк: " + list.size());
        return list;
    }    

    private static List<String> parseList(List<String> rawData){
        List<String> parsedList = new ArrayList<>();
        for (String line : rawData) {
            Boolean skip = false;
            for (String deprectated : deprecatedStrings) {
                if(line.indexOf(deprectated) == 0){
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            // if(line.indexOf("Ничего ") == 0) continue;
            // if(line.indexOf("  0  ") == 0) continue;
            // if(line.indexOf("Отлично ") == 0) continue;
            
            parsedList.add(line);
        }

        return parsedList;
    }

    static boolean writeToFile(String fileName, List<String> list){
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line : list) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch(Exception e){

            return false;
        }
        return true;
    }

    static boolean showResult(){
        System.out.print("Показать результат? [да\\нет] ");
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try{
            String ansver = bufferedReader.readLine().trim().toLowerCase(); //читаем строку с клавиатуры
            if(ansver == "да") return true;
        } catch(Exception e){

        }
        return false;
    }

    private static void showAllLines(List<String> list){
        for (String line : list) {
            System.out.println(line);
        }
    }
}


