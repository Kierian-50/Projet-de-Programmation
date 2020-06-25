Application Zen l'Initié

Version 1.0.0

Autors :
* Kierian Tirlemont <tirlemont.e1900238@etud.univ-ubs.fr>
* If you want to contribute to this project you're welcome.

Date :
* June 2020

Language used :

 * Java

Library/Framework :

* JUnit
* Swing (graphical interface)
* awt
* util
* io (create/deleting/rename file)
* net (find the path to access to the class)

- In which context did I this project?

I did this project as part of my studies in IT. I did as the year-end project
for my first year of study in the university of Vannes in IT.

- The goal of the project

The goal of the project is to reproduce the Zen l'Initié's game. This is a board
game and the goal of this game is to make a chain with every pawns that we own.
This is strategy game. I recommend to you to familiarize yourself with the rules
which are not really easy. The second goal of this project is to choose if the
user wants to play in graphical mode or in command line mode.

- The progress of the project

The goals of the specifications are complete. This is to say that it's possible
to play to 'Zen l'Initié' on my software in command line and in graphical mode.
In command line, the program is done, and there is nothing more to do, expect
the IA when the user when to play alone. Indeed, the IA only works in random. In
command line, it's possible to play a game or a match (several game, the winner
is the first at seven points). It's possible to save a game or a match, and to
play it later. You can activate or deactivate the option that allows to print
the possible squares from a selected pawn. And there is the tutorial which
explains the rules of the game.In graphical mode, the program is not completely
finish, besides the IA which is not done, I didn't do the match in graphical
mode. So in this mode, you can play a game, stop it, save it and continue it
later. You also can discover the game thanks to the tutorial. And you can also
activate or deactivate the option that allows to print the possible squares from
a selected pawn.
So, it was functionalities for the moment. Eventually, the goal is to have seve-
rals IA in function of the selected difficulties. And to add the match to the
graphical mode.
Finally, the game has not been tested by random users but only by me the develo-
pper. So, it's possible that the game includes errors that I didn't see. But
with the lack of time, nobody could test the software, reports me the problems
to fix it.
Be careful, because the project only works with the jar and you can't execute it
after compiling. Indeed, the path to get to the resources files is made to work
with the jar and not the root of the project. To upgrade the project do not
execute the project but generate new jar.

- The required file to execute this software

You mustn't have a special file to launch this project with the jar, but why ?
Indeed, I used text file to save a game.
At the beginning, the program will check if you've the "data" file at the level
of the file containing the jar. If this file doesn't exist, it'll create it and
if exists it will do nothing. And so, the execution of the jar is independent of
the others files. Inside the jar, you can find the resources files with the re-
quired pictures for the graphical mode.
But to launch the project, you'll need the resources files which is normally in
the jar. This is pictures that is used in graphical mode. But again, be careful,
the project only works with jar and it can't be execute due to problems with the
path and the resources files.

- Installation

In this context, this is very easy to execute the jar. I advice you to create a
file named "Zen-lInitie", and to create another file in the "Zen-lInitie"'s file
and to named it "jar". In the jar's file, you will put the jar. Now, in the
jar's file open a shell. Be careful to be situate in the jar's file for the
shell. And you just must write this command (The name of the jar is
ZenlInitie.jar):

java -jar ZenlInitie.jar

And it will create a data file in the Zen-lInitie's file.
But again, be careful, the project only works with jar and it can't be execute
due to problems with the path and the resources files. To upgrade the project do
not execute the project but generate new jar.

-- Zen-lInitie
    |-- jar
    |   `-- ZenlInitie.jar
    `-- data
        |-- saved_match0.txt
        |-- saved_game_for_match0.txt
        `-- saved_game0.txt
