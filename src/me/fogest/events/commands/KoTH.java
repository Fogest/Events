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

package me.fogest.events.commands;

import me.fogest.events.Events;
import me.fogest.events.MessageHandler;
import me.fogest.events.TimedEvents;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KoTH implements CommandExecutor {
	private Events plugin;
	private MessageHandler m;
	private TimedEvents t;
	public KoTH(Events plugin, MessageHandler m, TimedEvents t) {
		this.plugin = plugin;
		this.m = m;
		this.t = t;
	}

	public boolean onCommand(final CommandSender sender, final Command command,
	final String cmdLabel, final String[] args) {
			if(plugin.perms.has(sender,"events.koth") || plugin.perms.has(sender, "events.*")) {
				m.serverBroadCast("KoTH Works");
				Player p = (Player) sender;
				if(args[0].equalsIgnoreCase("forcestart")) {
					//Forces start of KoTH
					if(checkPerms(sender,"forcestart")) {
						m.serverBroadCast("KoTH is now being force started!");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("forcestop")){
					if(checkPerms(sender,"forcestop")) {
						m.serverBroadCast("The current KoTH has now been forcefully stopped!");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("timed") && args[1].equals("stop")){
					//Disables scheduled KoTH events
					if(checkPerms(sender,"timed") || checkPerms(sender,"timed.stop")) {
						m.serverBroadCast("The scheduler has now been disabled");
					}
					else{
						m.sendPlayerMessage(p);
					}
					
				}
				else if(args[0].equalsIgnoreCase("timed") && args[1].equals("start")){
					//Enables scheduled KoTH events
					if(checkPerms(sender,"timed")  || checkPerms(sender,"timed.start")) {
						m.serverBroadCast("The scheduler has now been enabled");
					}
					else{
						m.sendPlayerMessage(p);
					}
					
				}
				else if(args[0].equalsIgnoreCase("time")){
					//Checks the current time remaining on the King of the Hill
					if(checkPerms(sender,"time")) {
						m.serverBroadCast("The time left for the current KoTH is: ");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("captime")){
					//Displays the time left until KoTH is captured
					if(checkPerms(sender,"captime")) {
						m.serverBroadCast("The time left until KoTH is contested is: ");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("coordinates") || args[0].equalsIgnoreCase("coords")){
					//Coordinates of KoTH
					if(checkPerms(sender,"coords")) {
						m.serverBroadCast("The coordinates of the current KoTH are: ");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("")){
					if(checkPerms(sender,"")) {
						m.serverBroadCast("");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("")){
					if(checkPerms(sender,"")) {
						m.serverBroadCast("");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}
				else if(args[0].equalsIgnoreCase("")){
					if(checkPerms(sender,"")) {
						m.serverBroadCast("");
					}
					else{
						m.sendPlayerMessage(p);
					}
				}

				return true;
			}
			
		return false;
	}
	boolean checkPerms(CommandSender s, String p) {
		if(plugin.perms.has(s,"events.koth." + p)){
			return true;
		}
		else if(plugin.perms.has(s, "events.koth")) {
			return true;
		}
		else if(plugin.perms.has(s, "events.*")) {
			return true;
		}
		
		return false;
	}
}
