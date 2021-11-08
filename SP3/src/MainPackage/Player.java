import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    ArrayList<String> playerNamesArray = new ArrayList<>();
    ArrayList<String> teamsArray = new ArrayList<>();
    Scanner sc = new Scanner(System.in);


    public void inputFromPlayers() {
        System.out.println(" *VELKOMMEN TIL ÅRETS SKOLE BORDFODBOLDTURNERING!* ");

        while (teamsArray.size() < 16) {      // Scanner skal lukkes efter 16 hold er skrevet ind.
            System.out.println("Indtast første spillers navn:");
            playerNamesArray.add(sc.next());
            System.out.println("Indtast andens spillers navn:");
            playerNamesArray.add(sc.next());

            int userChoice = playerChoiceInt();
            if (userChoice == 1) {
                System.out.println("Indtast næste spillers navn:");
                playerNamesArray.add(sc.next());

            }
            System.out.println("Indtast et holdnavn:");
            teamsArray.add(sc.next());
            System.out.println("Jeres hold er nu oprettet. ");

            displayList(teamsArray);
        }
        sc.close();     // Closer scanneren ude af while loop scopet.
    }

    public void displayList(ArrayList teamsArray) {     // Metode der viser de tilføjede holdnavne.
        for (int i = 0; i < teamsArray.size(); i++) {
            System.out.println(teamsArray.get(i));
        }
    }

    public int playerChoiceInt() {        // Exception metode
        int choice = 0;
        System.out.println("Er i flere spillere? Indtast '1' for ja eller '2' for nej.");  // Spørger her
        try {
            String inStr = sc.next();
            int inInt = Integer.parseInt(inStr);
            if (inInt < 1 || inInt > 2) {       // Hvis der bliver indtastet alt andet end 1 eller 2, så skal der throwes en exception
                throw new InputMismatchException(); // Hvis inputtet/typen ikke passer til metoden. ('throw' bruges til at throw en specifik exception for en metode)
                // "InputMismatchException" opstår når man tager input fra brugeren, som ikke matcher metoden.
                // Fx hvis man læser en integer data med nextInt metoden, og værdien der bliver skrevet er af String, så sker der en fejl.
            } else {
                choice = inInt;     // Sætter choice til inInt, for at kunne køre videre hvis brugere skriver 1 eller 2.
            }
        } catch (InputMismatchException e) {     // Exception specifikt for scanneren, så prøv igen.
            System.out.println(" *FORKERT TAL INPUT! Du kan kun skrive '1' eller '2'!* ");
            playerChoiceInt();
        } catch (NumberFormatException e) {     // Exception for overall, hvis brugeren skriver en string i stedet for et tal, så prøv igen.
            // Kastes når man prøver at convert en string til et tal, heri fx int, float osv.
            System.out.println(" *FORKERT INPUT, DET KAN IKKE VÆRE BOGSTAVER! Du kan kun skrive '1' eller '2'!* ");
            playerChoiceInt();
        }
        return choice;
    }
}

// Ingen space mellem input fx Team Fire = TeamFire.

