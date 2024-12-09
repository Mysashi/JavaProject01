package ForJava;

import java.util.ArrayList;

public class BasicLibrary extends Igroteka {
    private boolean favorite;

    @Override
    public String getAllInfo() {
        StringBuilder f = new StringBuilder();

        if (hash.isEmpty()) {
            System.out.println("К сожалению игр в библиотеке нет \n");
        }
        else {
            for (String key: hash.keySet()) {
                String desc = hash.get(key).getFirst().toString();
                f.append(String.format("Игра: %s \n Описание: %s \n", key, desc));
                f.append(String.format("В избранных: %s\n", hash.get(key).getLast().toString()));
            }
        }
        return f.toString();

    }
    public void markFavorite(String name) {
        ArrayList<Object> arrayLAddFavorite = hash.get(name);
        arrayLAddFavorite.addLast(true);
        hash.put(name, arrayLAddFavorite);
    }

    @Override
    public void addGame(String name, String description) {
        super.addGame(name, description);
        ArrayList<Object> arrayList = hash.get(name);
        arrayList.addLast(false);
        hash.put(name, arrayList);

    }
}
