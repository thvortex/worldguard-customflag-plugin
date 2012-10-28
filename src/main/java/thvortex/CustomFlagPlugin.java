/*
 * WorldGuard Custom Region Flag Example
 *
 * Copyright (C) 2012 Wojciech Stryjewski <thvortex@gmail.com>
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
package thvortex;

import java.net.URL;
import java.net.MalformedURLException;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.CustomFlag;
import com.sk89q.worldguard.protection.flags.RegionGroup;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;

public class CustomFlagPlugin extends JavaPlugin {
    private class URLFlag extends CustomFlag {
        URLFlag() {
            super("url", RegionGroup.ALL);
        }
    
        @Override
        public Object parseInput(WorldGuardPlugin plugin, CommandSender sender, String input) throws InvalidFlagFormat {
            try {
                new URL(input);
            }
            catch (MalformedURLException e) {
                throw new InvalidFlagFormat("Invalid URL: " + e.getMessage());
            }
            return input;
        }
    }

    public void onEnable() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        ((WorldGuardPlugin)plugin).addCustomFlag(new URLFlag(), this);
    }
}
