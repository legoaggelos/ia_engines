package com.legoaggelos.mixin;

import immersive_aircraft.client.hud.SpeedIndicator;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import immersive_aircraft.client.OverlayRenderer;
import immersive_aircraft.entity.EngineVehicle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import immersive_aircraft.client.hud.StringDrawer;
import net.minecraft.util.FastColor;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Iterator;
import java.util.stream.IntStream;

import static immersive_aircraft.client.hud.Colors.*;

@Mixin(SpeedIndicator.class)
public class MixinSpeedIndicator {
    private Vec3 mSpeed;
    private Vec3 dir = new Vec3(0, 0, 0);
    @Unique
    private static void drawString5(GuiGraphics context, Minecraft client, @Nullable String text, int x, int y, int color, boolean dropShadow) {
        context.drawString(client.font, text, x - client.font.width(text) / 2 - 1, y - client.font.lineHeight / 2 + 1, color, dropShadow);
    }
    @Unique
    private static void drawString9(GuiGraphics context, Minecraft client, @Nullable String text, int x, int y, int color, boolean dropShadow) {
        context.drawString(client.font, text, x - client.font.width(text) - 2, y - client.font.lineHeight + 1, color, dropShadow);
    }
    /**
     * @author legoaggelos + denmor for the pr
     * @reason to make the dial not overflow
     */
    @Overwrite
    public void drawDials(GuiGraphics context, Minecraft client, int baseX, int baseY, int scale, EngineVehicle aircraft) {
        // dial 55x55
        context.fill(baseX - 27 * scale, baseY - 27 * scale, baseX + 27 * scale + 1, baseY + 27 * scale + 1, colorBG);
        Vec3 scale0 = new Vec3(0, 25 * scale, 0);
        Vec3 scale1 = new Vec3(0, 22 * scale, 0);
        Vec3 scale2 = new Vec3(0, 20 * scale, 0);
        Vec3 scale3 = new Vec3(0, 16 * scale, 0);
        for (int i = 0; i < 60; i += 1) {
            float angle = -(float) Math.toRadians(6.0f * i);
            Vec3 sa = scale0.zRot(angle);
            Vec3 sb = i % 10 == 0 ? scale2.zRot(angle) : scale1.zRot(angle);
            var color = colorFG;
            if (angle>=300) {
                color = FastColor.ARGB32.color(131, 0, 0, 1);
            }
            OverlayRenderer.renderLine(context, baseX + (int) sa.x, baseY + (int) sa.y, baseX + (int) sb.x, baseY + (int) sb.y, color);
            if (i % 10 == 0) {
                Vec3 sc = scale3.zRot(angle);
                drawString5(context, client, String.valueOf(i / 10), baseX + (int) sc.x + 2, baseY + (int) sc.y - 1, color, false);
            }
        }
        int iz = Double.valueOf(mSpeed.dot(dir) * 10).intValue();
        // hands
        float angle = -(float) Math.toRadians(Math.min(iz * 0.6f, 330));
        Vec3 h1 = scale1.zRot(angle);
        OverlayRenderer.renderLine(context, baseX, baseY, baseX + (int) h1.x, baseY + (int) h1.y, colorHD1, false, true);
        // border
        context.fill(baseX - 27 * scale, baseY - 27 * scale, baseX + 27 * scale + 1, baseY - 25 * scale, colorFG);
        context.fill(baseX - 27 * scale, baseY - 27 * scale, baseX - 25 * scale, baseY + 27 * scale + 1, colorFG);
        context.fill(baseX - 27 * scale, baseY + 25 * scale + 1, baseX + 27 * scale + 1, baseY + 27 * scale + 1, colorFG);
        context.fill(baseX + 25 * scale + 1, baseY - 27 * scale, baseX + 27 * scale + 1, baseY + 27 * scale + 1, colorFG);
        OverlayRenderer.drawScrew(context, baseX - 22 * scale, baseY - 22 * scale, scale, true, colorFG);
        OverlayRenderer.drawScrew(context, baseX + 22 * scale, baseY - 22 * scale, scale, false, colorFG);
        OverlayRenderer.drawScrew(context, baseX - 22 * scale, baseY + 22 * scale, scale, false, colorFG);
        OverlayRenderer.drawScrew(context, baseX + 22 * scale, baseY + 22 * scale, scale, true, colorFG);
        if (scale > 1)
            drawString9(context, client, "AS", baseX + 20 * scale + 1, baseY + 25 * scale + 1, colorFG, false);
        else {
            {
                // A
                int x = baseX + 13, y = baseY + 26;
                context.fill(x, y - 4, x + 1, y, colorFG);
                context.fill(x + 1, y - 5, x + 2, y - 4, colorFG);
                context.fill(x + 2, y - 4, x + 3, y, colorFG);
                context.fill(x + 1, y - 3, x + 2, y - 2, colorFG);
            }
            {
                // S
                int x = baseX + 16, y = baseY + 26;
                context.fill(x + 1, y - 5, x + 3, y - 4, colorFG);
                context.fill(x, y - 4, x + 1, y - 3, colorFG);
                context.fill(x + 1, y - 3, x + 2, y - 2, colorFG);
                context.fill(x + 2, y - 3, x + 3, y - 1, colorFG);
                context.fill(x, y - 1, x + 2, y, colorFG);
            }
        }
        // out of range lamp
        context.fill(baseX - 3 * scale, baseY - 3 * scale, baseX + 3 * scale + 1, baseY + 3 * scale + 1, colorFG);
        context.fill(baseX - 2 * scale, baseY - 2 * scale, baseX + 2 * scale + 1, baseY + 2 * scale + 1, iz >= 0 && iz < 600 ? colorLt0 : colorLt1);
    }
}
