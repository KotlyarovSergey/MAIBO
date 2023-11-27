import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    
    public List<String> readLines(String fileName){
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
            System.out.printf("ERROR! File \"%s\" not read!\n", fileName);
        }
        //System.out.println("строк: " + list.size());
        return list;
    }

    public boolean writeLines(String fileName, List<String> lineList){
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line : lineList) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch(Exception e){
            System.out.printf("ERROR! File \"%s\" can not write!\n", fileName);
            return false;
        }
        return true;
    }
}
