package dynamicstudios.minerooms.obj;

import dynamicstudios.minerooms.proceduralg.ProceduralGeneration;
import net.minecraft.world.entity.player.Player;

public class Survivor {
    private int level;
    private final Player player;

    private ProceduralGeneration pg;

    public Survivor(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level, ProceduralGeneration pg) {
        this.pg = pg;
        this.level = level;
    }

    public ProceduralGeneration getPG() {
        return this.pg;
    }

}


