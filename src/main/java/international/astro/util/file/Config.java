package international.astro.util.file;

import com.google.gson.JsonObject;
import international.astro.Astro;
import net.minecraft.client.Minecraft;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class Config extends Thread{

    public static Minecraft mc = Minecraft.getMinecraft();
    public static final String FOLDER = mc.gameDir+File.separator+"/Astro.dev";
    public static final String HACKS_FOLDER = mc.gameDir+File.separator+"/Astro.dev/modules";



    public static void loadConfig(String dir){
        for(String i:ReadFile(dir)){
            System.out.println(i);
        }

    }

    public static void saveConfig(String dir){

    }


    public static ArrayList<String> ReadFile(String dir) {
        ArrayList<String> testicle = new ArrayList<>();
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
        } catch (Exception e) {
            Astro.sendErrorMsg(e.getMessage());
        }
        return testicle;
    }
    public static void WriteFile(String dir,String Text) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileDirectory = dir;
        try {
            fileDirectory = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = Text;
        try {
            content = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File(fileDirectory);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(content);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void AppendFile(String dir,String Text) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileDirectory = dir;
        try {
            fileDirectory = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = Text;
        try {
            content = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File(fileDirectory);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
            fileWriter.append(content);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
