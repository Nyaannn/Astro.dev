package international.astro.hack.hacks.misc;

import com.mojang.authlib.GameProfile;
import international.astro.hack.Hack;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.world.GameType;

import java.util.UUID;

@Hack.Construct(name = "FakePlayer", description = "Spawns in fakeplayer for testing n stuff", category = Hack.Category.MISC)
public class Fakeplayer extends Hack {


    private EntityOtherPlayerMP fakePlayer = null;

    @Override
    public void onEnable() {
        fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("a3e50241-c8e1-4090-aa05-58c37daacee4"), "Astolfo"));
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        fakePlayer.inventory.copyInventory(mc.player.inventory);
        fakePlayer.setHealth(20.0f);
        fakePlayer.setGameType(GameType.SURVIVAL);
        mc.world.spawnEntity(fakePlayer);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (fakePlayer != null) {
            mc.world.removeEntity(fakePlayer);
            mc.world.removeEntityDangerously(fakePlayer);
            fakePlayer = null;
        }
    }

}