package validation;

import java.util.logging.Logger;

public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());


    public static void main(String[] args) {
        String input = "NOT <GR>gПродавец ОТМ B2C</GR> AND (NOT <PF>Комплементарные продукты</PF> OR NOT ((<GR>gСпециалист ппоп РСО - R2.0</GR> OR <GR>gСпециалист ппоп ИСО</GR>) AND <GR>gSZT</GR>)) AND (NOT <PF>Комплементарные продукты</PF> OR NOT ( <GR>gПродавец call-center B2C</GR> AND <GR>gDSV</GR>))";

        RequestValidator validator = new RequestValidator();
        validator.execute(input, true);
    }


}
