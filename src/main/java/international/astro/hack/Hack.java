package international.astro.hack;

import com.mojang.realmsclient.gui.ChatFormatting;
import international.astro.Astro;
import international.astro.hack.option.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class Hack {
    private Construct construct = getClass().getAnnotation(Construct.class);
    private String name = construct.name();
    private String description = construct.description();
    private Category category = construct.category();
    private int bind;
    private boolean enabled;
    public static final Minecraft mc = Minecraft.getMinecraft();

    private List<Option> options = new ArrayList<>();


    public void onEnable() {}
    public void onDisable() {}

    public static void onUpdate(){}

    public void enable() {
        setEnabled(true);
        onEnable();
        Astro.sendMsg("toggled " + getName() +" To True");
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void disable() {
        setEnabled(false);
        onDisable();
        Astro.sendMsg("toggled " + getName() +" To False");
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void toggle() {
        if (isEnabled()) disable();
        else enable();
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public List<Option> getOptions(){
        return options;
    }

    public void addOption(Option option){
        options.add(option);
    }

    public void addOptions(Option... options){
        for(Option option : options){
            addOption(option);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public enum Category {
        COMBAT("Combat"),
        EXPLOIT("Exploit"),
        CLIENT("Client"),
        RENDER("Render"),
        MOVEMENT("Movement"),
        RANDOM("Random"),
        MISC("Misc");

        private String name;

        Category(String name) {
            setName(name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Construct {
        String name();
        String description();
        Category category();
    }
}
