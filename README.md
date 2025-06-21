# Natural Numbers Interpretation

This is a Java standalone application that interprets sequences of natural numbers which may have ambiguous groupings.  
The program accepts user input as a sequence of numbers separated by spaces (each number up to three digits), and generates all possible interpretations by combining these numbers in different ways.

## Features

- Handles number sequences with different groupings (up to three digits per part)  
- Identifies all possible interpretations considering ambiguities in number splitting  
- Validates the output as a Greek phone number based on specific rules  

## How to Run

1. Clone the repository  
2. Compile and run `Main.java`  
3. Enter a sequence of numbers separated by spaces when prompted  
4. View all possible interpretations along with phone number validity status  

## How it works
*Example Input*:

> 2 10 6 9 30 6 6 4

*Example Output*:

> Interpretation 1: 2106930664 [phone number: VALID]
> 
> Interpretation 2: 210693664 [phone number: INVALID]
