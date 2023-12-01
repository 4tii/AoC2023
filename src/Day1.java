import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Day1 {

    public static void main(String[] args) throws IOException {

        Path filePath = Path.of("./input/input1.txt");
        String content = Files.readString(filePath);

        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line="";
        Integer sum=0;

        Map<String, String> replaceValues = Map.ofEntries(
                entry("one", "1"),
                entry("two", "2"),
                entry("three", "3"),
                entry("four", "4"),
                entry("five", "5"),
                entry("six", "6"),
                entry("seven", "7"),
                entry("eight", "8"),
                entry("nine", "9")
        );

        while((line= bufReader.readLine()) != null) {
            List<String> valuesToReplace = new ArrayList<>();
            Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|[1-9])");
            Matcher matcher = pattern.matcher(line);
            String numbersOnly="";
            if(matcher.find()) {
                numbersOnly = matcher.group(1);
                pattern = Pattern.compile("(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[1-9])");
                matcher = pattern.matcher(new StringBuilder(line).reverse());
                if(matcher.find()) {
                    numbersOnly = numbersOnly + new StringBuilder(matcher.group(1)).reverse();
                }
            }
            for (Map.Entry<String, String> entry : replaceValues.entrySet()) {
                numbersOnly = numbersOnly.replaceAll(entry.getKey(), entry.getValue());
            }
            if(numbersOnly.length()>1)
                sum += Integer.parseInt(numbersOnly.charAt(0) + numbersOnly.substring(numbersOnly.length()-1));
        }
        System.out.println(sum);

    }
}