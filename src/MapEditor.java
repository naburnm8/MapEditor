import java.io.IOException;
import java.util.Scanner;

public class MapEditor {
    private static MapData Edit(MapData edited){
        System.out.println("Syntax: -clear; -point x y type; -save");
        MapData output = edited;
        while(true){
            System.out.println(edited.toStringMenu());
            Scanner stream = new Scanner(System.in);
            String scanned = stream.nextLine();
            String[] split = scanned.split(" ");
            if(!(split[0].equals("-clear") || split[0].equals("-point") || split[0].equals("-save"))){
                System.out.println("Wrong syntax!");
                continue;
            }
            if (split[0].equals("-clear")){
                output = new MapData(edited.width, edited.height);
                continue;
            }
            if(split[0].equals("-save")){
                return output;
            }
            if(split[0].equals("-point")){
                int x = Integer.parseInt(split[1]);
                int y = Integer.parseInt(split[2]);
                int type = Integer.parseInt(split[3]);
                output.mapLayout[y][x] = Field.gnd_symbols[type-1];
                continue;
            }
        }
    }
    public static void main(String[] args) throws IOException, IllegalTileSymbol, IncorrectFileStructure {
            System.out.println("Welcome to Bauman's Gate map editor.\nSyntax: -load path; -create name");
            Scanner stream = new Scanner(System.in);
            String[] split = null;
            while(true){
                String read = stream.nextLine();
                split = read.split(" ");
                if (split[0].equals("-load") || split[0].equals("-create")){
                    break;
                }
                System.out.println("Wrong syntax!");
            }
            String path = split[1];
            if (split[0].equals("-create")){
                System.out.println("Enter dimensions: [width] [height]");
                String read = stream.nextLine();
                split = read.split(" ");
                MapData wr = Edit(new MapData(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                MapWriter writer = new MapWriter(path);
                writer.write(wr);
                return;
            }
            if (split[0].equals("-load")){
                MapReader rd = new MapReader(path);
                MapData wr;
                try {
                wr = Edit(rd.read());
                } catch (IncorrectFileStructure e){
                    System.out.println("Wrong file structure");
                    return;
                } catch (IllegalTileSymbol a){
                    System.out.println("Illegal symbols detected");
                    return;
                }
                System.out.println("Specify the new path: ");
                String newpath = stream.nextLine();
                MapWriter writer = new MapWriter(newpath);
                writer.write(wr);
            }


        }
    }
