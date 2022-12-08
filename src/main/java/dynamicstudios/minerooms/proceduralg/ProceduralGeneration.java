package dynamicstudios.minerooms.proceduralg;

import dynamicstudios.minerooms.obj.Survivor;

public abstract class ProceduralGeneration {
    Survivor survivor;
    public ProceduralGeneration(Survivor survivor) {
        this.survivor = survivor;
    }

    public abstract void generate();

}

