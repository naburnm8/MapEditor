import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapEditor {
    public static void main(String[] args) throws IOException, IncorrectFileStructure {
        /*
        Field test2 = new Field(10,10);
        FileWriter fw = new FileWriter("file.txt");
        fw.write("10\n");
        fw.write("10\n");
        fw.write(test2.toString());
        System.out.println(test2);
        fw.close();

        FileReader fr = new FileReader("file.txt");
        Scanner stream = new Scanner(fr);
        stream.useDelimiter("\\Z");
        System.out.println(stream.next());
        fr.close();
        FileReader fr = new FileReader("file.txt");
        Scanner stream = new Scanner(fr);
        System.out.println(stream.nextInt());
        System.out.println(stream.nextInt());
        stream.nextLine();
        stream.useDelimiter("\\Z");
        System.out.println(stream.next());
        Field test1 = new Field(10,10);

        MapReader reader = new MapReader("file.txt");
        Field test1 = new Field(reader.read());
        System.out.println(test1);
        */
        MapData test = new MapData(6,6);
        MapWriter writer = new MapWriter("test1.txt");
        writer.write(test);
        MapReader reader = new MapReader("file.txt");
        MapData test2 = reader.read();
        System.out.println(test2);
        System.out.println(test2.width);
        System.out.println(test2.height);
        }
    }
