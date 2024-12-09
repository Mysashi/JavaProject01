package ForJava;

import java.io.File;

interface FileInterface {

    void writeFile(String filepath, String nameofFile);

    void openFile(String filepath);

    default void fileToString(File file)  {
        System.out.println("ToStringNotImplemetedYet");
    }


}
