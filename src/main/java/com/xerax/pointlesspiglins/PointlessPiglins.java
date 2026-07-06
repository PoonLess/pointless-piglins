package com.xerax.pointlesspiglins;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointlessPiglins implements ModInitializer {
    public static final String MOD_ID = "pointless-piglins";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * Gamerule: when true (default), prevents zombified piglins from spawning naturally
     * with golden spears.
     * <p>
     * Usage: {@code /gamerule pointlessPiglins false} to allow vanilla behavior.
     */
    public static final GameRule<Boolean> DISABLE_GOLDEN_SPEAR =
            GameRuleBuilder.forBoolean(true)
                    .category(GameRuleCategory.MOBS)
                    .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "disableGoldenSpear"));

    @Override
    public void onInitialize() {
        LOGGER.info("Pointless Piglins loaded — your gold farm is safe again.");
    }
}
