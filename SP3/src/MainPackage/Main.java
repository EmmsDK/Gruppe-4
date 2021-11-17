package MainPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static Interface  ui = new Interface();

    public static void main(String[] args) throws IOException {
        ui.inputFromUser();
    }
}
