package mod.edelmeyder.dharma.init;

import mod.edelmeyder.dharma.Dharma;
import mod.edelmeyder.dharma.objects.items.SpecialItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Dharma.MODID);

    public static final RegistryObject<Item> EMBER_STONE = ITEMS.register("ember_stone", () -> new Item(new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_CANDY = ITEMS.register("ember_candy", () -> new Item(new Item.Properties().group(ModItemGroups.DHARMA).food(new Food.Builder().hunger(4).setAlwaysEdible().effect(new EffectInstance(Effects.GLOWING, 9600, 1), 1.0f).build())));
    public static final RegistryObject<Item> EMBER_SWORD = ITEMS.register("ember_sword", () -> new SwordItem(ModItemTier.EMBER, 1, 3.0F, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_AXE = ITEMS.register("ember_axe", () -> new AxeItem(ModItemTier.EMBER, 0, 1.6F, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_PICKAXE = ITEMS.register("ember_pickaxe", () -> new PickaxeItem(ModItemTier.EMBER, 0, 1.3F, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_SHOVEL = ITEMS.register("ember_shovel", () -> new ShovelItem(ModItemTier.EMBER, 0, 1.3F, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_HOE = ITEMS.register("ember_hoe", () -> new HoeItem(ModItemTier.EMBER, 1.3F, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_RING = ITEMS.register("ember_ring", () -> new SpecialItem(new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_COATED_IRON_PLATE = ITEMS.register("ember_coated_iron_plate", () -> new Item(new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_COATED_HELMET = ITEMS.register("ember_coated_helmet", () -> new ArmorItem(ModArmorMaterial.EMBER_COATED_IRON, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_COATED_CHESTPLATE = ITEMS.register("ember_coated_chestplate", () -> new ArmorItem(ModArmorMaterial.EMBER_COATED_IRON, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_COATED_LEGGINGS = ITEMS.register("ember_coated_leggings", () -> new ArmorItem(ModArmorMaterial.EMBER_COATED_IRON, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.DHARMA)));
    public static final RegistryObject<Item> EMBER_COATED_BOOTS = ITEMS.register("ember_coated_boots", () -> new ArmorItem(ModArmorMaterial.EMBER_COATED_IRON, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.DHARMA)));

    public enum ModArmorMaterial implements IArmorMaterial{
        EMBER_COATED_IRON(Dharma.MODID + ":ember_coated_iron", 16, new int[]{3, 5, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.5f, () -> {
            return Ingredient.fromItems(ModItems.EMBER_COATED_IRON_PLATE.get());
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;

        private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier) {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountsIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = equipSoundIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return this.damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @Override @OnlyIn(Dist.CLIENT)
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }
    }
    public enum ModItemTier implements IItemTier {
        EMBER(1, 400, 4.5F, 0.8F, 15, () -> {
            return Ingredient.fromItems(ModItems.EMBER_STONE.get());
        });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }
}
