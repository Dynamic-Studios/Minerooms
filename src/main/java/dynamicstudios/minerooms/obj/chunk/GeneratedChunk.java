package dynamicstudios.minerooms.obj.chunk;

import dynamicstudios.minerooms.obj.structure.Structure;
import net.minecraft.world.level.ChunkPos;

import java.util.ArrayList;

public class GeneratedChunk {
    private final ChunkPos chunkPos;
    private final ArrayList<Structure> structures = new ArrayList<Structure>();


    // TODO: Make abstract and create classes for every level type chunks
    public GeneratedChunk(ChunkPos chunkPos) {
        this.chunkPos = chunkPos;

        for (double x = chunkPos.getMinBlockX(); x <= chunkPos.getMaxBlockX(); x += 3)
            for (double z = chunkPos.getMinBlockZ(); z <= chunkPos.getMaxBlockZ(); z += 3) {
                structures.add(new Structure(x, 225, z));

            }


    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public ChunkPos getChunkPos() {
        return chunkPos;
    }
}
