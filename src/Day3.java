import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static int size=140;
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input3.txt");
        String content = Files.readString(filePath);

        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line="";



        char[][] input = new char[size][size];
        int i=0;

        int sum=0;

        while((line= bufReader.readLine()) != null) {
            for(int j=0; j<line.length(); j++){
                char c = line.toCharArray()[j];
                input[i][j]=c;
            }
            i++;
        }

        for (int k=0; k<size; k++){
            for(int j=0; j<size; ){
                int num=0;
                if(input[k][j]>=48 && input[k][j]<=57){
                    num=checkNeighbours(k, j, input);
                    sum += num;
                }
                if(num!=0)
                    j+=String.valueOf(num).length()+1;
                else
                    j++;
            }
        }
        System.out.println(sum);

        //Part 2
        sum=0;

        List<Point> pointList = new ArrayList<>();

        for (int k=0; k<size; k++){
            for(int j=0; j<size; ){
                int num=0;
                if(input[k][j]>=48 && input[k][j]<=57){
                    Point p = checkNeighboursForStars(k, j, input);
                    if(p!=null) {
                        pointList.add(p);
                        num = p.getNum();
                    }
                }
                if(num!=0)
                    j+=String.valueOf(num).length()+1;
                else
                    j++;
            }
        }

        for(int idx=0; idx<pointList.size(); idx++){
            Point p = pointList.get(idx);
            long amout = pointList.stream().filter(q -> q.getX()==p.getX() && q.getY()==p.getY()).count();
            if(amout==2){
                List<Point> newList = pointList.stream().filter(q -> q.getX()==p.getX() && q.getY()==p.getY()).toList();
                sum = sum + (newList.get(0).getNum()*newList.get(1).getNum());
                pointList.removeIf(q -> q.getX()==p.getX() && q.getY()==p.getY());
                if(idx-2<0)
                    idx=0;
                else
                    idx-=2;
            }
        }
        System.out.println(sum);
    }

    public static int checkNeighbours(int i, int j, char[][] input){
        int num = getNumber(i, j, input);

        for(int k=-1; k<=1; k++){
            for(int l=-1; l<=String.valueOf(num).length(); ){
                if(i+k<size && i+k>0 && j+l<size && j+l>0) {
                    if (isNotDotAndNumber(input[i + k][j + l]))
                        return num;
                }
                if (k == 0)
                    l = l + String.valueOf(num).length() + 1;
                else
                    l++;
            }
        }

        return 0;
    }

    public static int getNumber(int i, int j, char[][] input){
        String num="";
        for(int k=0; k<3; k++){
            if(j+k<size && input[i][j+k]>=48 && input[i][j+k]<=57){
                num += input[i][j+k];
            }else
                break;
        }
        return Integer.parseInt(num);
    }

    public static boolean isNotDotAndNumber(char c){
        if(c!=46 && (c<48 || c>57))
            return true;
        return false;
    }

    public static Point checkNeighboursForStars(int i, int j, char[][] input){
        int num = getNumber(i, j, input);

        for(int k=-1; k<=1; k++){
            for(int l=-1; l<=String.valueOf(num).length(); ){
                if(i+k<size && i+k>0 && j+l<size && j+l>0) {
                    if (isStar(input[i + k][j + l]))
                        return new Point(i+k, j+l, num);
                }
                if (k == 0)
                    l = l + String.valueOf(num).length() + 1;
                else
                    l++;
            }
        }

        return null;
    }

    public static boolean isStar(char c){
        if(c==42)
            return true;
        return false;
    }

    public static class Point{
        int x;
        int y;

        int num;

        public Point(int x, int y, int num){
            this.x=x;
            this.y=y;
            this.num=num;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
