package com.envyclient.core.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StringUtils;

public class FontUtils {

    private static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

    private FontUtils() {
    }

    public static int getStringWidth(String text) {
        return fontRenderer.getStringWidth(StringUtils.stripControlCodes(text));
    }

    public static int getFontHeight() {
        return fontRenderer.FONT_HEIGHT;
    }

    public static void drawString(String text, double x, double y, int color) {
        fontRenderer.drawString(text, (int) x, (int) y, color);
    }

    public static void drawStringWithShadow(String text, double x, double y, int color) {
        fontRenderer.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public static void drawCenteredString(String text, double x, double y, int color) {
        drawString(text, x - fontRenderer.getStringWidth(text) / 2.0D, y, color);
    }

    public static void drawCenteredStringWithShadow(String text, double x, double y, int color) {
        drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2.0D, y, color);
    }

    public static void drawTotalCenteredString(String text, double x, double y, int color) {
        drawString(text, x - fontRenderer.getStringWidth(text) / 2.0D, y - getFontHeight() / 2.0D, color);
    }

    public static void drawTotalCenteredStringWithShadow(String text, double x, double y, int color) {
        drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2.0D, y - getFontHeight() / 2F, color);
    }
}