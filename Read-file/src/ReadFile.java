import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\User\\Desktop\\note.txt");
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null){
                String[] country = line.split(",");
                System.out.println(country[5]);
            }
        } catch (Exception e){
            System.out.println("Loi");
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (Exception e){
                    System.out.println("lai loi roy");
                }
            }
        }

    }
}
