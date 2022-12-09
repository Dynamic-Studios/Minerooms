package dynamicstudios.minerooms.proceduralg;



import dynamicstudios.minerooms.init.BlockInit;
import dynamicstudios.minerooms.obj.Survivor;
import dynamicstudios.minerooms.obj.chunk.GeneratedChunk;
import dynamicstudios.minerooms.obj.structure.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class Level0ProceduralG extends ProceduralGeneration {

    private final Random rand = new Random();
    Block floor = BlockInit.LEVEL0_CARPET_BLOCK.get();
    Block wall = BlockInit.LEVEL0_WALLPAPER_BLOCK.get();

    // Structures generated manually
    double [][] doorway = { // Start from 4y
            {0, 0},
            {1, 0},
            {2, 0},
            {3, 0},
    };

    // Structures generated automatically
    double[][][] structures =
            {
                    { // Straight corridor (down to up)
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
                    { // Straight corridor (left to right)
                            {0, 0},
                            {1, 0},
                            {2, 0},
                            {3, 0},
                            {3, 0},
                            {3, 1},
                            {3, 2},
                            {3, 3}

                    },
                    { // Empty space

                    },



            };



    Level world = this.survivor.getPlayer().getLevel();

    private final ArrayList<GeneratedChunk> chunks = new ArrayList<GeneratedChunk>();
    private final ArrayList<BlockPos> generatedStructures = new ArrayList<BlockPos>();
    private final ArrayList<BlockPos> chunksWithRareStruct = new ArrayList<BlockPos>();
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

                // Randomly chooses one of the available structures
                int randint = rand.nextInt(0, structures.length);
                double[][] blockPositioning = structures[randint];

                // Randomizing the random class
                rand.setSeed(System.nanoTime());

                // Manually generated structures
                // 40% chance of generating doorway
                if (rand.nextInt(0, 100) < 40) {
                    generateStructureInArea(structure, doorway, chunk, 3);return;}

                generateStructureInArea(structure, blockPositioning, chunk, null);

            }


        }
    }

    public boolean generateStructureInArea(Structure area, double[][] structure, GeneratedChunk chunk, @Nullable Integer yStartingPos) {
        if (generatedStructures.contains(area.getStructureStart())) {return false;}

        // x, z position of the blocks in the structure
        for (double[] pos: structure) {
            // y position of the blocks in the structure
            for (int y = 0 + ((yStartingPos == null) ? 0 : yStartingPos); y < 4; y++) {
                // if out of the chunk bounds
                if (area.start.getX() + pos[0] > chunk.getChunkPos().getMaxBlockX() || area.start.getZ() + pos[1] > chunk.getChunkPos().getMaxBlockZ()) {
                    continue;
                }
                world.setBlockAndUpdate(new BlockPos(area.start.getX() + pos[0], 226 + y, area.start.getZ() + pos[1]), wall.defaultBlockState());

                generatedStructures.add(area.getStructureStart());

    }
        }
    return true;
    }


    private void BigRoom(Structure start) {
        if (chunksWithRareStruct.contains(start.getChunk().getChunkPos().getWorldPosition())) {return;}

        BlockPos startingPos = start.getStructureStart();

        for (double x = startingPos.getX(); x < startingPos.getX() + 9; x += 1) {
            for (double z = startingPos.getZ(); z < startingPos.getZ() + 9; z+= 1) {
                for (double y = startingPos.getY(); y < 226 + 5; y+= 1) {
                    world.setBlockAndUpdate(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState());
                }
            }
        }
        chunksWithRareStruct.add(start.getChunk().getChunkPos().getWorldPosition());

    }

    private void rareStructures() {
        for (GeneratedChunk chunk: chunks) {
            // random structure from chunk
            Structure start = chunk.getStructures().get(rand.nextInt(0, chunk.getStructures().size() - 1));
            rand.setSeed(System.nanoTime());

            BigRoom(start);
        }
    }




    public void generate() {
        playerChunkPos = new GeneratedChunk(this.survivor.getPlayer().chunkPosition());
        chunks.add(playerChunkPos);

        flooring();
        rareStructures();
        structures();
        chunks.clear();
    }
}


