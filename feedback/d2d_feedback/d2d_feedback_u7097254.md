D2C Feedback:

- Board.java: Lines 39-116

- Metro.java: Lines 126-131


1.0 / 1.0 - Pushed the originality statement, Git log shows healthy engagement, code compiles.

0.5 / 0.5 - Reasonable implementation of what is required.

0 / 0.5 - Quality of the work is exceptional.

Mark:

1.5 / 2

Comments:
- "int NUMBER_OF_CHAR=6;//used to decide the string contains exactly six characters"
should only use all cap variable names for global variables - make this into a global variable.
- in isPiecePlacementWellFormed you could just return wellFormed. 
- in isPlacementSequenceWellFormed you could just return "Placement.noMoreInstance(placement)", 
no need for the if/else statement
- Same goes for in isPlacementSequenceValid

- Weird indenting in Board class - please fix
- Create java docs for methods like in other classes 
- As a general rule, read yellow highlighted text - intelliJ suggests edits to improve
code logic and tidy code. 
