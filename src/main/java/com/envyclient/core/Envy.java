package com.envyclient.core;

import com.boxysystems.jgoogleanalytics.FocusPoint;
import com.boxysystems.jgoogleanalytics.JGoogleAnalyticsTracker;
import com.envyclient.core.impl.managers.*;
import com.envyclient.core.util.Loader;
import com.envyclient.core.util.ReflectionUtils;
import me.ihaq.eventmanager.EventManager;
import me.ihaq.imguruploader.ImgurUploader;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Envy implements Loader {

    private static final Loader[] loaders = new Loader[]{new Info(), new ThirdParty(), new Managers()};

    @Override
    public void enable() {
        Arrays.stream(loaders).forEach(Loader::enable);
    }

    @Override
    public void disable() {
        Arrays.stream(loaders).forEach(Loader::disable);
    }

    public static class Info implements Loader {

        public static final String NAME = "Envy";
        public static final double VERSION = 4.2;

        @Override
        public void enable() {
            Display.setTitle(NAME + " v" + VERSION);
        }
    }

    public static class Colors {
        public static final int MAIN = new Color(33, 170, 47).getRGB();
        public static final int SECONDARY = new Color(23, 23, 23).getRGB();
        public static final int SECONDARY_BRIGHTER = new Color(37, 37, 37).getRGB();
    }

    public static class ThirdParty implements Loader {
        public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
        public static final ImgurUploader IMGUR = new ImgurUploader("1a68a88b9b5d961");

        @Override
        public void enable() {
            // Google analytics
            JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker("EnvyClient", String.valueOf(Info.VERSION), "UA-78987238-4");
            FocusPoint focusPoint = new FocusPoint("Active");
            tracker.trackAsynchronously(focusPoint);
        }

        @Override
        public void disable() {
            EXECUTOR_SERVICE.shutdown();
            IMGUR.shutdown();
        }
    }

    public static class Managers implements Loader {

        public static final EventManager EVENT = new EventManager();
        public static final FriendManager FRIEND = new FriendManager();

        public static final ModuleManager MODULE = new ModuleManager();
        public static final SettingManager SETTING = new SettingManager();
        public static final CommandLoader COMMAND = new CommandLoader();
        public static final ClickGUIManager CLICK_GUI = new ClickGUIManager();
        public static final CustomFileManager FILE = new CustomFileManager();

        @Override
        public void enable() {
            ReflectionUtils.invokeLoader(getClass(), this, true);
        }

        @Override
        public void disable() {
            ReflectionUtils.invokeLoader(getClass(), this, false);
        }
    }

}