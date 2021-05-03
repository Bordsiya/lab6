package data;

import java.io.Serializable;

/**
 * Field chapter for class {@link SpaceMarine}
 * @author NastyaBordun
 * @version 1.1
 */

public class Chapter implements Serializable {
    /**
     * Chapter name
     */
    private String name;
    /**
     * Chapter world
     */
    private String world;

    /**
     * Empty constructor for class {@link Chapter} (for correct Json parsing)
     * @see Chapter#Chapter(String, String)
     */
    public Chapter(){
        this.name = null;
        this.world = null;
    }

    /**
     * Constructor for class {@link Chapter}
     * @param name Chapter name
     * @param world Chapter world
     */
    public Chapter(String name, String world){
        this.name = name;
        this.world = world;
    }

    /**
     * Getting field value {@link Chapter#name}
     * @return {@link Chapter#name}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setting field value {@link Chapter#name}
     * @param name new Chapter name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getting field value {@link Chapter#world}
     * @return {@link Chapter#world}
     */
    public String getWorld(){
        return this.world;
    }

    /**
     * Setting field value {@link Chapter#world}
     * @param world new Chapter world
     */
    public void setWorld(String world) {
        this.world = world;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        if (this.hashCode() != chapter.hashCode()) return false;
        return (this.getName().equals(chapter.getName()) && this.getWorld().equals(chapter.getWorld()));
    }

    @Override
    public int hashCode(){
        int ans = 0;
        for (int i = 0; i < this.getName().length(); i++){
            ans = (ans + (int)this.getName().charAt(i)) % 2000000000;
        }
        ans = (ans + this.getWorld().length()) % 2000000000;
        return ans;
    }

    @Override
    public String toString(){
        String res = "Название главы: " + this.getName() + "\n";
        res += "Название мира: " + this.getWorld() + "\n";
        return res;
    }
}
