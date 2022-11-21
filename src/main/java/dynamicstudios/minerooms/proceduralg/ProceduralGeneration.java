package dynamicstudios.minerooms.proceduralg;

import dynamicstudios.minerooms.obj.Survivor;

public abstract class ProceduralGeneration {

    final int radius = 10;
    Survivor survivor;
    public ProceduralGeneration(Survivor survivor) {
        this.survivor = survivor;
    }

    public abstract void generate();

}

