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
├── main/
│   ├── java/
│   │   ├── AtlasGame.java
│   │   ├── AtlasExceptions.java
│   │   └── ... (other main classes)
│   └── resources/
│       └── ValidPlaces.txt
├── test/
│   └── java/
│       ├── AtlasGameTest.java
│       ├── TestRunner.java
│       └── ... (other test classes)
└── README.md
```

---

## Running Tests

1. **Compile the tests:**
   ```bash
   javac main/java/*.java test/java/*.java
   ```

2. **Run the test runner:**
   ```bash
   java -ea test.java.TestRunner
   ```

If all tests pass, you will see:
```
All Tests have Passed
```
If a test fails, an assertion error message will be printed.

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests.
