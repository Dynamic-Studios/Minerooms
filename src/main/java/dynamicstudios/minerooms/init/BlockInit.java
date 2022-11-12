package dynamicstudios.minerooms.init;

import dynamicstudios.minerooms.Minerooms;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    // Blocks Registry
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Minerooms.MODID);

    // Every Block will be created in a similar way
    // The only part that changes is the Properties.of block which simply describes the blocks properties, so if a block has properties of a Material.STONE it'll have the same properties as a Stone block
    // you can also modify these properties using methods like .strength()
    public static final RegistryObject<Block> LEVEL0_CARPET_BLOCK = BLOCKS.register("level0_carpet_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0f)));
}
