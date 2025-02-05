package express;

import java.util.Random;

public class Assets {
    private static final String UPPER_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "123456789";
    private static final String ALPHA_NUMERIC = UPPER_ALPHABET + LOWER_ALPHABET + NUMBERS;

    public static String getRandomString(int length) {
        return generate(ALPHA_NUMERIC, length);
    }

    private static String generate(String alphaNumeric, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            sb.append(alphaNumeric.charAt(index));
        }
        return sb.toString();
    }

    // Метод для 240 символов
    public static String generateString240() {
        return getRandomString(240);
    }

    // Метод для 250 символов
    public static String generateString250() {
        return getRandomString(250);
    }
    public enum ValidationMessage {
        INVALID_CHARACTER("/"),
        FILE_NAME_ERROR("Имя файла не должно содержать специальные символы: /"),
        FOLDER_NAME_ERROR("Имя папки не должно содержать специальные символы: /"),
        EMPTY_FOLDER_NAME_ERROR("Имя папки не может быть пустым"),
        INVALID_FOLDER_NAME("Недопустимое имя папки"),
        INVALID_FILE_NAME("Недопустимое имя файла"),
        DUPLICATE_FOLDER_ERROR("Папка с таким именем уже существует"),
        DUPLICATE_FILE_ERROR("Файл с таким именем уже существует"),
        DOT_FILE_ERROR("..");

        private final String message;

        ValidationMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum FileType {
        DOCX(".docx"),
        XLSX(".xlsx"),
        TXT(".txt");

        private final String extension;

        FileType(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }

        @Override
        public String toString() {
            return extension; // Теперь toString() вернет ".docx", ".xlsx", и т.д.
        }
    }
}
