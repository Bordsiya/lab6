package data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SpaceShip
 * @author NastyaBordun
 * @version 1.1
 */

public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {
    /**
     * SpaceShip ID, generated automatically
     */
    private Integer id = null;
    /**
     * SpaceShip name
     */
    private String name = null;
    /**
     * SpaceShip coordinates
     */
    private Coordinates coordinates = null;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    /**
     * SpaceShip creation date, generated automatically
     */
    private LocalDateTime creationDate = null;
    /**
     * SpaceShip health
     */
    private Float health = null;
    /**
     * SpaceShip achievements
     */
    private String achievements = null;
    /**
     * SpaceShip weapon type
     */
    private Weapon weaponType = null;
    /**
     * SpaceShip melee weapon
     */
    private MeleeWeapon meleeWeapon = null;
    /**
     * SpaceShip chapter
     */
    private Chapter chapter = null;

    /**
     * Empty constructor for {@link SpaceMarine} (for correct Json parsing)
     * @see SpaceMarine#SpaceMarine(Integer, String, Coordinates, Float, String, Weapon, MeleeWeapon, Chapter)
     */
    public SpaceMarine(){

    }

    /**
     * Constructor for {@link SpaceMarine}
     * @param id SpaceShip iD
     * @param name SpaceShip name
     * @param coordinates SpaceShip coordinates
     * @param health SpaceShip health
     * @param achievements SpaceShip achievements
     * @param weaponType SpaceShip weapon type
     * @param meleeWeapon SpaceShip melee weapon
     * @param chapter SpaceShip chapter
     * @see SpaceMarine#SpaceMarine()
     */
    public SpaceMarine(Integer id, String name, Coordinates coordinates, Float health, String achievements, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.health = health;
        this.achievements = achievements;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    /**
     * Getting field value {@link SpaceMarine#id}
     * @return {@link SpaceMarine#id}
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * Getting field value {@link SpaceMarine#name}
     * @return {@link SpaceMarine#name}
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getting field value {@link SpaceMarine#coordinates}
     * @return {@link SpaceMarine#coordinates}
     */
    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    /**
     * Getting field value {@link SpaceMarine#creationDate}
     * @return {@link SpaceMarine#creationDate}
     */
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    /**
     * Getting field value {@link SpaceMarine#health}
     * @return {@link SpaceMarine#health}
     */
    public Float getHealth(){
        return this.health;
    }

    /**
     * Getting field value {@link SpaceMarine#achievements}
     * @return {@link SpaceMarine#achievements}
     */
    public String getAchievements(){
        return this.achievements;
    }

    /**
     * Getting field value {@link SpaceMarine#weaponType}
     * @return {@link SpaceMarine#weaponType}
     */
    public Weapon getWeaponType(){
        return this.weaponType;
    }

    /**
     * Getting field value {@link SpaceMarine#meleeWeapon}
     * @return {@link SpaceMarine#meleeWeapon}
     */
    public MeleeWeapon getMeleeWeapon(){
        return this.meleeWeapon;
    }

    /**
     * Getting field value {@link SpaceMarine#chapter}
     * @return {@link SpaceMarine#chapter}
     */
    public Chapter getChapter(){
        return this.chapter;
    }

    /**
     * Setting field value {@link SpaceMarine#id}
     * @param id new SpaceShip ID
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Setting field value {@link SpaceMarine#name}
     * @param name new SpaceShip name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setting field value {@link SpaceMarine#creationDate}
     * @param localDateTime new SpaceShip creation date
     */
    public void setCreationDate(LocalDateTime localDateTime){
        this.creationDate = localDateTime;
    }

    /**
     * Setting field value {@link SpaceMarine#coordinates}
     * @param coordinates new SpaceShip coordinates
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    /**
     * Setting x value of field {@link SpaceMarine#coordinates}
     * @param x new SpaceShip x coordinate
     * @see Coordinates#setX(long)
     */
    public void setCoordinateX(long x){
        this.coordinates.setX(x);
    }

    /**
     * Setting y value of field {@link SpaceMarine#coordinates}
     * @param y new SpaceShip y coordinate
     * @see Coordinates#setY(Double)
     */
    public void setCoordinateY(Double y){
        this.coordinates.setY(y);
    }

    /**
     * Setting field value {@link SpaceMarine#health}
     * @param health new SpaceShip health
     */
    public void setHealth(Float health){
        this.health = health;
    }

    /**
     * Setting field value {@link SpaceMarine#achievements}
     * @param achievements new SpaceShip achievements
     */
    public void setAchievements(String achievements){
        this.achievements = achievements;
    }

    /**
     * Setting field value {@link SpaceMarine#weaponType}
     * @param weaponType new SpaceShip weapon type
     */
    public void setWeaponType(Weapon weaponType){
        this.weaponType = weaponType;
    }

    /**
     * Setting field value {@link SpaceMarine#meleeWeapon}
     * @param meleeWeapon new SpaceShip melee weapon
     */
    public void setMeleeWeapon(MeleeWeapon meleeWeapon){
        this.meleeWeapon = meleeWeapon;
    }

    /**
     * Setting field value {@link SpaceMarine#chapter}
     * @param chapter new Spaceship chapter
     */
    public void setChapter(Chapter chapter){
        this.chapter = chapter;
    }

    /**
     * Setting name value of field {@link SpaceMarine#chapter}
     * @param name new SpaceShip Chapter name
     */
    public void setChapterName(String name){
        this.chapter.setName(name);
    }

    /**
     * Setting name value of field {@link SpaceMarine#chapter}
     * @param world new SpaceShip Chapter world
     */
    public void setChapterWorld(String world){
        this.chapter.setWorld(world);
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if(this.getName().length() > o.getName().length()) return 1;
        else if(this.getName().length() == o.getName().length()) return 0;
        else return -1;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SpaceMarine spaceMarine = (SpaceMarine) o;
        if (this.hashCode() != spaceMarine.hashCode()) return false;
        return (this.getName().equals(spaceMarine.getName()) && this.getId().equals(spaceMarine.getId())
        && this.getCoordinates().equals(spaceMarine.getCoordinates()) && this.getHealth().equals(spaceMarine.getHealth())
        && this.getAchievements().equals(spaceMarine.getAchievements()) && this.getWeaponType() == spaceMarine.getWeaponType()
        && this.getMeleeWeapon() == spaceMarine.getMeleeWeapon() && this.getChapter().equals(spaceMarine.getChapter()));
    }

    @Override
    public int hashCode(){
        int ans = 0;
        for (int i = 0; i < this.getName().length(); i++){
            ans = (ans + (int)this.getName().charAt(i)) % 2000000000;
        }
        ans = (ans + this.getAchievements().length()) % 2000000000;
        return ans + coordinates.hashCode();
    }

    @Override
    public String toString(){
        String res = "Id: " + this.getId() + "\n";
        res += "Название корабля: " + this.getName() + "\n";
        res += "Координаты:\n" + this.getCoordinates().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        res += "Дата создания: " + this.getCreationDate().format(formatter) + "\n";
        res += "Здоровье: " + this.getHealth() + "\n";
        res += "Достижения: " + this.getAchievements() + "\n";
        if(this.getWeaponType() == null){
            res += "Тип оружия: null\n";
        }
        else res += "Тип оружия: " + this.getWeaponType().toString() + "\n";
        if(this.getMeleeWeapon() == null){
            res += "Тип оружия ближнего боя: null\n";
        }
        else res += "Тип оружия ближнего боя: " + this.getMeleeWeapon().toString() + "\n";
        res += "Глава:\n" + this.getChapter().toString();
        return res;
    }
}
