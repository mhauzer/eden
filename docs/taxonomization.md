# Taxonomization algorithm

Rules:
- extract groupings by quale variance
- for each grouping:
    - recognize words:
        - recognize letters
        - match letter groupings to a dictionary and infere word ideas
    
```
H   E   J   !
Q1, Q2, Q3, Q4
```

Character table (dictionary)

| character | volume | density | taxonomy |
|---|---|---|---|
| a | low | medium | character - letter - lower-case-letter - lower-case-letter-a
| A | high | high | character - letter - capital-letter - capital-case-letter-A
| 0 | high | high | character - digit - digit-0
| 9 | high | high | character - digit - digit-9
|   | none | none | character - space
| . | low | low | character - punctuation-mark - dot
| , | low | low | character - punctuation-mark - coma
| : | low | low | character - punctuation-mark - colon
| ; | low | low | character - punctuation-mark - semicolon
| - | low | low |
| + | low | low |
| ! | high | medium |
| ? | medium | medium |



# Ideas for further exploration

`a-z` is a different sub-dictionary than `A-Z` and this can impact the variance
It can be even more useful when you conside `a-z` vs. `!?`

sub-dictionary = predefined set

predefined sets is a higher order notion, so it can impact taxonomization on some higher level