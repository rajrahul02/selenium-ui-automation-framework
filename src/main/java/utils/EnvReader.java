package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvReader {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    /**
     * Get value from:
     * 1. System Environment Variables (CI/CD)
     * 2. .env file (local)
     */
    public static String get(String key) {
        String value = System.getenv(key);

        if (value == null || value.isEmpty()) {
            value = dotenv.get(key);
        }

        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Missing required environment variable: " + key);
        }

        return value;
    }

    /**
     * Optional value (no exception if missing)
     */
    public static String getOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);

        if (value == null || value.isEmpty()) {
            value = dotenv.get(key);
        }

        return (value != null) ? value : defaultValue;
    }

    /**
     * Get integer values
     */
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    /**
     * Get boolean values
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}