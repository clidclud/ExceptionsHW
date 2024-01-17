import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите через пробел: Фамилия Имя Отчество дата рождения(Формат: dd.mm.yyyy) номер телефона(Формат: 11 целых чисел) пол(f или m)");
            String person = scanner.nextLine();
            String[] personData = person.split(" ");
            if (personData.length != 6) {
                System.out.println("Ошибка: введено неправильное количество данных");
                return;
            }

        String lastName = personData[0];
        String firstName = personData[1];
        String middleName = personData[2];
        String birthDate = personData[3];
        String phoneNumber = personData[4];
        String gender = personData[5];

            try {
                validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
                writeToUserFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateData(String lastName, String firstName, String middleName, String birthDate,
                                     String phoneNumber, String gender) {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }
        if (!phoneNumber.matches("\\d{11}")) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }
        if (!gender.equals("m") && !gender.equals("f")) {
            throw new IllegalArgumentException("Неверный формат пола");
        }
    }

    private static void writeToUserFile(String lastName, String firstName, String middleName, String birthDate,
                                        String phoneNumber, String gender) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
            String dataLine = "<" + lastName + ">" + "<" +  firstName + ">" + "<" + middleName + ">" + "<" + birthDate + ">" +"<"+ phoneNumber + ">" + "<" + gender + ">";
            writer.write(dataLine);
            writer.newLine();
            System.out.println("Запись сохранена");
        }
    }
}