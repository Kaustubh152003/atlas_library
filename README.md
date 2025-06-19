# Atlas Library

A Java library for managing and playing the classic **Atlas Game**.

---

## Features

- **Atlas Game Engine:** Manage players, turns, and game state.
- **Place Validation:** Validate place names using a customizable list.
- **Game History:** Track moves and player actions.
- **Extensible Design:** Easily integrate with other Java applications.

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/atlas_library.git
   ```

---

## Running Tests

To run the custom test suite:

1. **Compile the tests:**
   ```bash
   javac .\test\TestRunner.java
   ```

2. **Run the test runner:**
   ```bash
   java -ea test.TestRunner
   ```

If all tests pass, you will see:
```
All Tests have Passed
```
If a test fails, an assertion error message will be printed.

---

## Project Structure

```
atlas_library/
│
├── main/
│   ├── AtlasGame.java
│   ├── AtlasPlaceValidator.java
│   ├── AtlasGameHistory.java
│   ├── Ola.java
│   └── ...
├── test/
│   ├── AtlasPlaceValidatorTest.java
│   └── TestRunner.java
└── README.md
```

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests.
