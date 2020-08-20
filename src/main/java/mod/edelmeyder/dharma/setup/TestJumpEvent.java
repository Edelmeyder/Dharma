/*
package mod.edelmeyder.dharma.setup;

import mod.edelmeyder.dharma.Dharma;
import mod.edelmeyder.dharma.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Dharma.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class TestJumpEvent {
    @SubscribeEvent
    public static void TestJumpEvent(LivingEvent.LivingJumpEvent event){
        LivingEntity entity = event.getEntityLiving();
        World world = entity.world;
        if (entity.getHeldItemMainhand().getItem() == ModBlocks.EMBER_CLAY.get().asItem()){
            Minecraft.getInstance().player.sendChatMessage("Hello");
            for (int y = 0; y < 156; y++){
                for (int x = entity.getPosition().getX() - 250; x < entity.getPosition().getX() + 250; x++){
                    for (int z = entity.getPosition().getZ() - 250; z < entity.getPosition().getZ() + 250; z++){
                        if (world.getBlockState(new BlockPos(x,y,z)) == ModBlocks.EMBER_CLAY.get().getDefaultState()) {
                            Minecraft.getInstance().player.sendChatMessage(x + " " + y + " " + z);
                        }
                    }
                }
            }
            Minecraft.getInstance().player.sendChatMessage("Bye");
        }
    }
}

*/
