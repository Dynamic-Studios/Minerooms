package dynamicstudios.minerooms;

import com.mojang.logging.LogUtils;
import dynamicstudios.minerooms.init.BlockInit;
import dynamicstudios.minerooms.init.ItemInit;
import dynamicstudios.minerooms.obj.Survivor;
import dynamicstudios.minerooms.proceduralg.Level0ProceduralG;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.ArrayList;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Minerooms.MODID)
public class Minerooms {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "minerooms";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private ArrayList<Survivor> survivors = new ArrayList<Survivor>();

    public Minerooms() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.addListener(this::serverTick);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Actually registering the List of blocks and items we've prepered in BlockInit and ItemInit
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Survivor joined = new Survivor(event.getEntity());

        joined.setLevel(0, new Level0ProceduralG(joined));
        survivors.add(joined);


        Vec3 playerPos = event.getEntity().position();
        event.getEntity().teleportTo(playerPos.x(),226,playerPos.z());
    }

    public void serverTick(TickEvent.ServerTickEvent event) {
        for (Survivor survivor: survivors) {
            survivor.getPG().generate();
        }

    }
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        event.getEntity().teleportTo(event.getEntity().getX(), 226, event.getEntity().getZ());
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }




    }
}
