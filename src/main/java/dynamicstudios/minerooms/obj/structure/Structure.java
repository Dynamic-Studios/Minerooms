package dynamicstudios.minerooms.obj.structure;

import net.minecraft.core.BlockPos;

public class Structure {
    // Structure generates from lowest x, z to highest x, z
    public BlockPos structureStart;

    public Structure(BlockPos structureStarts) {
        this.structureStart = structureStarts;
    }
    public Structure(double x, double y, double z) {
        this.structureStart = new BlockPos(x, y, z);
    }

    public BlockPos getStructureStart() {
        return structureStart;
    }
}
