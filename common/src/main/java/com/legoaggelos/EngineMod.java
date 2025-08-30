package com.legoaggelos;


import immersive_aircraft.Items;
import immersive_aircraft.cobalt.registration.Registration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import static immersive_aircraft.Items.baseProps;

public class EngineMod{
    public static final String MOD_ID = "more_engines";
    public static Supplier<Item> SEMI_ECO_ENGINE;
    public static Supplier<Item> REINFORCED_ENGINE;
    public static Supplier<Item> GOLD_ENGINE;
    public static Supplier<Item> DIAMOND_ENGINE;
    public static Supplier<Item> JET_ENGINE;
    public static Supplier<Item> SUPERCHARGER;
    public static Supplier<Item> TURBO;
    public static Supplier<Item> TWIN_TURBO;
    public static Supplier<Item> QUAD_TURBO;
    static Supplier<Item> register(String name, Supplier<Item> item) {
        Supplier<Item> register = Registration.register(BuiltInRegistries.ITEM, EngineMod.locate(name), item);
        Items.items.add(register);
        return register;
    }

    public static ResourceLocation locate(String name) {
        return new ResourceLocation(EngineMod.MOD_ID, name);
    }
    
    public static void init() {
        SEMI_ECO_ENGINE = register("semi_eco_engine", () -> new Item(baseProps().stacksTo(8)));
        REINFORCED_ENGINE = register("reinforced_engine", () -> new Item(baseProps().stacksTo(8)));
        GOLD_ENGINE = register("gold_engine", () -> new Item(baseProps().stacksTo(8)));
        DIAMOND_ENGINE = register("diamond_engine", () -> new Item(baseProps().stacksTo(8)));
        JET_ENGINE = register("jet_engine", () -> new Item(baseProps().stacksTo(8)));
        SUPERCHARGER = register("supercharger", () -> new Item(baseProps().stacksTo(8)));
        TURBO = register("turbo", () -> new Item(baseProps().stacksTo(8)));
        TWIN_TURBO = register("twin_turbo", () -> new Item(baseProps().stacksTo(8)));
        QUAD_TURBO = register("quad_turbo", () -> new Item(baseProps().stacksTo(8)));
    }

}
