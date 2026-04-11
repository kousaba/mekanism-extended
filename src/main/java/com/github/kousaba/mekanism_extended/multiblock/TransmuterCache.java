package com.github.kousaba.mekanism_extended.multiblock;

import mekanism.common.lib.multiblock.MultiblockCache;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public class TransmuterCache extends MultiblockCache<TransmuterMultiblockData> {
    @Override
    public void merge(MultiblockCache<TransmuterMultiblockData> mergeCache, RejectContents rejectContents) {
        super.merge(mergeCache, rejectContents);
    }

    @Override
    public void apply(HolderLookup.Provider provider, TransmuterMultiblockData data) {
        super.apply(provider, data);
    }

    @Override
    public void sync(TransmuterMultiblockData data) {
        super.sync(data);
    }

    @Override
    public void load(HolderLookup.Provider provider, CompoundTag nbt) {
        super.load(provider, nbt);
    }

    @Override
    public void save(HolderLookup.Provider provider, CompoundTag nbt) {
        super.save(provider, nbt);
    }
}
