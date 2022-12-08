package dynamicstudios.minerooms.proceduralg;



import dynamicstudios.minerooms.init.BlockInit;
import dynamicstudios.minerooms.obj.Survivor;
import dynamicstudios.minerooms.obj.chunk.GeneratedChunk;
import dynamicstudios.minerooms.obj.structure.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import java.util.ArrayList;
import java.util.Random;

public class Level0ProceduralG extends ProceduralGeneration {

    private final Random rand = new Random();
    Block floor = BlockInit.LEVEL0_CARPET_BLOCK.get();
    Block wall = BlockInit.LEVEL0_WALLPAPER_BLOCK.get();
    double[][][] structures =
            {           // Straight corridor
                    {
                            {0, 0},
                            {0, 1},
                            {0, 2},
                            {0, 3},
                            {3, 0},
                            {3, 1},
                            {3, 2},
                            {3, 3},
                    },
                    {   // Left turn
                            {0, 0},
                            {0, 3},
                            {1, 3},
                            {2, 3},
                            {3, 3},
                            {3, 2},
                            {3, 1},
                            {3, 0},

                    },
                    { // Empty space

                    }
            };



    Level world = this.survivor.getPlayer().getLevel();

    private final ArrayList<BlockPos> generatedStructures = new ArrayList<BlockPos>();
    private final ArrayList<GeneratedChunk> chunks = new ArrayList<GeneratedChunk>();

    GeneratedChunk playerChunkPos;

    public Level0ProceduralG(Survivor survivor) {
        super(survivor);
    }

    private void flooring() {
        for (GeneratedChunk chunk: chunks) {
            for (double x = chunk.getChunkPos().getMinBlockX(); x <= chunk.getChunkPos().getMaxBlockX(); x++) {
                for (double z = chunk.getChunkPos().getMinBlockZ(); z <= chunk.getChunkPos().getMaxBlockZ(); z++) {
                    world.setBlockAndUpdate(new BlockPos(x, 225, z), floor.defaultBlockState());
                }
            }
        }

    }

    private void structures() {
        for (GeneratedChunk chunk: chunks) {
            for (Structure structure: chunk.getStructures()) {

                // If structure already generated
                if (generatedStructures.contains(structure.getStructureStart())) {continue;}

                // Adds structure to the list of generated structures
                generatedStructures.add(structure.getStructureStart());

                // Randomly chooses one of the available structures
                int randint = rand.nextInt(0, structures.length);
                double[][] blockPositioning = structures[randint];

                // Randomizing the random class
                rand.setSeed(System.nanoTime());
                // x, z position of the blocks in the structure
                for (double[] pos: blockPositioning) {
                    // y position of the blocks in the structure
                    for (int y = 0; y < 4; y++) {
                        // if out of the chunk bounds
                        if (structure.structureStart.getX() + pos[0] > chunk.getChunkPos().getMaxBlockX() || structure.structureStart.getZ() + pos[1] > chunk.getChunkPos().getMaxBlockZ()) {continue;}

                        world.setBlockAndUpdate(new BlockPos(structure.structureStart.getX() + pos[0], 226 + y, structure.structureStart.getZ() + pos[1]), wall.defaultBlockState());
                    }
                }
            }
        }
    }

    private void walls() {

    }



    public void generate() {
        playerChunkPos = new GeneratedChunk(this.survivor.getPlayer().chunkPosition());
        chunks.add(playerChunkPos);

        flooring();
        structures();
        walls();
        chunks.clear();
    }
}


