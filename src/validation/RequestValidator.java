package validation;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestValidator {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private static final String operators = "AND|OR|NOT|AND NOT|OR NOT";

    private Matcher searchNoneReadable(String input) {
        Pattern pattern = Pattern.compile("not+ | and+ | or+|\\n+|[\\x00\\x08\\x0B\\x0C\\x0E-\\x1F]");
        return pattern.matcher(input);
    }

    private Matcher searchNoneClosedTag(String input) {
        Pattern pattern = Pattern.compile("<([^/]+)>[^<>()]+?(?=" + operators + "|[()]|(<(?!/)\\1>))");
        return pattern.matcher(input);
    }

    private Matcher searchNoneOpenedTag(String input) {
        Pattern pattern = Pattern.compile("((?<=" + operators + "|[()])[^<>()]+?(</.+>))|(^(?!" + operators + "|[<(]).+?)");
        return pattern.matcher(input);
    }

    private Matcher searchNoneOperators(String input) {
        Pattern pattern = Pattern.compile("([)]|.+?>)\\s*(?!" + operators + ")\\s*([(]|<.*?)");
        return pattern.matcher(input);
    }

    private Matcher searchForbiddenSymbols(String input) {
        Pattern pattern = Pattern.compile("<(?!/?[A-Za-z0-9]+>)");
        return pattern.matcher(input);
    }

    public boolean execute(String request, boolean isPrintErrors) {
        return validate(searchNoneReadable(request), "Find none readable symbols", isPrintErrors) &
                validate(searchForbiddenSymbols(request), "Find forbidden symbol", isPrintErrors) &
                validate(searchNoneClosedTag(request), "Find none closed tag", isPrintErrors) &
                validate(searchNoneOpenedTag(request), "Find none open tag", isPrintErrors) &
                validate(searchNoneOperators(request), "Find none operators", isPrintErrors);
    }

    private boolean validate(Matcher matcher, String errorMessage, boolean isPrint) {
        if (matcher.find()) {
            if (isPrint) {
                logger.info(errorMessage + ": " + matcher.group());
                while (matcher.find()) {
                    logger.info(errorMessage + ": " + matcher.group());
                }
            }
            return false;
        }
        return true;
    }
}
