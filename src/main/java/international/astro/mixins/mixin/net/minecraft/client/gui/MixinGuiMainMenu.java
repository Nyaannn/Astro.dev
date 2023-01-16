package international.astro.mixins.mixin.net.minecraft.client.gui;

import international.astro.graphics.menu.AstroMainMenu;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {

    @Inject(method = "addSingleplayerMultiplayerButtons", at = @At("HEAD"))
    public void addSingleplayerMultiplayerButtons(int p_addSingleplayerMultiplayerButtons_1_, int p_addSingleplayerMultiplayerButtons_2_, CallbackInfo ci){
        this.buttonList.add(new GuiButton(69420, 10, 10,100,20,  "AstroMenu"));
    }

    @Inject(method = "actionPerformed", at = @At("HEAD"))
    public void actionPerformed(GuiButton p_actionPerformed_1_, CallbackInfo ci){
        if (p_actionPerformed_1_.id == 69420) {
            this.mc.displayGuiScreen(new AstroMainMenu());
        }
    }


}
