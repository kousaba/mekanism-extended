package com.github.kousaba.mekanism_extended.registration;

import com.github.kousaba.mekanism_extended.MekanismExtended;
import com.github.kousaba.mekanism_extended.multiblock.TileEntityTransmuterCasing;
import com.github.kousaba.mekanism_extended.multiblock.TileEntityTransmuterGlass;
import com.github.kousaba.mekanism_extended.multiblock.TileEntityTransmuterInternal;
import com.github.kousaba.mekanism_extended.multiblock.TileEntityTransmuterPort;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.generators.common.registries.GeneratorsBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTileEntities {
    public static final TileEntityTypeDeferredRegister TILE_ENTITIES = new TileEntityTypeDeferredRegister(MekanismExtended.MODID);

    public static final TileEntityTypeRegistryObject<TileEntityTransmuterCasing> TRANSMUTER_CASING =
        TILE_ENTITIES.register(ModBlocks.TRANSMUTER_CASING, TileEntityTransmuterCasing::new);

    public static final TileEntityTypeRegistryObject<TileEntityTransmuterPort> TRANSMUTER_PORT =
        TILE_ENTITIES.register(ModBlocks.TRANSMUTER_PORT, TileEntityTransmuterPort::new);

    public static final TileEntityTypeRegistryObject<TileEntityTransmuterGlass> TRANSMUTER_GLASS =
        TILE_ENTITIES.register(ModBlocks.TRANSMUTER_GLASS, TileEntityTransmuterGlass::new);
}
