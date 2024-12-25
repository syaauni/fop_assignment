

import java.io.*;

public class ScoringSystem {
    private int score;
    private int level;
    private int size;
    private int totalFishEaten;
    private static final int[] levelThresholds = {0, 50, 150, 300}; // Level thresholds

    public ScoringSystem() { 
        this.score = 0;  // Initial score
        this.level = 1;  // Initial level
        this.size = 3;   // Initial size of the fish
        this.totalFishEaten = 0; // Initial count of fish eaten
    }

    /**
     * Updates the score based on the type of fish eaten.
     * @param typeOfFish The type of fish eaten ("small", "medium", or "large").
     */
    public void scoreUpdate(String typeOfFish) {
        int points = 0;

        // Determine points based on the type of fish
        switch (typeOfFish.toLowerCase()) {
            case "small":
                points = 3;
                break;
            case "medium":
                points = 5;
                break;
            case "large":
                points = 7;
                break;
            default:
                return; // No action for unknown fish types
        }

        // Update score and total fish eaten
        score += points;
        totalFishEaten++;
        System.out.println(typeOfFish + " fish eaten! Points: " + points);
        System.out.println("Total Score: " + score);

        // Check for level progression and size upgrade
        checkLevelUp();
        checkSizeUpgrade();
    }

    /**
     * Resets the scoring system to its initial state.
     */
    public void reset() {
        this.score = 0;
        this.level = 1;
        this.size = 3;
        this.totalFishEaten = 0;
        System.out.println("Scoring system reset.");
    }

    /**
     * Checks if the player should level up based on their score.
     */
    private void checkLevelUp() {
        if (level < levelThresholds.length && score >= levelThresholds[level]) {
            level++;
            System.out.println("Level Up! You are now at level " + level);
        }
    }

    /**
     * Checks if the fish size should increase based on the level.
     */
    private void checkSizeUpgrade() {
        if (level == 2) {
            size = 4; // Medium fish
        } else if (level == 3) {
            size = 5; // Large fish
        }
    }

    // Getters for score, level, and size
    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getSize() {
        return size;
    }

    /**
     * Saves the game statistics to a file.
     * @param fileName The name of the file to save the stats.
     */
    public void saveGameStats(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Score: " + score + "\n");
            writer.write("Level: " + level + "\n");
            writer.write("Size: " + size + "\n");
            writer.write("Total Fish Eaten: " + totalFishEaten + "\n");
            System.out.println("Game stats saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game stats: " + e.getMessage());
        }
    }

    /**
     * Loads the game statistics from a file.
     * @param fileName The name of the file to load the stats from.
     */
    public void loadGameStats(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    switch (parts[0].toLowerCase()) {
                        case "score":
                            score = Integer.parseInt(parts[1]);
                            break;
                        case "level":
                            level = Integer.parseInt(parts[1]);
                            break;
                        case "size":
                            size = Integer.parseInt(parts[1]);
                            break;
                        case "total fish eaten":
                            totalFishEaten = Integer.parseInt(parts[1]);
                            break;
                    }
                }
            }
            System.out.println("Game stats loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading game stats: " + e.getMessage());
        }
    }
}

