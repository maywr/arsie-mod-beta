package xyz.maywr.arsie.impl.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import org.json.JSONObject;
import xyz.maywr.arsie.Arsie;
import xyz.maywr.arsie.api.settings.Setting;

import java.util.ArrayList;

import static xyz.maywr.arsie.Arsie.mc;

public class Module {

    private final String name, description;
    private final Category category;
    private boolean enabled = false;
    private int keyBind;
    private ArrayList<Setting> settings = new ArrayList<>();

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public void enable(){
        this.enabled = true;
        if(Arsie.moduleManager.getModule("ChatNotifies").isEnabled() && (!name.equalsIgnoreCase("ClickGUI"))) {
            mc.player.sendMessage(new TextComponentString(ChatFormatting.LIGHT_PURPLE + "[arsie] " + ChatFormatting.WHITE + this.getName() + ChatFormatting.GREEN + " enabled"));
        }
        MinecraftForge.EVENT_BUS.register(this);
        this.onEnable();
    }

    public void disable(){
        this.enabled = false;
        if(Arsie.moduleManager.getModule("ChatNotifies").isEnabled() && (!name.equalsIgnoreCase("ClickGUI"))) {
            mc.player.sendMessage(new TextComponentString(ChatFormatting.LIGHT_PURPLE + "[arsie] " + ChatFormatting.WHITE + name + ChatFormatting.RED + " disabled"));
        }
        MinecraftForge.EVENT_BUS.unregister(this);
        this.onDisable();
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public void toggle(){
        if(this.enabled){
            this.disable();
        } else if (!this.enabled) {
            this.enable();
        }
    }

    public void setKeyBind(int keyCode){
        this.keyBind = keyCode;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Category getCategory(){
        return this.category;
    }

    public int getKeyBind(){
        return this.keyBind;
    }

    public void onEnable(){}
    public void onDisable(){}

    public Setting register(Setting setting){
        this.settings.add(setting);
        return this.settings.get(this.settings.indexOf(setting));
    }

    public Setting getSetting(String name){
        for(Setting setting : this.settings){
            if(setting.getName().equalsIgnoreCase(name)) return setting;
        }
        return null;
    }

    @Override
    public String toString(){
        JSONObject object = new JSONObject();
        object.put("name", this.getName());
        object.put("desc", this.getDescription());
        object.put("bind", this.getKeyBind());
        object.put("category", this.category.getName());
        object.put("enabled", this.isEnabled());
        return object.toString();
    }
}
