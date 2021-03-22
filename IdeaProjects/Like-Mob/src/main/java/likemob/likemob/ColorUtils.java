package likemob.likemob;

import org.bukkit.ChatColor;

public class ColorUtils {
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}