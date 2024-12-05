package lol.nils.nocreakings.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public class MixinServerWorld {

    @Unique
    private static final Logger LOGGER = LogManager.getLogger();

    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    private void preventCreakingSpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType() == EntityType.CREAKING) {
            cir.setReturnValue(false);
            LOGGER.debug("[NoCreakings] Cancelled creaking spawn");
        }
    }
}
