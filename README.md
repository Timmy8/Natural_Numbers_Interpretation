# Natural Numbers Interpretation

This is a Java standalone application that interprets sequences of natural numbers which may have ambiguous groupings.  
The program accepts user input as a sequence of numbers separated by spaces (each number up to three digits), and generates all possible interpretations by combining these numbers in different ways.

# Table of Contents

- [Features](#features)
- [How to Run](#how-to-run)
- [Example](#example)
- [Troubleshooting](#troubleshooting)


## Features

- Handles number sequences with different groupings (up to three digits per part)  
- Identifies all possible interpretations considering ambiguities in number splitting  
- Validates the output as a Greek phone number based on specific rules  

## How to Run

1. **Clone the repository or download the ZIP archive:**

```bash
git clone https://github.com/Timmy8/Natural_Numbers_Interpretation.git
cd Natural_Numbers_Interpretation
```

> **Alternatively, you can download the project as a ZIP file from GitHub and extract it on your computer.**

2. **Compile the project manually (requires Java 8 or higher) or open it in your favorite IDE:**

```bash
javac src/main/java/Assignment/Java/Omilia/Main.java
```

4. **Run the application:**

```bash
java -cp src/main/java Assignment.Java.Omilia.Main
```

5. **Enter a sequence of numbers separated by spaces when prompted:**

6. **View the results in your console**

## Example
*Example Input*:

> 2 10 6 9 30 6 6 4

*Example Output*:

> Interpretation 1: 2106930664 [phone number: VALID]
> 
> Interpretation 2: 210693664 [phone number: INVALID]

## Troubleshooting

**⚠️ Possible Errors and Input Validation**

The application performs basic input validation and reports errors to `System.err`. Please make sure to follow these rules when entering input:

- ✅ Input must be a **sequence of integers** separated by **spaces**.
- ✅ Each number must be a **natural number** from **0 to 999** (inclusive).
- ❌ Letters, symbols, and negative numbers are **not allowed**.
- ❌ Empty input or input containing only whitespace is **invalid**.
