package mod.edelmeyder.dharma.init;

import mod.edelmeyder.dharma.Dharma;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class ModItemGroups {
    public static final ItemGroup DHARMA = new ModItemGroup(Dharma.MODID, () -> new ItemStack(ModItems.EMBER_STONE.get()));


    public static class ModItemGroup extends ItemGroup {
        private final Supplier<ItemStack> iconSupplier;
        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() {
            return iconSupplier.get();
        }
    }
}

