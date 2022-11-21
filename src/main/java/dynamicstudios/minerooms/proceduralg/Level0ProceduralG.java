package dynamicstudios.minerooms.proceduralg;



import dynamicstudios.minerooms.init.BlockInit;
import dynamicstudios.minerooms.obj.Survivor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class Level0ProceduralG extends ProceduralGeneration {

    public Level0ProceduralG(Survivor survivor) {
        super(survivor);
    }

    private void flooring() {
        Block floor = BlockInit.LEVEL0_CARPET_BLOCK.get();

        Level world = this.survivor.getPlayer().getLevel();
        Vec3 pos = this.survivor.getPlayer().position();

        for (double x = pos.x; x <= pos.x + this.radius; x++) {
            for (double z = pos.z; z <= pos.z + this.radius; z++) {
                world.setBlockAndUpdate(new BlockPos(x, 226, z), floor.defaultBlockState());
            }
        }
        }


    public void generate() {
        flooring();
    }
}


