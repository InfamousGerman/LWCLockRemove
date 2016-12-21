

import com.griefcraft.listeners.LWCBlockListener;
import com.griefcraft.listeners.LWCEntityListener;
import com.griefcraft.listeners.LWCPlayerListener;
import com.griefcraft.listeners.LWCServerListener;
import com.griefcraft.lwc.LWC;
import com.griefcraft.model.Protection;
import com.griefcraft.scripting.event.LWCCommandEvent;
import com.griefcraft.sql.Database;
import com.griefcraft.util.StringUtil;
import com.griefcraft.util.Updater;
import com.griefcraft.util.locale.LWCResourceBundle;
import com.griefcraft.util.locale.LocaleClassLoader;
import com.griefcraft.util.locale.UTF8Control;
import net.minegrid.obelisk.api.Info;
import net.minegrid.obelisk.api.JavaModule;
import net.minegrid.obelisk.api.command.OCmd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.jar.JarFile;



/**
 * Created by Graham on 10/24/2016.
 */

@Info(name = "LWCRemove", version = "1.0", color = ChatColor.AQUA, authors = "InfamousGerman", info = "This is a plugin used by moderators to remove locks within a cubic region", displayName = "LWCLockRemove")
public class LWCRemove extends JavaModule {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @OCmd(cmd = "lwcradiusremove %i",
            info = "removes locks in a cubic region with a given radius",
            perm = "mod")
    void command(Player p, String []args) {
        int radius  = Integer.parseInt(args[1]);
        Location center = p.getLocation().clone();
        Location first = new Location(p.getWorld(), center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        int firstX = (int)first.getX();
        int firstY = (int)first.getY();
        int firstZ = (int)first.getZ();
        Location second = new Location(p.getWorld(), center.getX() - radius, center.getY(), center.getZ() - radius);
        int secondX = (int)second.getX();
        int secondY = (int)second.getY();
        int secondZ = (int)second.getZ();
        LWC lwc = com.griefcraft.lwc.LWC.getInstance();
        final List<Protection> protections = lwc.getPhysicalDatabase().loadProtections(first.getWorld().getName(), firstX, secondX, firstY, secondY, firstZ, secondZ);
        for (Protection protection : protections) {
            protection.remove();
        }
    }
}
