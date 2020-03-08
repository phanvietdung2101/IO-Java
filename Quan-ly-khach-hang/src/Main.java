import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
        source.delete();
    }
    public static void main(String[] args) throws Exception {
//        splitFile("C:\\Users\\User\\Desktop\\D.png","D:\\",3);
        joinFile("D:\\D.png");
    }


    public static void splitFile(String source, String des, int numberFile) throws Exception{
        File sourceFile = new File(source);
        if(sourceFile.isFile() && sourceFile.exists()){
            long sizeSplitFile = sourceFile.length() / numberFile;
            FileInputStream is = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024];
            for(int i = 1; i <= numberFile; i++){
                long length = 0;
                int a = 0;
                FileOutputStream os = new FileOutputStream(des + sourceFile.getName() + "_" + i);
                while((a = is.read(buffer)) != -1){
                    os.write(buffer,0, a);
                    length += a;
                    if(length >= sizeSplitFile){
                        break;
                    }
                }
                os.flush();
                os.close();
        }
            is.close();
        }
    }

    public static void joinFile(String des) throws Exception {
        File fileOutput = new File(des);
        FileOutputStream output = new FileOutputStream(fileOutput);
        int i = 1;
        int data = 0;
        while(true){
            File inputFile = new File(des + "_" + i);
            if(!inputFile.exists()){
                break;
            }
            FileInputStream input = new FileInputStream(inputFile);
            while((data = input.read()) != -1){
                output.write(data);
            }
            i++;
            input.close();
        }
        output.flush();
        output.close();
    }
}
