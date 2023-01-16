package international.astro.util;
import international.astro.Astro;
import java.io.*;
import java.util.ArrayList;
public class FileInteractor {
    public static ArrayList<String> ReadFile(String dir) {
        ArrayList<String> testicle = new ArrayList<String>();
        testicle.clear();
        String fileDirectory = dir;
        try {
            File file = new File(fileDirectory);
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fileReader.readLine()) != null) {
                testicle.add(line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            Astro.sendErrorMsg(e.getMessage());
        } catch (IOException e) {
            Astro.sendErrorMsg(e.getMessage());
        }
        return testicle;
    }
    public static void WriteFile(String dir,String Text) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileDirectory = dir;
        try {
            fileDirectory = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = Text;
        try {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(fileDirectory);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void AppendFile(String dir,String Text) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileDirectory = dir;
        try {
            fileDirectory = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = Text;
        try {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(fileDirectory);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
            fileWriter.append(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
