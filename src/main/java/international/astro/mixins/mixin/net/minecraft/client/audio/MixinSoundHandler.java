package international.astro.mixins.mixin.net.minecraft.client.audio;

import international.astro.mixins.accessor.ISoundHandler;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({SoundHandler.class})
public class MixinSoundHandler implements ISoundHandler {
    @Shadow @Final private SoundManager sndManager;

    @Override
    public SoundManager getManager() {
        return sndManager;
    }
}
