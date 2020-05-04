# cse237-project

# 05/04/2020

## What user stories were completed this iteration?
Many users stories are completed in this iteration, including:
- A user is able to play with a virtual computer player instead of only playing with another player in Tutorial & AI mode.
- A user is able to save and load information of many past games so that the game can be stopped and reloaded when the user wishes to.
- As a user, I can see a more well-organized interface.
- A user is able to do some interface modification(specifically, manage background color).
- A user is able to see the elapsed time from the start of the game so that they can monitor the time spent.

## Is there anything that you implemented but doesn't currently work?
- Currently, everything we implemented is working. However, the save/load feature is disabled for AI game, as we didn't find a proper way to restore an AI player based on our current AI algorithm.

## What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
- Get into repo root directory and simply use "./run.sh" shell command to start the game.

# 04/20/2020

## What user stories were completed this iteration?
In this iteration, we tentatively experiment on more advanced features including save/load game feature, tutorial feature. The save/load game feature now supports one saving slot. However, upon a decision of the UI, we can easily adapt it to a multi-save feature. We also made the UI easier to use compared to last iteration.

## What user stories do you intend to complete next iteration?
- The UI for tutorial session.
- Automated play algorithm for user to play against a computer player.
- A more intuitive navigation system, the funtionality is mostly complete, but they are not intuitive enough, for example, to exit to main page, users must click on save game, we will improve this by adding buttons to the UI.

## Is there anything that you implemented but doesn't currently work?
- We implemented the tutorial player, whose plays are fixed to teach users how to play the game. The backend logic is complete, but we need to design a tutorial choosing page, so tutorial feature is not yet available.

## What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
- Simply run the run.sh shell file using Bash or any linux cmd.

# 04/06/2020

## What user stories were completed this iteration?
The most important  user story that we completed during this iteration is a basic two human players game. It is supportted by a complete UI design and front-end implementation, and a modulized backend game coordinator.

## What user stories do you intend to complete next iteration?
- The tutorial session. It requires a new UI design for tutorial choosing page along with its frontend implementation.
- Load/save game feature. This is easy to implement. We plan to use local file to store a game and read the file into a game upon loading request.
- A complete navigation system, in which users can navigate between game page, tutorial page, entry page freely.

## Is there anything that you implemented but doesn't currently work?
- We plan to use the Controller to coordinate all the events happenning in the app. However, since some parts are not missing, it only serves a wrapper of the frontend, we will enable it in next iteration.

## What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
- Simply run the run.sh shell file using Bash or any linux cmd.
