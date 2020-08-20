/*
package mod.edelmeyder.dharma;

import mod.edelmeyder.dharma.init.ModBlocks;
import mod.edelmeyder.dharma.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Dharma.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(
            setup(new Item(new Item.Properties().group(ModItemGroups.DHARMA)), "ember_stone")
        );
    }
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            setup(new Block(Block.Properties.create(Material.ICE)
                    .hardnessAndResistance(3.0F, 3.0F)
                    .sound(SoundType.GLASS)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)), "ember_block")
        );
    }
    @SubscribeEvent
    public static void onRegisterBlockItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            setup(new BlockItem(ModBlocks.EMBER_BLOCK, new Item.Properties().group(ModItemGroups.DHARMA)))
        );
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Dharma.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}


*/
