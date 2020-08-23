/*
 * Copyright (c) 2015 Jerrell Fang
 *
 * This project is Open Source and distributed under The MIT License (MIT)
 * (http://opensource.org/licenses/MIT)
 *
 * You should have received a copy of the The MIT License along with
 * this project.   If not, see <http://opensource.org/licenses/MIT>.
 */
package com.meowj.langutils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.meowj.langutils.lang.LanguageRegistry;
import com.meowj.langutils.lang.convert.EnumLang;

/**
 * Created by Meow J on 6/20/2015.
 *
 * @author Meow J
 */
public class LangUtils extends JavaPlugin {

    public static LangUtils plugin;
    public FileConfiguration config;

    @Override
    public void onEnable() {
        plugin = this;
        this.config = getConfig();


        config.addDefault("FallbackLanguage", "en_us");
        List<String> defaultLang = new ArrayList<>();
        defaultLang.add("en_us");
        defaultLang.add("ru_ru");
        config.addDefault("LoadLanguage", defaultLang);
        config.options().copyDefaults(true);
        saveConfig();

        // Init default lang
        final long startTime = System.currentTimeMillis();
        EnumLang.init();
        info("Language Utils has been enabled." + "(" + (System.currentTimeMillis() - startTime) + "ms)");


        LanguageRegistry.INSTANCE = new LanguageRegistry();
    }

    @Override
    public void onDisable() {
        EnumLang.clean();
        info("Language Utils has been disabled.");
    }

    /**
     * Display a info message
     *
     * @param msg message to be sent
     */
    public void info(String msg) {
        getLogger().log(Level.INFO, msg);
    }

    /**
     * Display a warning message
     *
     * @param msg message to be sent
     */
    public void warn(String msg) {
        getLogger().log(Level.WARNING, msg);
    }


}
