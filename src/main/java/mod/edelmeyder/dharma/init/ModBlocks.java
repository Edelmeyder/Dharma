package mod.edelmeyder.dharma.init;

import mod.edelmeyder.dharma.Dharma;
import mod.edelmeyder.dharma.objects.blocks.CustomBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Dharma.MODID);

    public static final RegistryObject<Block> EMBER_BLOCK = BLOCKS.register("ember_block", () -> new Block(Block.Properties.create(Material.ROCK)
            .hardnessAndResistance(3.0F, 3.0F)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(1)
            .sound(SoundType.LANTERN)
            .lightValue(15)));
    public static final RegistryObject<Block> EMBER_CLAY = BLOCKS.register("ember_clay", () -> new Block(Block.Properties.create(Material.CLAY)
            .hardnessAndResistance(1.0F, 0.2F)
            .harvestTool(ToolType.SHOVEL)
            .harvestLevel(0)
            .sound(SoundType.WET_GRASS)
            .lightValue(3)));
    public static final RegistryObject<Block> CUSTOM_BLOCK = BLOCKS.register("custom_block", () -> new CustomBlock(Block.Properties.create(Material.CORAL)
            .hardnessAndResistance(2.0f,0.3f)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.CORAL)
            .lightValue(5)));
}
