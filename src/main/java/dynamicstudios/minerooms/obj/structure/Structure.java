package dynamicstudios.minerooms.obj.structure;

import dynamicstudios.minerooms.obj.chunk.GeneratedChunk;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class Structure {
    // Structure generates from lowest x, z to highest x, z
    public BlockPos start;

    private GeneratedChunk chunk;
    public Structure onNorth;
    public Structure onSouth;
    public Structure onWest;
    public Structure onEast;



    public Structure(BlockPos structureStarts, @Nullable GeneratedChunk chunk) {
        this.start = structureStarts;
        this.chunk = chunk;

        onNorth = new Structure(this.start.getX(), this.start.getY(), this.start.getZ() + 3, null);
        onSouth = new Structure(this.start.getX(), this.start.getY(), this.start.getZ() - 3, null);
        onWest = new Structure(this.start.getX() - 3, this.start.getY(), this.start.getZ(), null);
        onEast = new Structure(this.start.getX() + 3, this.start.getY(), this.start.getZ(), null);
    }

    public Structure(double x, double y, double z, @Nullable GeneratedChunk chunk) {
        this.start = new BlockPos(x, y, z);
        this.chunk = chunk;
    }

    public GeneratedChunk getChunk() {
        return chunk;
    }

    public BlockPos getStructureStart() {

        return start;
    }

}
