package com.github.kousaba.mekanism_extended.multiblock;

import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.property.Properties;

import java.util.function.Supplier;


public class TransmuterGlassBlock<TILE extends BlockEntity> extends TintedGlassBlock implements IHasTileEntity<TILE> {
    private TileEntityTypeRegistryObject<TILE> tileType;

    public TransmuterGlassBlock(Properties properties, TileEntityTypeRegistryObject<TILE> tileType) {
        super(properties);
        this.tileType = tileType;
    }

    @Override
    public TileEntityTypeRegistryObject<TILE> getTileType() {
        return tileType;
    }
}
