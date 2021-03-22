package likemob.likemob;


import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    public ItemStack stack;

    public ItemBuilder(Material mat) {
        stack = new ItemStack(mat);
    }

    public ItemMeta getItemMeta() {
        return stack.getItemMeta();
    }

    public ItemBuilder setItemMeta(ItemMeta meta) {
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setStack(ItemStack stack) {
        this.stack = stack;
        return this;
    }

    public ItemBuilder setLoresFromBeginning(String... lores) {
        ItemMeta im = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        for (String loreText : lores)
            lore.add(ColorUtils.color(loreText));
        if (im != null && im.getLore() != null)
            lore.addAll(im.getLore());

        im.setLore(lore);
        stack.setItemMeta(im);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
        meta.setColor(color);
        stack.setItemMeta(meta);
        return this;
    }


    public ItemBuilder setFlag(ItemFlag itemFlag) {
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(itemFlag);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder storedEnchant(Enchantment enchantment, int level) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder clearLores() {
        ItemMeta meta = getItemMeta();
        meta.setLore(new ArrayList<>());
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setGlow(boolean glow) {
        if (glow) {
            addEnchant(Enchantment.MENDING, 1);
            addItemFlag(ItemFlag.HIDE_ENCHANTS);
        } else {
            ItemMeta meta = getItemMeta();
            for (Enchantment enchantment : meta.getEnchants().keySet()) {
                meta.removeEnchant(enchantment);
            }
        }
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta meta = stack.getItemMeta();
        meta.setUnbreakable(unbreakable);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setBannerColor(DyeColor color) {
        BannerMeta meta = (BannerMeta) stack.getItemMeta();
        meta.setBaseColor(color);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        stack.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplayName(String displayname) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(ColorUtils.color(displayname));
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder setItemStack(ItemStack stack) {
        this.stack = stack;
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(lore);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        List<String> loreList = new ArrayList<>();
        if (getItemMeta().getLore() != null) {
            loreList = getItemMeta().getLore();
        }
        for (String nickname : lore)
            loreList.add(ColorUtils.color(nickname));
        ItemMeta meta = getItemMeta();
        meta.setLore(loreList);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta meta = getItemMeta();
        meta.addEnchant(enchantment, level, true);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder unsafeEnchant(Enchantment enchantment, int level) {
        build().addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(flag);
        setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return stack;
    }
}