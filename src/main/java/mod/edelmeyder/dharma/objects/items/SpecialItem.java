package mod.edelmeyder.dharma.objects.items;

import mod.edelmeyder.dharma.init.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class SpecialItem extends Item {
    public SpecialItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        context.getWorld().setBlockState(context.getPos(), ModBlocks.EMBER_BLOCK.get().getDefaultState());
        return super.onItemUse(context);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 800;
    }
}
