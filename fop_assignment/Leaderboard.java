
import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String FILE_NAME = "leaderboard.txt";

    /**
     * Adds a player's score to the leaderboard file.
     * @param playerName The name of the player.
     * @param score The score to be added.
     */
    public static void addScore(String playerName, int score) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(playerName + ":" + score + "\n"); // Save name and score in proper format
        } catch (IOException e) {
            System.out.println("Error writing to leaderboard: " + e.getMessage());
        }
    }

    /**
     * Displays the leaderboard sorted by scores in descending order.
     * Validates the format of entries before processing.
     */
    public static void displayLeaderboard() {
        System.out.println("\n--- Leaderboard ---");
        List<String> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read and validate each line
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    scores.add(line);
                }
            }

            // Sort scores in descending order
            scores.sort((a, b) -> {
                int scoreA = Integer.parseInt(a.split(":")[1]);
                int scoreB = Integer.parseInt(b.split(":")[1]);
                return Integer.compare(scoreB, scoreA); // Descending order
            });

            // Print sorted leaderboard
            int rank = 1;
            for (String score : scores) {
                String[] parts = score.split(":");
                System.out.println(rank + ". " + parts[0] + " - " + parts[1]);
                rank++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Leaderboard file not found. No scores to display.");
        } catch (IOException e) {
            System.out.println("Error reading leaderboard: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Malformed entry in leaderboard. Please check the file.");
        }
    }

    /**
     * Clears the leaderboard file.
     */
    public static void clearLeaderboard() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            // Overwrites the file with an empty string
            System.out.println("Leaderboard cleared successfully.");
        } catch (IOException e) {
            System.out.println("Error clearing leaderboard: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Testing the leaderboard system
        addScore("Player1", 100);
        addScore("Player2", 200);
        addScore("Player3", 150);

        // Display the leaderboard
        displayLeaderboard();

        // Uncomment to test clearing the leaderboard
        // clearLeaderboard();
    }
}

        
    

