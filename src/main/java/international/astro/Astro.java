package international.astro;
import com.mojang.realmsclient.gui.ChatFormatting;
import international.astro.graphics.clickgui.ClickGui;
import international.astro.util.RenderUtils;
import international.astro.util.RotationManager;
import international.astro.util.color.ColorManager;
import international.astro.command.CommandManager;
import international.astro.events.ChatEvent;
import international.astro.events.KeyEvent;
import international.astro.hack.HackManager;
import international.astro.util.font.GlyphPageFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;


@Mod(modid = Astro.MODID, name = Astro.NAME, version = Astro.VERSION )
public class Astro {

    public static final Logger LOGGER = LogManager.getLogger("Astro.dev");
    public static final String NAME = "Astro.dev";
    public static final String MODID = "astro";
    public static final String VERSION = "2.2";
    public static Minecraft mc = Minecraft.getMinecraft();
    public static CommandManager commandManager;
    public static ClickGui clickGui;
    public static HackManager hackManager;
    public static ColorManager colorManager;
    public static GlyphPageFontRenderer font;
    public static GlyphPageFontRenderer MenuFont;

    public static RotationManager rotationManager;
    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        log("Client PreInitialization");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        log("Client initialization");
        Display.setTitle(NAME+" b"+VERSION);
        Discord.startRPC();
        hackManager = new HackManager();
        commandManager = new CommandManager();
        font = GlyphPageFontRenderer.create("Segoe UI", 18, true, false, false);
        MenuFont = GlyphPageFontRenderer.create("Azonix", 20, true, false, false);
        colorManager = new ColorManager(120, 120, 255, 255);
        clickGui = new ClickGui();
        //Config.loadConfig();
        rotationManager=new RotationManager();
        RenderUtils.setWindowIcon();
        MinecraftForge.EVENT_BUS.register(new KeyEvent());
        MinecraftForge.EVENT_BUS.register(new ChatEvent());
    }

    public static void onShutdown(){
        //Config.saveConfig();
        Discord.stopRPC();
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        log("Client PostInitialization");
    }

    public static void log(String message){
        LOGGER.info( "[Astro.dev] " + message);
    }
    public static void sendMsg(String s){
        mc.player.sendMessage(new TextComponentString("["+ ChatFormatting.BLUE+"Astro.dev"+ChatFormatting.WHITE+"] " + s));
    }
    public static void sendErrorMsg(String s){
        mc.player.sendMessage(new TextComponentString("["+ ChatFormatting.BLUE+"Astro.dev"+ChatFormatting.WHITE+"] [" + ChatFormatting.RED+"ERROR"+ ChatFormatting.WHITE +"] "+ s));
    }
    public static void sendDebugMsg(String s){
        mc.player.sendMessage(new TextComponentString("["+ ChatFormatting.BLUE+"Astro.dev"+ChatFormatting.WHITE+"] [" + ChatFormatting.YELLOW+"DEBUG"+ ChatFormatting.WHITE +"] "+ s));
    }

}
