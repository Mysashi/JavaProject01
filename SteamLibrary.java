package ForJava;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class SteamLibrary extends BasicLibrary implements FileInterface {

    private final String name;
    private final DataBase dataBase;
    private final HashMap<String, HashMap<String, ArrayList<String>>> hashUsers = new HashMap<>();
    public SteamLibrary(String name) {
        this.name = name;
    }
    {
        initWindow();
        dataBase = new DataBase();
    }




    private void initWindow() {
        System.out.println("Вас приветствует библиотека Steam!");
    }

    @Override
    public void writeFile(String filepath, String nameofFile) {
        String path = filepath + '/' + nameofFile;
        System.out.println(path);
        String s = getAllInfo();
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(s);
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void openFile(String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addGameIntoSteam(String game, String description,String genre, Boolean mark) {
        dataBase.addGame(game,description,genre,mark);

    }

    public void deleteGameFromSteam(String game) {
        dataBase.deleteGame(game);
    }
    public void updateDescription(String newDescription, String game) {
        dataBase.updateGameDescription(newDescription, game);
    }


}
