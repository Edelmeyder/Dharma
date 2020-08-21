package mod.edelmeyder.dharma.init;

import mod.edelmeyder.dharma.Dharma;
import mod.edelmeyder.dharma.objects.biomes.ModBiome;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Dharma.MODID);

    public static final RegistryObject<Biome> VALAR = BIOMES.register("valar", () -> new ModBiome(new Biome.Builder()
            .category(Biome.Category.FOREST)
            .scale(1.2f)
            .precipitation(Biome.RainType.RAIN)
            .downfall(0.2F)
            .temperature(0.5f)
            .waterColor(13878480)
            .waterFogColor(12570288)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState()))
            .depth(0.115f)
            .parent(null)));

    public static void RegisterBiomes(){
        for (RegistryObject<Biome> biome: BIOMES.getEntries()) {
            RegisterBiome(biome.get(), Type.FOREST);
        }
    }
    private static void RegisterBiome(Biome biome, Type... types){
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }
}
