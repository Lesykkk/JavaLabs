package game;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter logWriter;

    public Logger(String logFile) {
        try {
            logWriter = new FileWriter(logFile, true); // Додаємо до файлу
        } catch (IOException e) {
            System.err.println("Не вдалося відкрити лог файл: " + e.getMessage());
        }
    }

    public void log(String message) {
        // Запис у консоль
        System.out.println(message);

        // Запис у файл
        try {
            if (logWriter != null) {
                logWriter.write(message + "\n");
                logWriter.flush(); // Примусове записування в файл
            }
        } catch (IOException e) {
            System.err.println("Не вдалося записати в лог файл: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (logWriter != null) {
                logWriter.close(); // Закриваємо writer
            }
        } catch (IOException e) {
            System.err.println("Не вдалося закрити лог файл: " + e.getMessage());
        }
    }
}
