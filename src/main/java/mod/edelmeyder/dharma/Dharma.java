package mod.edelmeyder.dharma;

import mod.edelmeyder.dharma.init.ModBlocks;
import mod.edelmeyder.dharma.init.ModItemGroups;
import mod.edelmeyder.dharma.init.ModItems;
import mod.edelmeyder.dharma.init.ModTileEntityTypes;
import mod.edelmeyder.dharma.world.gen.ModOreGen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Dharma.MODID)
@EventBusSubscriber(modid = Dharma.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Dharma {
    public static final String MODID = "dharma";

    public static final Logger LOGGER = LogManager.getLogger();
    public Dharma() {
        LOGGER.debug("Namaste from Dharma");
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);

        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(eventBus);

    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(ModItemGroups.DHARMA);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(ModOreGen::generateOre);
    }
}
