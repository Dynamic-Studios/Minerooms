package dynamicstudios.minerooms.proceduralg;



import dynamicstudios.minerooms.init.BlockInit;
import dynamicstudios.minerooms.obj.Survivor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class Level0ProceduralG extends ProceduralGeneration {

    public Level0ProceduralG(Survivor survivor) {
        super(survivor);
    }

    private void flooring() {
        Block floor = BlockInit.LEVEL0_CARPET_BLOCK.get();

        Level world = this.survivor.getPlayer().getLevel();
        ChunkPos chunkPos = this.survivor.getPlayer().chunkPosition();

        for (double x = chunkPos.getMinBlockX(); x <= chunkPos.getMaxBlockX(); x++) {
            for (double z = chunkPos.getMinBlockZ(); z <= chunkPos.getMaxBlockZ() ; z++) {
                world.setBlockAndUpdate(new BlockPos(x, 225, z), floor.defaultBlockState());
            }
        }
    }

    private void structures() {
        Level world = this.survivor.getPlayer().getLevel();
        ChunkPos chunkPos = this.survivor.getPlayer().chunkPosition();

        for (double x = chunkPos.getMinBlockX(); x <= chunkPos.getMaxBlockX(); x += 3) {
            for (double z = chunkPos.getMinBlockZ(); z <= chunkPos.getMaxBlockZ(); z += 3) {
                world.setBlockAndUpdate(new BlockPos(x, 226, z), Blocks.STONE.defaultBlockState());
            }
        }

    }



    public void generate() {
        flooring();
        structures();

    }
}


