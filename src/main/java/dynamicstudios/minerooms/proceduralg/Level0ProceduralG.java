package dynamicstudios.minerooms.proceduralg;



import dynamicstudios.minerooms.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class Level0ProceduralG {


    public static void generate(Player p){
            flooring(p);
        }
        private static void flooring(Player p) {
            Block floor = BlockInit.LEVEL0_CARPET_BLOCK.get();

            Level world = p.getLevel();
            Vec3 blockPos = new Vec3(p.getX(), 225, p.getZ());

            world.setBlockAndUpdate(new BlockPos(blockPos), floor.defaultBlockState());
        }
}

