D2C Feedback:

Code:
-   Placement Class; Lines 82-142
-   Station Class; Lines 40-74
-   Deck Class; Lines 7-146 (rewrite logic for previous tasks according to the D2C feedback and some new thoughts,e.g., all tiles have the same probabilities to be selected)
-   Metro Class; Lines 81-104 (rewrite logic for previous tasks according to the D2C feedback and some new thoughts)
-   Placement Class; Lines 12-80 (rewrite logic for previous tasks according to the D2C feedback and some new thoughts)
(List at least 10, and include line numbers.)



1.0 / 1.0 - Pushed the originality statement, Git log shows healthy engagement, code compiles.

0.5 / 0.5 - Reasonable implementation of what is required.

0.25 / 0.5 - Quality of the work is exceptional.

Mark:

1.75 / 2

Comments:
- Fill out all the param descriptions (really nice to java docs being used)
- You can delete lines 51 and 57 in Station, have a look a the warning messages. 
- Could tidy "getInitialDeck" by using another method with a for loop, and passing in 
the tile sting and the number of times to be added. 
- in "drawFromCurrentDeck" you can just return currentDeck.get(index)
- Cut out some "magic numbers" e.g. using 6 in code like placement.substring(6*i,6*i+6);
could create a global variable so it's clear where that number is coming from. 