package international.astro.util.file;

import com.google.gson.*;
import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;
import it.unimi.dsi.fastutil.objects.Object2BooleanLinkedOpenCustomHashMap;
import net.minecraft.client.Minecraft;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Config extends Thread{
    private static final File abstractDir = new File("Astro");
    private static final String modulesPath = abstractDir.getAbsolutePath() + "/modules";

    public static void loadConfig(){
        loadModules();
    }


    private static void loadModules(){
        for(Hack h : Astro.hackManager.getHacks()){
            loadHack(h);
        }
    }

    private static void loadHack(Hack hack){
        try {
            Path path = Paths.get(modulesPath, hack.getName() + ".json");
            if (!path.toFile().exists()) return;
            String rawJson = loadFile(path.toFile());
            JsonObject jsonObject = new JsonParser().parse(rawJson).getAsJsonObject();

            if(jsonObject.get("Enabled") != null){
                if(jsonObject.get("Enabled").getAsBoolean()){
                    hack.setEnabled(true);
                    hack.enable();
                }else {
                    hack.setEnabled(false);
                    hack.disable();
                }
            }
            if(jsonObject.get("Bind") != null){
                hack.setBind(jsonObject.get("Bind").getAsInt());
            }
            for(Option o : hack.getOptions()){
                if(o instanceof OList){
                    if(jsonObject.get(o.getName()) != null) {
                        ((OList) o).setMode(jsonObject.get(o.getName()).getAsString());
                    }
                }
                if(o instanceof ODouble){
                    if(jsonObject.get(o.getName()) != null) {
                        ((ODouble) o).setValue(jsonObject.get(o.getName()).getAsFloat());
                    }
                }
                if(o instanceof OBoolean){
                    if(jsonObject.get(o.getName()) != null) {
                        ((OBoolean) o).setEnabled(jsonObject.get(o.getName()).getAsBoolean());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        saveConfig();
    }
    private static void saveConfig(){
        if (!abstractDir.exists() && !abstractDir.mkdirs()) System.out.println("Failed to create config folder");
        if (!new File(modulesPath).exists() && !new File(modulesPath).mkdirs()) {
            System.out.println("Failed to create modules folder");
        }
        saveModules();
    }


    private static void saveModules(){
        for(Hack h : Astro.hackManager.getHacks()){
            saveHack(h);
        }
    }


    private static void saveHack(Hack h){
        try {
            Path path = Paths.get(modulesPath, h.getName() + ".json");
            createFile(path);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Enabled", new JsonPrimitive(h.isEnabled()));
            jsonObject.add("Bind", new JsonPrimitive(h.getBind()));

            for(Option o : h.getOptions()){
                if(o instanceof OBoolean){
                    jsonObject.add(o.getName(), new JsonPrimitive(((OBoolean) o).isEnabled()));
                }
                if(o instanceof ODouble){
                    jsonObject.add(o.getName(), new JsonPrimitive(((ODouble) o).getValue()));

                }
                if(o instanceof OList){
                    jsonObject.add(o.getName(), new JsonPrimitive(((OList) o).getMode()));
                }
            }
            Gson gson = new Gson();
            Files.write(path, gson.toJson(new JsonParser().parse(jsonObject.toString())).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createFile(Path path) {
        if (Files.exists(path)) new File(path.normalize().toString()).delete();
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String loadFile(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file.getAbsolutePath());
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
