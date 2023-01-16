package international.astro.mixins.mixin.net.minecraft.client.gui;

import international.astro.Astro;
import international.astro.hack.hacks.misc.ChatPlus;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({GuiNewChat.class})
public class MixinGuiNewChat {

    @Redirect(method = {"drawChat"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void drawChat(int left, int top, int right, int bottom, int color) {
        Gui.drawRect(left, top, right, bottom, (Astro.hackManager.getHack("ChatPlus").isEnabled() && ChatPlus.getInstance.clearChat.isEnabled()) ? 0 : color);
    }

}
