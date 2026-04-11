package com.github.kousaba.mekanism_extended.multiblock;

import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class TransmuterBlock<TILE extends BlockEntity> extends Block implements IHasTileEntity<TILE> {
    private final TileEntityTypeRegistryObject<TILE> tileType;

    public TransmuterBlock(Properties properties, TileEntityTypeRegistryObject<TILE> tileType) {
        super(properties);
        this.tileType = tileType;
    }

    @Override
    public TileEntityTypeRegistryObject<TILE> getTileType() {
        return tileType;
    }
}
