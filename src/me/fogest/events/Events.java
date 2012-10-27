/*
 * MCTrade
 * Copyright (C) 2012 Fogest <http://fogest.net16.net> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package me.fogest.events;

import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.fogest.events.commands.KoTH;
import me.fogest.events.listeners.*;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Events extends JavaPlugin {
	private Events plugin;
	private MessageHandler msg;
	
    public Economy econ = null;
    public Permission perms = null;
    public Chat chat = null;
    
	public Updater updater;
	
	public boolean update = false;
    
	public Events() {
		plugin = this;
		msg = new MessageHandler("[Events]", this);
	}
	
	@Override
	public void onEnable() {
		reloadSettings();
		// Registering the listeners (with the new 1.3 API)
		getServer().getPluginManager().registerEvents(new Chat(this), this);

		// Registering the command executors
		TimedEvents time = new TimedEvents(this, msg);
		getCommand("kingofthehill").setExecutor(new KoTH(this, msg, time));
		
		econ = getProvider(Economy.class);
		perms = getProvider(Permission.class);
		chat = getProvider(Chat.class);
		
		
		time.scheduleKoTH(20);
		
		if(update == true)
			updater = new Updater(this, "mctrade", this.getFile(), Updater.UpdateType.DEFAULT, true);
		
	}
	public void onReload() {
		reloadSettings();
	}
	public <T> T getProvider(final Class<T> c) {
        final org.bukkit.plugin.RegisteredServiceProvider<T> provider
            = Bukkit.getServicesManager().getRegistration(c);
        if (provider != null)
            return provider.getProvider();
        return null;
    }
	public void reloadSettings() {
		reloadConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();

        update = getConfig().getBoolean("Options.AutoUpdate");
	}
	public void onDisable() {
		
	}
    public Events getPlugin(){
        return plugin;
    }
}
