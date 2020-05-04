D2E Feedback:
Jiawei Fan

0.5 / 0.5 - Originality statement

0.5 / 0.5 - Git log shows healthy engagement

0.5 / 0.5 - 20+ lines of new code and one + test/s that compile/s.

1 / 1 - Test is reasonable for the target code.

0.5 / 0.5 - Code is well designed and documented.

1 / 1 - Work is of an exceptionally high standard.

Mark:

4 / 4

Comments:
- Few spelling mistakes in comments (line 98 placement)
- Don't submit redundant/unused code for marking (e.g. PlayerStationAndPlacement). It should
just be your best work!
- All your code is commented and your methods all have javadocs - really wonderful to see!
- You don't need to be commenting every line - just where it would be useful. E.g commenting 
    if (track.size() > 1) {
        int index1 = track.size();//get the size of the track
        TileEntryAndExit lastTile = track.get(index1 - 1);//get the last tile
        if (isNextToCentre(lastTile)) {//check if last tile next to center station
            plusScore = index1 * 2;//scores doubles
        }
        if (isNextToEdge(lastTile)) {//check if last tile next to edge
            plusScore = index1;//score not doubled
        }
    }
you don't need to comment "plusScore = index1 * 2;//scores doubles", as I can see what you're doing
directly from your code. Too many comments can clutter code!
- I also would reccomend not submitting classes that are mostly "boiler plate" code - code where you're
just typing out data. This code is very useful, but doen't really demonstrait any skill. E.g. Station and
StationNumber. 
- It would be good if you add a comment above each test just saying what it's testing for. 
