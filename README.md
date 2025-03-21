# DaresBoardGame

## Overview
DaresBoardGame is a Java-based desktop game built using Swing. The game involves players completing dares as they progress through a board and accumulate points. The application features a graphical user interface (GUI) with a game board rendered via the `DareGame` class.

This project was originally created 5 years ago and has been updated to resolve compilation issues by replacing the `javafx.util.Pair` dependency with a custom `Pair` class.

## Prerequisites
To run this application, you need the following:

- **Java Development Kit (JDK)**: Version 8 or later (tested with JDK 17+).
  - Download and install from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or use a package manager like `apt` (Linux), `brew` (macOS), or similar.
- **Git**: To clone the repository.
- A terminal or command-line interface to compile and run the Java application.
- A desktop environment to display the Swing GUI (this app won’t work on a headless server).

## How to Run the Application

### 1. Clone the Repository:
```bash
git clone https://github.com/JoshAtre/DaresBoardGame.git
cd DaresBoardGame
```
### 2. Navigate to the `src` Folder
```bash
cd src
```
### 3. Compile the Java Files
Compile all the .java files in the src folder:
```bash
javac *.java
```
This will generate `.class` files for each Java file. If there are no compilation errors, you're ready to run the app.
**Troubleshooting Compilation Errors**
- Ensure you have the JDK installed and `javac` is accessible in your terminal (`javac -version` should show the version).
- If you encounter errors related to missing classes, ensure all files (`Basic.java`, `Board.java`, `DareGame.java`, `Main.java`, `Pair.java`, and `RollDice.java`) are present in the `src` folder

### 4. Run the Application
```bash
java Main
```
### 5. Play the Game with a Friend!

## Notes
- **Java Version Compatibility**: The project was updated to work with modern JDK versions (e.g., JDK 21).
- **Dependencies**: The project uses Java Swing for the GUI, which is part of the standard JDK. No external libraries are required.
- **Custom Pair Class**: The Pair.java file was added to replace the javafx.util.Pair dependency, which was removed to avoid requiring JavaFX.
