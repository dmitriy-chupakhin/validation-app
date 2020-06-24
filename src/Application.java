import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        String input = "NOT <GR>gПродавец ОТМ B2C</GR> AND (NOT <PF>Комплементарные продукты</PF> OR NOT ((<GR>gСпециалист ппоп РСО - R2.0</GR> OR <GR>gСпециалист ппоп ИСО</GR>) AND <GR>gSZT</GR>)) AND (NOT <PF>Комплементарные продукты</PF> OR NOT ( <GR>gПродавец call-center B2C</GR> AND <GR>gDSV</GR>))";
        String input2 = "<G> fff </G> AND <G> fff </G>";

        Matcher matcher = searchNonClosedTag(input);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    private static boolean searchNonReadable(String input) {
        Pattern pattern = Pattern.compile("not+ | and+ | or+|\\n+|[\\x00\\x08\\x0B\\x0C\\x0E-\\x1F]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private static Matcher searchNonClosedTag(String input) {
        Pattern pattern = Pattern.compile("<([^/]+)>[^<>()]+(?=AND|OR|NOT|AND NOT|OR NOT|[()]|(<(?!/)\\1>))");
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    private static Matcher searchNonOperators(String input) {
        Pattern pattern = Pattern.compile("([)]|.{2}>)\\s(?!AND|NOT|OR|OR NOT|AND NOT)\\s([(]|<.{2})");
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

}
