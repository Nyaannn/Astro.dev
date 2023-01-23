package international.astro.hack;

import international.astro.hack.hacks.client.BackupCaller;
import international.astro.hack.hacks.client.ClickGuiMod;
import international.astro.hack.hacks.client.HUD;
import international.astro.hack.hacks.combat.*;
import international.astro.hack.hacks.exploits.*;
import international.astro.hack.hacks.misc.*;
import international.astro.hack.hacks.movement.*;
import international.astro.hack.hacks.random.AntiYonkie;
import international.astro.hack.hacks.random.Spinjitzu;
import international.astro.hack.hacks.render.*;

import java.util.ArrayList;
import java.util.List;

public class HackManager{

    public List<Hack> hacks = new ArrayList<>();

    public HackManager(){
        add(new CornerClip());
        add(new KillAura());
        add(new XCarry());
        add(new NoFall());
        add(new NoSlow());
        add(new Timer());
        add(new Phase());
        add(new ChatPlus());
        add(new ViewTweaks());
        add(new ClickGuiMod());
        add(new SelfWeb());
        add(new MoonWalk());
        add(new Burrow());
        add(new Fakeplayer());
        add(new Disabler());
        add(new Flight());
        add(new HUD());
        add(new BedAura());
        add(new FreeLook());
        add(new Spinjitzu());
        add(new NoRotate());
        add(new AutoIgnore());
        add(new AntiYonkie());
        add(new Surround());
        add(new AntiAntiBed());
        add(new AntiKB());
        add(new IceSpeed());
        add(new FootExp());
        add(new Crits());
        add(new AutoTotem());
        add(new Animations());
        add(new FastUse());
        add(new InstaMine());
        add(new GodMode());
        add(new BackupCaller());
    }

    public List<Hack> getHacks() {
        return hacks;
    }

    public List<Hack> getEnabledHacks(){
        List<Hack> enabled = new ArrayList<>();
        for(Hack hack : hacks){
            if(hack.isEnabled()){enabled.add(hack);}
        }
        return enabled;
    }

    public Hack getHack(String name){
        Hack h = null;
        for(Hack hack : hacks){
            if(name.equalsIgnoreCase(hack.getName())){
                h = hack;
            }
        }
        return h;
    }

    public List<Hack> getHacksInCategory(Hack.Category category){
        List<Hack> cathacks = new ArrayList<>();
        for(Hack hack : hacks){
            if(hack.getCategory() == category){
                cathacks.add(hack);
            }
        }
        return cathacks;
    }

    private void add(Hack hack){
        hacks.add(hack);
    }
}

