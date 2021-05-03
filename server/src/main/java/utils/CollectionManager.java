package utils;

import comparators.AchievementsComparator;
import comparators.WeaponTypeComparator;
import data.*;
import exceptions.EmptyCollectionException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Class for working with collection
 * @author NastyaBordun
 * @version 1.1
 */

public class CollectionManager {
    /**
     * Collection of elements {@link SpaceMarine}
     */
    private Stack<SpaceMarine> stack;
    /**
     * Last save time for the collection
     */
    private LocalDateTime lastSave = null;
    /**
     * Last initialization time for the collection
     */
    private LocalDateTime lastInit = null;

    private FileManager fileManager;

    /**
     * Creating a class to work with the collection
     * @see CollectionManager#lastInit
     * @see CollectionManager#lastSave
     */
    public CollectionManager(FileManager fileManager){
        Stack<SpaceMarine> fileCollection = fileManager.readCollection();
        if(!fileCollection.isEmpty()){
            this.stack = checkCollection(fileCollection);
        }
        else this.stack = fileCollection;
        this.lastInit = LocalDateTime.now();
        this.lastSave = null;
        this.fileManager = fileManager;
    }

    /**
     * Getting collection last saving time
     * @return {@link CollectionManager#lastSave}
     */
    public LocalDateTime getLastSave(){
        return this.lastSave;
    }
    /**
     * Getting collection last initialization time
     * @return {@link CollectionManager#lastInit}
     */
    public LocalDateTime getLastInit(){
        return this.lastInit;
    }
    /**
     * Getting collection
     * @return {@link CollectionManager#stack}
     */
    public Stack<SpaceMarine> getCollection(){
        return this.stack;
    }

    /**
     * Setting field {@link CollectionManager#stack}
     * @param newStack новая коллекция
     */
    public void setCollection(Stack<SpaceMarine> newStack){
        this.stack = newStack;
    }

    /**
     * Setting collection last saving time
     * @param localDateTime new collection last save
     */
    public void setLastSave(LocalDateTime localDateTime){
        this.lastSave = localDateTime;
    }

    /**
     * Getting collection size
     * @return collection size
     */
    public int collectionSize(){
        return stack.size();
    }

    /**
     * Checking data from file
     * @param fileCollection uploaded collection
     * @return changed collection
     */
    private Stack<SpaceMarine> checkCollection(Stack<SpaceMarine> fileCollection){
        ArrayList<Integer> ids = new ArrayList<>();
        boolean flag = true;
        for(SpaceMarine sp: fileCollection){
            if(sp.getId() == null || sp.getId() < 1 || ids.contains(sp.getId())){
                flag = false;
                break;
            }
            else{
                ids.add(sp.getId());
            }
        }
        if(!flag){
            Integer integer = 1;
            for(SpaceMarine sp: fileCollection){
                sp.setId(integer);
                integer++;
            }
            return fileCollection;
        }
        for(SpaceMarine sp: fileCollection){
            if(sp.getName() == null || sp.getName().length() == 0) sp.setName("Default Name");
            if(sp.getCoordinates() == null){
                Coordinates coordinates = new Coordinates(1, 1.0);
                sp.setCoordinates(coordinates);

            }
            else if(Math.abs(sp.getCoordinates().getX()) > 991) sp.getCoordinates().setX(1);
            else if(sp.getCoordinates().getY() == null) sp.getCoordinates().setY(1.0);
            if(sp.getCreationDate() == null) sp.setCreationDate(LocalDateTime.now());
            if(sp.getHealth() == null || sp.getHealth() <= 0) sp.setHealth(1.0f);
            if(sp.getAchievements() == null) sp.setAchievements("Default Achievements");
            if(sp.getMeleeWeapon() == null) sp.setMeleeWeapon(MeleeWeapon.POWER_BLADE);
            if(sp.getChapter() == null){
                Chapter chapter = new Chapter("Default Chapter name", "Default World");
                sp.setChapter(chapter);
            }
            else if(sp.getChapter().getName() == null) sp.getChapter().setName("Default Chapter name");
            else if(sp.getChapter().getWorld() == null) sp.getChapter().setWorld("Default World");
        }
        return fileCollection;
    }
    /**
     * Creating new ID for new element with type {@link SpaceMarine} of {@link CollectionManager#stack} collection
     * @return new ID
     * @see CollectionManager#getCollection()
     * @see SpaceMarine#getId()
     */
    public Integer getNewId(){
        Integer mxId = 0;
        for(SpaceMarine sm : this.getCollection()){
            if (sm.getId() > mxId) mxId = sm.getId();
        }
        return mxId + 1;
    }
    /**
     * Printing {@link CollectionManager#stack} collection
     * @throws EmptyCollectionException if collection is empty
     * @see CollectionManager#getCollection()
     */
    public String getStringCollection() throws EmptyCollectionException {
        if(this.getCollection().size() == 0) throw new EmptyCollectionException("Коллекция пуста");
        String ans = "";
        for (SpaceMarine sm : this.getCollection()){
            ans += "Космический корабль\n" + sm.toString() + "\n";
        }
        return ans;
    }

    /**
     * Adding new element to collection {@link CollectionManager#stack}
     * @param spaceMarine new element with type {@link SpaceMarine}
     * @see CollectionManager#getNewId()
     * @see SpaceMarine#setId(Integer)
     * @see SpaceMarine#setCreationDate(LocalDateTime)
     */
    public String addElement(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        this.stack.push(spaceMarine);
        return "-----\nЭлемент успешно добавлен\n-----";
    }

    /**
     * Searching element with type {@link SpaceMarine} in collection {@link CollectionManager#stack} by ID
     * @param id ID of the searching element
     * @return founded element with type {@link SpaceMarine}
     * @see SpaceMarine#getId()
     */
    public SpaceMarine searchById(Integer id){
        for(SpaceMarine sm : this.stack){
            if(sm.getId().equals(id)){
                return sm;
            }
        }
        return null;
    }

    /**
     * Deleting element with type {@link SpaceMarine} {@link CollectionManager#stack} collection by ID
     * @param id ID of the deleting element
     * @return operation result
     * @see CollectionManager#searchById(Integer)
     * @see CollectionManager#getCollection()
     */
    public boolean removeElementById(Integer id){
        SpaceMarine spaceMarine = searchById(id);
        if (spaceMarine != null){
            this.getCollection().remove(spaceMarine);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Collection clearing {@link CollectionManager#stack}
     */
    public void clearCollection(){
        stack.clear();
    }

    /**
     * Collection {@link CollectionManager#stack} saving in file
     * @throws IOException Input/Output exception
     * @see CollectionManager#getCollection()
     * @see CollectionManager#setLastSave(LocalDateTime)
     */
    public void saveCollection() throws IOException {
        fileManager.writeCollection(this.stack);
        this.setLastSave(LocalDateTime.now());
    }

    /**
     * Collection {@link CollectionManager#stack} sorting in reverse order
     * @see CollectionManager#getCollection()
     * @see CollectionManager#setCollection(Stack)
     */
    public void reorderCollection(){
        ArrayList<SpaceMarine> arrayList = new ArrayList<>(this.getCollection());
        Collections.reverse(arrayList);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(arrayList);
        this.setCollection(newStack);
    }

    /**
     * Searching for the collection elements with type {@link SpaceMarine}, with achievements fields starts with the certain substring
     * @param achievement substring for searching
     * @return suitable SpaceMarines
     * @see CollectionManager#getCollection()
     */
    public ArrayList<SpaceMarine> startsWithAchievements(String achievement){
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>();
        for (SpaceMarine sm : this.getCollection()){
            if(sm.getAchievements().indexOf(achievement) == 0){
                spaceMarines.add(sm);
            }
        }
        return spaceMarines;
    }

    /**
     * Printing command for field weaponType of all collection elements, with types {@link SpaceMarine}, in ascending order
     * @see WeaponTypeComparator
     * @see CollectionManager#printWeaponTypes(Stack)
     * @see CollectionManager#getCollection()
     */
    public String ascendWeaponType(){
        WeaponTypeComparator cmp = new WeaponTypeComparator();
        ArrayList<SpaceMarine> list = new ArrayList<>(this.getCollection());
        list.sort(cmp);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(list);
        return printWeaponTypes(newStack);
    }

    /**
     * Printing weaponType fields of collection {@link CollectionManager#stack} elements with types {@link SpaceMarine}
     * @param stack collection
     * @see SpaceMarine#getWeaponType()
     */
    public String printWeaponTypes(Stack <SpaceMarine> stack){
        String ans = "";
        for(SpaceMarine sm : stack){
            if(sm.getWeaponType() == null){
                ans += "null\n";
            }
            else{
                ans += sm.getWeaponType().toString() + "\n";
            }
        }
        return ans;
    }
    /**
     * Printing command for field achievements of all collection elements, with types {@link SpaceMarine}, in descending order
     * @see AchievementsComparator
     * @see CollectionManager#printAchievements(Stack)
     * @see CollectionManager#getCollection()
     */
    public String descendAchievements(){
        AchievementsComparator cmp = new AchievementsComparator();
        ArrayList<SpaceMarine> list = new ArrayList<>(this.getCollection());
        list.sort(cmp);
        Stack<SpaceMarine> newStack = new Stack<>();
        newStack.addAll(list);
        return printAchievements(newStack);
    }
    /**
     * Printing achievements fields of collection {@link CollectionManager#stack} elements with types {@link SpaceMarine}
     * @param stack collection
     */
    public String printAchievements(Stack<SpaceMarine> stack){
        String ans = "";
        for(SpaceMarine sm : stack){
            ans += sm.getAchievements() + "\n";
        }
        return ans;
    }

    /**
     * Removing for collection {@link CollectionManager#stack} elements with type {@link SpaceMarine}, bigger than assigned element
     * @param spaceMarine assigned element
     * @return operation result
     * @see SpaceMarine#setId(Integer)
     * @see SpaceMarine#setCreationDate(LocalDateTime)
     * @see CollectionManager#getCollection()
     * @see SpaceMarine#getName()
     * @see SpaceMarine#getId()
     * @see CollectionManager#removeElementById(Integer)
     */
    public boolean removeGreater(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>(this.getCollection());
        if(!spaceMarines.contains(spaceMarine)){
            spaceMarines.add(spaceMarine);
        }
        Collections.sort(spaceMarines);
        boolean flag = false;
        boolean checkpoint = false;
        for (SpaceMarine sm : spaceMarines){
            if (sm.getName().length() == spaceMarine.getName().length()){
                flag = true;
            }
            else if (flag){
                checkpoint = true;
                removeElementById(sm.getId());
            }
        }
        return checkpoint;
    }
    /**
     * Removing for collection {@link CollectionManager#stack} elements with type {@link SpaceMarine}, lower than assigned element
     * @param spaceMarine assigned element
     * @return operation result
     * @see SpaceMarine#setId(Integer)
     * @see SpaceMarine#setCreationDate(LocalDateTime)
     * @see CollectionManager#getCollection()
     * @see SpaceMarine#getName()
     * @see SpaceMarine#getId()
     * @see CollectionManager#removeElementById(Integer)
     */
    public boolean removeLower(SpaceMarine spaceMarine){
        spaceMarine.setId(getNewId());
        spaceMarine.setCreationDate(LocalDateTime.now());
        ArrayList<SpaceMarine> spaceMarines = new ArrayList<>(this.getCollection());
        if(!spaceMarines.contains(spaceMarine)){
            spaceMarines.add(spaceMarine);
        }
        Collections.sort(spaceMarines);
        boolean flag = true;
        boolean checkpoint = false;
        for (SpaceMarine sm : spaceMarines){
            if (sm.getName().length() == spaceMarine.getName().length()){
                flag = false;
            }
            else if (flag){
                checkpoint = true;
                removeElementById(sm.getId());
            }
        }
        return checkpoint;
    }

    @Override
    public String toString(){
        if(stack.isEmpty()){
            return "Коллекция пуста";
        }
        String res = "Коллекция:\n";
        for(SpaceMarine sm : this.stack){
            res += sm;
            res += "\n";
        }
        res += "\nДата последнего сохранения:\n";
        res += lastSave.toString();
        res += "\nДата последнего взаимодействия:\n";
        res += lastInit.toString();
        return res;
    }

}
