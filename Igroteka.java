package ForJava;

import java.util.ArrayList;
import java.util.HashMap;

public class Igroteka{

    public ArrayList<String> getListOfGames() {
        return listOfGames;
    }

    private final ArrayList<String> listOfGames = new ArrayList<>();
    protected final HashMap<String, ArrayList<Object>> hash = new HashMap<>();


    public HashMap<String, ArrayList<Object>> getHash() {
        return hash;
    }

    public void addGame(String name, String description) {
        name = normalizingName(name);
        ArrayList<Object> arrayL = new ArrayList<>();
        arrayL.add(description);
        if (listOfGames.contains(name)) {
            System.out.println("В библиотеке уже есть такая игра!");
        }
        else {
            hash.put(name, arrayL);
            listOfGames.add(name);
        }

    }
    public void clearAll() {
        hash.clear();
        listOfGames.clear();
    }
    public void deleteGame(String name) {
        if (listOfGames.contains(name) && hash.containsKey(name)) {
            listOfGames.remove(listOfGames.lastIndexOf(name));
            hash.remove(name);
            System.out.println("Игра успешно удалена!");
        }
        else {
            System.out.println("Такой игры нет в библиотеке");
        }
    }

    public void getAllGames() {
        if (listOfGames.isEmpty()) {
            System.out.println("В библиотеке нет игр \n");
        }
        else
        {
            for (String listOfGame : listOfGames) {
                System.out.println(listOfGame);
            }
        }
    }
    public String getAllInfo() {
        String f = "";
        if (hash.isEmpty()) {
            System.out.println("К сожалению игр в библиотеке нет \n");
        }
        else {
            for (String key: hash.keySet()) {
                String desc = hash.get(key).getFirst().toString();
                f += String.format("Игра: %s \n Описание; %s \n", key, desc);
            }
        }
        return f;

    }


   public void getDescriptionOfGame(String name) {
        ArrayList<Object> buffer = hash.get(name);
       System.out.printf("Описание %s: %s \n", name,buffer.getFirst().toString());
   }

   public void editDescription(String name, String description) {
       ArrayList<Object> buffer = new ArrayList<>();
       buffer.add(description);
       buffer.add(hash.get(name).get(1).toString());
       hash.put(name, buffer);
       System.out.println("Описание изменено успешно! \n");

   }

    private String normalizingName(String name) {
       return (Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase()).strip();
    }



}
