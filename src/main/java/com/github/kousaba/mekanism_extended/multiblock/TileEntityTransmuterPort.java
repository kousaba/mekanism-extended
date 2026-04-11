package com.github.kousaba.mekanism_extended.multiblock;

import com.github.kousaba.mekanism_extended.registration.ModBlocks;
import mekanism.api.IContentsListener;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class TileEntityTransmuterPort extends TileEntityTransmuterCasing {
    public TileEntityTransmuterPort(BlockPos pos, BlockState state) {
        super(ModBlocks.TRANSMUTER_PORT, pos, state);
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener) {
        return side -> getMultiblock().isFormed() ?
            List.of(getMultiblock().inputTank, getMultiblock().outputTank) : Collections.emptyList();
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        return side -> getMultiblock().isFormed() ?
            List.of(getMultiblock().energyContainer) : Collections.emptyList();
    }
}
