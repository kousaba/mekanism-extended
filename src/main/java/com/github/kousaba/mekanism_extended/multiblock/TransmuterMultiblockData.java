package com.github.kousaba.mekanism_extended.multiblock;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.energy.IEnergyContainer;
import mekanism.common.capabilities.chemical.VariableCapacityChemicalTank;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.energy.VariableCapacityEnergyContainer;
import mekanism.common.inventory.container.sync.dynamic.ContainerSync;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.registries.MekanismItems;
import mekanism.generators.common.registries.GeneratorsItems;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;

public class TransmuterMultiblockData extends MultiblockData {
    // 蓄電
    @ContainerSync
    public final IEnergyContainer energyContainer;

    // 化学物質タンク（Lead -> Uranium）
    @ContainerSync
    public final IChemicalTank inputTank;
    @ContainerSync
    public final IChemicalTank outputTank;

    // 性能パラメータ
    @ContainerSync
    public int coilCount = 0;
    @ContainerSync
    public double successRate = 0.0;

    public TransmuterMultiblockData(TileEntityTransmuterCasing tile) {
        super(tile);

        energyContainers.add(energyContainer = VariableCapacityEnergyContainer.input(
            () -> (long) getVolume() * 1000000L, // 第1引数: LongSupplier
            this                                 // 第2引数: IContentsListener
        ));

        chemicalTanks.add(inputTank = VariableCapacityChemicalTank.input(this,
            () -> (long) getVolume() * 10000L,
            chem -> true,
            ChemicalAttributeValidator.ALWAYS_ALLOW, // これを追加
            this));

        chemicalTanks.add(outputTank = VariableCapacityChemicalTank.output(this,
            () -> (long) getVolume() * 10_000L, chem -> true, this));
    }

    @Override
    public boolean tick(Level world) {
        boolean needsPacket = super.tick(world);

        if (isFormed() && !inputTank.isEmpty() && energyContainer.getEnergy() > 1000) {
            // 変換ロジック
            long processAmount = (long) (10 * (1 + coilCount * 0.5));
            if (inputTank.getStored() >= processAmount && world.random.nextDouble() < successRate) {
                // 成功：鉛を消費してウランを出す
                inputTank.shrinkStack(processAmount, Action.EXECUTE);
                outputTank.insert(MekanismChemicals.URANIUM_OXIDE.asStack(processAmount), Action.EXECUTE, AutomationType.INTERNAL);
                energyContainer.extract(1000 * processAmount, Action.EXECUTE, AutomationType.INTERNAL);
            }
        }
        return needsPacket;
    }

}
