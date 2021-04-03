package likemob.likemob;


import com.jayway.jsonpath.JsonPath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.*;


public final class LikeMob extends JavaPlugin {

    public static List<ItemStack> helmets = Arrays.asList(
            new ItemBuilder(Material.GOLDEN_HELMET).build(),
            new ItemBuilder(Material.IRON_HELMET).build(),
            new ItemBuilder(Material.CHAINMAIL_HELMET).build(),
            new ItemBuilder(Material.LEATHER_HELMET).build()
    );
    public static List<ItemStack> chestplate = Arrays.asList(
            new ItemBuilder(Material.GOLDEN_CHESTPLATE).build(),
            new ItemBuilder(Material.IRON_CHESTPLATE).build(),
            new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).build(),
            new ItemBuilder(Material.LEATHER_CHESTPLATE).build()
    );
    public static List<ItemStack> leggings = Arrays.asList(
            new ItemBuilder(Material.GOLDEN_LEGGINGS).build(),
            new ItemBuilder(Material.IRON_LEGGINGS).build(),
            new ItemBuilder(Material.CHAINMAIL_LEGGINGS).build(),
            new ItemBuilder(Material.LEATHER_LEGGINGS).build()
    );
    public static List<ItemStack> boots = Arrays.asList(
            new ItemBuilder(Material.GOLDEN_BOOTS).build(),
            new ItemBuilder(Material.IRON_BOOTS).build(),
            new ItemBuilder(Material.CHAINMAIL_BOOTS).build(),
            new ItemBuilder(Material.LEATHER_BOOTS).build()
    );
    
    public Integer getLikes() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(getDataFolder() + "\\data.json"));
            JSONObject jsonObject = (JSONObject)obj;
            return Integer.valueOf(JsonPath.parse(jsonObject).read("$.items[0].statistics.likeCount"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Entity entity;


    public int likesSave=0;

    public void Mobs() {
        int likes=getLikes();
        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.getName().equals("Player Nickname"))
            {
                if(likes>likesSave)
                {
                    for(int i=likesSave; i<likes; i++)
                    {
                        Location loc = p.getLocation();
                        int max = 40;
                        double x = Math.random() * (max * 2) - max;
                        double z = Math.random() * (max * 2) - max;

                        loc.add(x,256,z);
                        loc.setY(loc.getWorld().getHighestBlockYAt(loc) + 15);

                        Zombie zombie = (Zombie) p.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                        zombie.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        if(i%5==0 && i!=0)
                        {
                            Zombie zombie1 =(Zombie) p.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                            zombie.getEquipment().setHelmet(helmets.get(new Random().nextInt(helmets.size())));
                            zombie.getEquipment().setChestplate(chestplate.get(new Random().nextInt(chestplate.size())));
                            zombie.getEquipment().setLeggings(leggings.get(new Random().nextInt(leggings.size())));
                            zombie.getEquipment().setBoots(boots.get(new Random().nextInt(boots.size())));
                        }
                        if(i%10==0 && i!=0)
                        {
                            Zombie babyZombie = (Zombie) p.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
                            babyZombie.setBaby(true);
                            babyZombie.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                        if(i%50==0 && i!=0)
                        {
                            Creeper creeper = (Creeper) p.getWorld().spawnEntity(loc, EntityType.CREEPER);
                            creeper.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                        if(i%100==0 && i!=0)
                        {
                            Witch witch = (Witch) p.getWorld().spawnEntity(loc, EntityType.WITCH);
                            witch.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                        if(i%200==0 && i!=0)
                        {
                            WitherSkeleton witherSkeleton = (WitherSkeleton) p.getWorld().spawnEntity(loc, EntityType.WITHER_SKELETON);
                            witherSkeleton.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                        if(i%500==0 && i!=0)
                        {
                            Ravager ravager = (Ravager) p.getWorld().spawnEntity(loc, EntityType.RAVAGER);
                            ravager.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                        if(i%1000==0 && i!=0)
                        {
                            Illusioner illusioner = (Illusioner) p.getWorld().spawnEntity(loc, EntityType.ILLUSIONER);
                            illusioner.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                        if(i%2000==0 && i!=0)
                        {
                            Wither wither = (Wither) p.getWorld().spawnEntity(loc, EntityType.WITHER);
                            wither.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
                        }
                    }
                    likesSave=likes;
                }
            }
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Mobs();
        },0, 20 * 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
