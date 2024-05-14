import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MapEditor {
    private static MapData Edit(MapData edited, Scanner stream){
        System.out.println("Syntax: -clear; -point x y type; -save; -addObstacle [character] [fineInfantry] [fineArcher] [fineMounted]");
        MapData output = edited;
        while(true){
            System.out.println(edited.toStringMenu());
            //Scanner stream = new Scanner(inputStream);
            String scanned = stream.nextLine();
            //System.out.println(scanned);
            String[] split = scanned.split(" ");
            if(!(split[0].equals("-clear") || split[0].equals("-point") || split[0].equals("-save") || split[0].equals("-addObstacle"))){
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
            if(split[0].equals("-addObstacle")){
                char character = split[1].charAt(0);
                double fineInfantry = Double.parseDouble(split[2]);
                double fineArcher = Double.parseDouble(split[3]);
                double fineMounted = Double.parseDouble(split[4]);
                Obstacle add = new Obstacle(new double[]{fineInfantry, fineArcher, fineMounted}, character);
                edited.obstacles.add(add);
            }
            if(split[0].equals("-point")){
                int x = Integer.parseInt(split[1]);
                int y = Integer.parseInt(split[2]);
                int type = Integer.parseInt(split[3]);
                //output.mapLayout[y][x] = Field.gnd_symbols[type-1];
                output.mapLayout[y][x] = output.obstacles.get(type-1).getSymbol();
                continue;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        mainStream(args, System.in);
    }
    public static void mainStream(String[] args, InputStream inputStream) throws IOException {
            System.out.println("Welcome to Bauman's Gate map editor.\nSyntax: -load path; -create name");
            Scanner stream = new Scanner(inputStream);
            String[] split = null;
            while(true){
                String read = stream.nextLine();
                //System.out.println(read);
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
                //System.out.println(read);
                split = read.split(" ");
                MapData wr = Edit(new MapData(Integer.parseInt(split[0]), Integer.parseInt(split[1])), stream);
                MapWriter writer = new MapWriter(path);
                writer.write(wr);
                return;
            }
            if (split[0].equals("-load")){
                MapReader rd = new MapReader(path);
                MapData wr;
                wr = Edit(rd.read(), stream);
                System.out.println("Specify the new path: ");
                String newpath = stream.nextLine();
                MapWriter writer = new MapWriter(newpath);
                writer.write(wr);
            }


        }
    }
