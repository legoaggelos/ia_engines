package com.legoaggelos.fabric;

import com.legoaggelos.EngineMod;

import immersive_aircraft.fabric.CommonFabric;
import net.fabricmc.api.ModInitializer;

public class EngineModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new CommonFabric();
        EngineMod.init();
    }
}
