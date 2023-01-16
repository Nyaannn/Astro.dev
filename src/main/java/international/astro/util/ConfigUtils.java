package international.astro.util;

import org.lwjgl.Sys;

public class ConfigUtils {
    public static void loadconfig(String dir){
        for(String i:FileInteractor.ReadFile(dir)){
            System.out.println(i);
        }

    }
}
