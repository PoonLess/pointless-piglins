package com.spoonless.pointlesspiglins.mixin;

import com.spoonless.pointlesspiglins.PointlessPiglins;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.item.Items;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin into ZombifiedPiglin to strip golden spears on natural spawn.
 *
 * We hook {@code populateDefaultEquipmentSlots} at RETURN — after vanilla equipment
 * logic has already run. If the zombified piglin spawned with a golden spear
 * in hand, we remove it. This prevents chain-kills in gold farms while
 * preserving the normal weapon behavior (golden sword).
 */
@Mixin(ZombifiedPiglin.class)
public abstract class ZombifiedPiglinEntityMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots",
            at = @At("RETURN")
    )
    private void pointlessPiglins$afterPopulateEquipment(
            RandomSource random,
            DifficultyInstance difficulty,
            CallbackInfo ci
    ) {
        ZombifiedPiglin zombie = (ZombifiedPiglin) (Object) this;

        // Game rules are server-side only in 26.1.2
        if (!(zombie.level() instanceof ServerLevel serverLevel)) return;

        // Respect the gamerule
        if (!serverLevel.getGameRules().get(PointlessPiglins.DISABLE_GOLDEN_SPEAR)) return;

        // Strip golden spear from main hand
        if (zombie.getMainHandItem().is(Items.GOLDEN_SPEAR)) {
            zombie.setItemSlot(
                    net.minecraft.world.entity.EquipmentSlot.MAINHAND,
                    net.minecraft.world.item.ItemStack.EMPTY
            );
        }
        // Also check off hand (just in case)
        if (zombie.getOffhandItem().is(Items.GOLDEN_SPEAR)) {
            zombie.setItemSlot(
                    net.minecraft.world.entity.EquipmentSlot.OFFHAND,
                    net.minecraft.world.item.ItemStack.EMPTY
            );
        }
    }
}
