package international.astro.mixins.mixin.net.minecraft.client;

import international.astro.Astro;
import international.astro.graphics.menu.AstroMainMenu;
import international.astro.mixins.accessor.IMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin({Minecraft.class})
public abstract class MixinMinecraft implements IMinecraft {

    @Shadow private Timer timer;

    @Override
    public Timer getTimer() {
        return timer;
    }

    @Accessor
    @Override
    public abstract void setRightClickDelayTimer(int delay);

    @Shadow public abstract void displayGuiScreen(@Nullable GuiScreen p_displayGuiScreen_1_);

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        displayGuiScreen(new AstroMainMenu());
    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void shutdownMinecraftApplet(CallbackInfo ci){
        Astro.onShutdown();
    }


}
