package dynamicstudios.minerooms.init;

import dynamicstudios.minerooms.Minerooms;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Minerooms.MODID);

    // Every block has to have its BLOCKITEM equivalent
    public static final RegistryObject<BlockItem> LEVEL0_CARPET_BLOCK_ITEM = ITEMS.register("level0_carpet_block", () -> new BlockItem(BlockInit.LEVEL0_CARPET_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

}
