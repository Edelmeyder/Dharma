package mod.edelmeyder.dharma.world.gen;

import mod.edelmeyder.dharma.Dharma;
import mod.edelmeyder.dharma.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen {
    public static void generateOre() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("CLAY", "clay", new BlockMatcher(Blocks.CLAY)), ModBlocks.EMBER_CLAY.get().getDefaultState(), 6)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(500, 50, 0, 70))));
            Dharma.LOGGER.debug(biome.getDisplayName().toString() + "added ember clay");
        }
    }
}
