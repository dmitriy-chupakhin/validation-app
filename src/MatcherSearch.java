import java.util.regex.Matcher;

public class MatcherSearch {
    private Matcher matcher;

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MatcherSearch(Matcher matcher, String message) {
        this.matcher = matcher;
        this.message = message;
    }

    private String message;
}
