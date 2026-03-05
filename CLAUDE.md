# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java implementation of "Schere-Stein-Papier" (Rock-Paper-Scissors game). The game rules are documented in README.md:
- Schere (Scissors) beats Papier (Paper)
- Papier (Paper) beats Stein (Rock)
- Stein (Rock) beats Schere (Scissors)

## Project Structure

This is a new Java project. The .gitignore is configured for standard Java artifacts (*.class, *.jar, *.war, etc.).

## Development Workflow

**The user writes the production code. Claude only writes tests.**

## Test Guidelines

### Test Style: BDD (Behavior-Driven Development)

### Parametrization
- Tests are parametrized when testing multiple scenarios
- If there is only one parameter, the test is considered NOT parametrized

### Test Coverage Requirements
- At least one good-case test (valid/happy path)
- All edge cases must be tested

### Test Naming Convention
Format: `should<Action>With<Valid|Invalid>Parameters`

Examples:
- `shouldThrowExceptionWithInvalidParameters`
- `shouldReturnWinWithValidParameters`
- `shouldReturnDrawWithIdenticalMoves`

### Test Framework
- **JUnit 5 (Jupiter)** for test execution
- **AssertJ 3.x** for fluent assertions
- Parametrized tests using `@ParameterizedTest` with `@MethodSource`
- **Use `@MethodSource` with `Arguments.of(ENUM1, ENUM2)` for enum parameters** - NOT `@CsvSource`
- Static imports for better readability (Move.*, Result.*)
- Group related test data in static methods (e.g., `winningMoves()`, `invalidMoves()`)
- Use **fluent assertions** (AssertJ) instead of classic JUnit assertions
