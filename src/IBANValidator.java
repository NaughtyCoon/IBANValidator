import java.math.BigInteger;

/**
 * @author Антон Городсков, группа Java412
 * Класс предназначен для проверки IBAN-номера на валидность
 */
public class IBANValidator {
    private static final int[] countryCodeLength = {22, 27, 22, 27, 24, 28, 18, 16, 21, 24};
    private static final String[] countryCode = {"DE", "FR", "GB", "IT", "ES", "PL", "NL", "BE", "CH", "SE"};

    /**
     * Метод реализует проверку строки на соответствие стандарту № 13616 Международной организации по стандартизации
     * ИСО и Европейского комитета по банковским стандартам ECBS, European Committee for Banking Standard.
     * @param a исходная строчная переменная, содержащая неочищенный IBAN-номер, передаваемый вызывающим методом.
     * @return будет возвращено значение true, если номер валиден, в противном случае - false.
     */
    public boolean isValid(String a) {

        String cleanedInput = a.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        String countryPart = cleanedInput.substring(0, 2);

        int countryIndex = -1;  // Инициализируем индекс словаря, в котором будем искать
                                // совпадение с кодом страны и брать соотв стране длину IBAN-номера

        for (int i=0; i<countryCode.length; i++) {

            if (countryCode[i].equals(countryPart)) {

                countryIndex = i;
                break;

            }

        }

        if (countryIndex == -1) return false;   // Если countryIndex не изменился, значит совпадения с кодом страны
                                                // из словаря не обнаружено и строка невалидна.

        if (countryCodeLength[countryIndex] != cleanedInput.length()) return false;

        char[] letter = (cleanedInput.substring(4) + cleanedInput.substring(0, 4)).toCharArray();

        String finalString = "";    // Инициализация переменной, в которой сохраним итоговую строку,
                                    // готовую к преобразованию в BigInteger

        for (int i=0; i<cleanedInput.length(); i++) {

            if (letter[i] >= 'A' && letter[i] <= 'Z') {
                finalString += letter[i] - 'A' + 10;
            } else {
                finalString += Character.toString(letter[i]);
            }

        }

        BigInteger finalNumber = new BigInteger(finalString);

        return finalNumber.remainder(BigInteger.valueOf(97)).equals(BigInteger.valueOf(1));

    }

}
