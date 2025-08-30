package com.legoaggelos.mixin;

import immersive_aircraft.entity.InventoryVehicleEntity;
import immersive_aircraft.entity.inventory.VehicleInventoryDescription;
import immersive_aircraft.item.upgrade.VehicleUpgradeRegistry;
import immersive_aircraft.screen.slot.UpgradeSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UpgradeSlot.class)
public class MixinUpgradeSlot {
    @Final
    private InventoryVehicleEntity vehicle;
    /**
     * @author legoaggelos
     * @reason disallow the player to use multiple engines/turbos/superchargers in the same plane
     */
    @Overwrite
    public boolean mayPlace(ItemStack stack) {
        if (stack.getItem().toString().toLowerCase().contains("engine")) {
            return  vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(item -> item.getItem().toString().toLowerCase().contains("engine"))
                    && VehicleUpgradeRegistry.INSTANCE.hasUpgrade(stack.getItem())
                    && vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(s -> s.getItem() == stack.getItem());
        }
        if (stack.getItem().toString().toLowerCase().contains("turbo")) {
            return  vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(item -> item.getItem().toString().toLowerCase().contains("turbo"))
                    && VehicleUpgradeRegistry.INSTANCE.hasUpgrade(stack.getItem())
                    && vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(s -> s.getItem() == stack.getItem());
        }
        if (stack.getItem().toString().toLowerCase().contains("super")) {
            return  vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(item -> item.getItem().toString().toLowerCase().contains("super"))
                    && VehicleUpgradeRegistry.INSTANCE.hasUpgrade(stack.getItem())
                    && vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(s -> s.getItem() == stack.getItem());
        }
        return VehicleUpgradeRegistry.INSTANCE.hasUpgrade(stack.getItem())
                && vehicle.getSlots(VehicleInventoryDescription.UPGRADE).stream().noneMatch(s -> s.getItem() == stack.getItem());
    }
}