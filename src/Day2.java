import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day2 {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input2.txt");
        String content = Files.readString(filePath);

        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line="";
        Integer sum=0;

        int maxRed=12;
        int maxGreen=13;
        int maxBlue=14;

        while((line= bufReader.readLine()) != null) {
            String id = line.split(":")[0].split(" ")[1];
            String[] games = line.split(":")[1].split(";");
            boolean addToSum = true;
            for(String game: games){
                String[] cubes = game.split(",");
                for(String cube : cubes){
                    if(cube.contains("red") && Integer.parseInt(cube.replaceAll("[a-z]", "").trim())>maxRed){
                        addToSum = false;
                        break;
                    }else if(cube.contains("blue") && Integer.parseInt(cube.replaceAll("[a-z]", "").trim())>maxBlue){
                        addToSum = false;
                        break;
                    }else if(cube.contains("green") && Integer.parseInt(cube.replaceAll("[a-z]", "").trim())>maxGreen){
                        addToSum = false;
                        break;
                    }
                }
                if(!addToSum)
                    break;
            }
            if(addToSum)
                sum+=Integer.parseInt(id);
        }
        System.out.println(sum);

        //Part 2
        bufReader = new BufferedReader(new StringReader(content));
        sum=0;
        maxRed=0;
        maxGreen=0;
        maxBlue=0;

        while((line= bufReader.readLine()) != null) {
            String[] games = line.split(":")[1].split(";");
            for(String game: games){
                String[] cubes = game.split(",");
                for(String cube : cubes){
                    int value=Integer.parseInt(cube.replaceAll("[a-z]", "").trim());
                    if(cube.contains("red") && value>maxRed){
                        maxRed = value;
                    }else if(cube.contains("blue") && value >maxBlue){
                        maxBlue = value;
                    }else if(cube.contains("green") && value >maxGreen){
                        maxGreen = value;
                    }
                }
            }
            sum = sum + (maxRed*maxBlue*maxGreen);
            maxRed=0;
            maxGreen=0;
            maxBlue=0;
        }
        System.out.println(sum);
    }
}
