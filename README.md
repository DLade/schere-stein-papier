# Rock, Paper, Scissors

A Java console implementation of the classic hand game.

## Prerequisites

- **Java 21** (or compatible JDK)
- **Gradle** (included via Gradle Wrapper - no separate installation required)

## Description

This program simulates 100 rounds of Rock, Paper, Scissors between two players:
- **Player A**: Always plays Paper
- **Player B**: Randomly selects Rock, Paper, or Scissors each round

The program runs as a simple Java console application without parameters and terminates after 100 rounds. The final results (wins/losses/ties) are displayed for each player.

## Game Rules

### Basic Principle
- Two players compete against each other
- Both players simultaneously show one of three hand symbols

### The Three Hand Symbols
- **Scissors**: Index and middle finger spread apart
- **Rock**: A closed fist
- **Paper**: A flat hand with fingers together

### Who Wins?
- **Scissors** beats **Paper** (scissors cut paper)
- **Paper** beats **Rock** (paper wraps rock)
- **Rock** beats **Scissors** (rock blunts scissors)
- Same symbol: Tie → replay

![Rock-Paper-Scissors](https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Rock-paper-scissors.svg/400px-Rock-paper-scissors.svg.png)

## Getting Started

### Build the project
```bash
./gradlew build
```

### Run tests
```bash
./gradlew test
```

### Run the application
```bash
./gradlew run
```

**Windows users:** Use `gradlew.bat` instead of `./gradlew`
