package com.github.kousaba.mekanism_extended.multiblock;


import com.github.kousaba.mekanism_extended.registration.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityTransmuterGlass extends TileEntityTransmuterCasing {
    public TileEntityTransmuterGlass(BlockPos pos, BlockState state) {
        super(ModBlocks.TRANSMUTER_GLASS, pos, state);
    }
}
