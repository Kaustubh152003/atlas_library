# Atlas Library

A Java library for managing and playing the classic **Atlas Game**.

---

## Features

- **Atlas Game Engine:** Manage players, turns, and game state.
- **Place Validation:** Validate place names using a customizable list.
- **Game History:** Track moves and player actions.
- **Extensible Design:** Easily integrate with other Java applications.

---

## Project Structure

```
atlas_library/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── AtlasGame.java
│   │   │   ├── AtlasExceptions.java
│   │   │   ├── AtlasPlaceValidator.java
│   │   │   └── ... (other main classes)
│   │   └── resources/
│   │       └── ValidPlaces.txt
│   └── test/
│       └── java/
│           ├── AtlasGameTest.java
│           ├── TestRunner.java
│           └── ... (other test classes)
└── README.md
```

---
## Running Ola

1. **Run Ola:**
   ```bash
   javac -d out src/main/java/com/AtlasLibrary/*.java src/test/java/*.java src/main/java/*.java
   java -cp out Ola
   ```

---

## Running Tests

1. **Compile the code and tests:**
   ```bash
   javac -d out src/main/java/com/AtlasLibrary/*.java src/test/java/*.java
   ```

2. **Run the test runner:**
   ```bash
   javac -d out src/main/java/com/AtlasLibrary/*.java src/test/java/*.java
   java -cp out TestRunner
   ```

If all tests pass, you will see:
```
All Tests have Passed
```
If a test fails, an assertion error message will be printed.

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests.
