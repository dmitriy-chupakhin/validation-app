package validation;

import java.util.logging.Logger;

public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        String input = "(<A></A> AND <B></B>) AND (ddd) AND <BR></BR>";

        RequestValidator validator = new RequestValidator();
        boolean valid = validator.execute(input, true);

        if (valid) {
            logger.info("Request is valid.");
        } else {
            logger.info("Request is not valid!");
        }
    }


}
