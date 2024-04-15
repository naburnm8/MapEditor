import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapEditor {
    public static void main(String[] args) throws IOException, IncorrectFileStructure {
            System.out.println("Welcome to Bauman's Gate map editor.\nSyntax: -load path -create name");
            Scanner stream = new Scanner(System.in);
            while(true){
                String read = stream.nextLine();
                String[] split = read.split(" ");
                if (split[0].equals("-load") || split[0].equals("-create")){
                    break;
                }
                System.out.println("Wrong syntax!");
            }

        }
    }
