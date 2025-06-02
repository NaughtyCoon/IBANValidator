public class Main {

    public static void main(String[] args) {

        IBANValidator validator = new IBANValidator();

        final String a = "•\tGB82 WEST 1234 5698 7654 32 ";

        System.out.println(validator.isValid(a) ? "Карта валидна" : "Карта невалидна");

    }

}
