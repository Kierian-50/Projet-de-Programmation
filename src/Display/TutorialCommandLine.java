package Display;

import gameElement.Game;
import gameElement.Mode;
import utility.RWFile;

import java.util.Scanner;

/**
 * This class is used to create the tutorial.
 * @author Kierian Tirlemont
 */
class TutorialCommandLine {

    /**
     * The scanner.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * The main method for the creation of the tutorial.
     */
    static void tutorial(){
        /*
        Intro
         */
        System.out.println("Hello, I'm the professor Oak, known in France as the professor \"Chen\". May be you already");
        System.out.println("know me because I was the professor in Pokemon. But I left Pokemon for a better game : Zen");
        System.out.println("l'Initie.\nIn this part, I will teach you how to play this amazing game which is Zen l'Initie.");
        System.out.println("Listen carefully to what I'm going to teach you, it could be useful when you will be in a duel.");
        System.out.println("The presentation made. Let's start !");
        System.out.println("-------------------------------------\\    /----------------------------------------------------");
        System.out.println("                                      \\  / ");
        System.out.println("                                       \\/");
        System.out.println(acsiiArtProfAok(2));
        System.out.println("Press a key to continue");
        sc.nextLine();

        /*
        Explication movement
         */
        System.out.println("First of all, you have to see what the game boards looks like :");
        Game gamePrintNormalGrid = new Game("Apprentice","Professor", Mode.HA,true);
        gamePrintNormalGrid.displayBoard();
        System.out.println("This is on this 11 by 11 square board that the duel take place. You see that there is two teams:");
        System.out.println("the green circle and the blue cross. You see in the center that there is special red pawn, I");
        System.out.println("speak about it later. The goal of the game is to align all your pawns before your opponent. ");
        System.out.println("Now, we will answer to the question \"How can this pawns be move? What is the rules ?\"");
        System.out.println("The pawns can move in every direction : horizontal, vertical and diagonal. To know the number");
        System.out.println("of squares that you can pass, you must count the number of pawn on a axe and it will the size ");
        System.out.println("of your movement in Square. But if there are an enemy pawn on the path you can't jump over : the");
        System.out.println("movement is forbidden. But if the enemy pawn is an the final square you can capture his enemy pawn.");
        System.out.println("And you can jump over your pawns and the zen pawn.");
        System.out.println("---------------------------------------------\\    /-----------------------------------------------");
        System.out.println("                                              \\  / ");
        System.out.println("                                               \\/");
        System.out.println(acsiiArtProfAok(0));
        System.out.println("\nPress a key to continue");
        sc.nextLine();
        gamePrintNormalGrid.displayBoard();
        System.out.println("As an example is worth as a thousand words, the pawn in (0,5) can move in two directions.");
        System.out.println("First, it can move in (0,8) because there is three pawns on this axe and also (0,2) for the");
        System.out.println("same reason. And it can move in (3,5) because there is three pawns on this axe. But it can't");
        System.out.println("move diagonally because the enemy pawns avoid this movement.");
        System.out.println("\nPress a key to continue");
        sc.nextLine();
        System.out.println("\nTry to move the pawn in (0;5) and move it in (0,8) :\n");
        System.out.println("Please enter the x position of the pawn that you want to move");
        askMovement();
        gamePrintNormalGrid.move(0,8,0,5);
        gamePrintNormalGrid.displayBoard();
        System.out.println("\nPress a key to continue");
        sc.nextLine();
        /*
        Zen
         */
        System.out.println("It was a really nice movement ! But your learning is not finish... Be patient... This is very ");
        System.out.println("important in this game.");
        System.out.println("Now, we will speak about the red pawn : the zen. This is a special pawn. The zen pawn is ");
        System.out.println("common to the two players : it can be move by you or your opponent. You can consider it as");
        System.out.println("your friend or your enemy, this is to say that you can capture it with your pawns. But the zen");
        System.out.println("respects specials rules. First, when a player moves it, his opponent can't replace it at his old");
        System.out.println("square. Secondly, it's forbidden to move it, if the zen pawn is not in touch with a pawn");
        System.out.println("(whatever if it's one of your pawns or one of the pawns of your opponent).");
        System.out.println("---------------------------------------------\\    /-----------------------------------------------");
        System.out.println("                                              \\  / ");
        System.out.println("                                               \\/");
        System.out.println(acsiiArtProfAok(1));
        System.out.println("\nPress a key to continue");
        sc.nextLine();
        /*
        Victory
         */
        System.out.println("\nNow, we will see how to finish a game and win. It's really easy : to win, you must align");
        System.out.println("all your pawns before your opponent. To align, your pawn, you can capture your enemy pawns.");
        System.out.println("But if the zen is always on the board, you must include the zen to your chain else you will");
        System.out.println("your chain will not be valid.");
        System.out.println("And there are two case, we're the game end by a draw between the player : ");
        System.out.println("- First, when you move the zen pawn and it align and finish your opponent's chain");
        System.out.println("- Secondly, when you capture the only pawn of your opponent which is not in the chain, the");
        System.out.println("game end by a draw whereas the opponent's win.");
        System.out.println("To finish before the exercise, you can play a single game but you can also play a match in");
        System.out.println("several sleeves. At each sleeve, we count the number of pawn of the looser which is not link");
        System.out.println("to the main chain and add it to the score of the winner. The first at seven points is the winner.");
        System.out.println("---------------------------------------------\\    /---------------------------------------------");
        System.out.println("                                              \\  / ");
        System.out.println("                                               \\/");
        System.out.println(acsiiArtProfAok(0));
        System.out.println("\nPress a key to continue");
        sc.nextLine();
        System.out.println("We talked a lot, now, make way for a short exercise\n");

        /*
        Creation of the example
         */
        RWFile.createFile(Technical.findDataPath()+"tutorial.txt");
        int[] zenPosition = new int[2];
        zenPosition[0] = 2;
        zenPosition[1] = 2;
        Game gameDemonstration = new Game("Apprentice","bot",Mode.HA,true,
                createExample("Apprentice"), createExample("bot"), zenPosition, "data/tutorial.txt");

        gameDemonstration.displayBoard();
        System.out.println("I just start a game for you now try to finish, I will give you few advices, don't worry.");
        System.out.println("First, you see that only two of your pawns are not link to your chain. So, you must move");
        System.out.println("this two pawns. We will begin with the (10,3) pawn. In the line y=3, you see that there is");
        System.out.println("five pawns, so you can move five squares from the pawn. You see that you will arrive in (5;3).");
        System.out.println("Perfect ! This pawn will be link to the others pawn.\nTry to move it in the correct square.");
        askMovement();
        gameDemonstration.move(5,3,10,3);
        gameDemonstration.changeCurrent();
        gameDemonstration.move(10,6,10,8);
        gameDemonstration.changeCurrent();
        System.out.println("Congrats, well move ! Now, only one move and you will win.");
        System.out.println("In diagonal, we count three pawns. Interesting because to join the others pawns the size of");
        System.out.println("the movement must be three from the pawn. So, you must move the pawn in (7;10) to the square");
        System.out.println("(4;7)");
        askMovement();
        gameDemonstration.move(4,7,7,10);
        gameDemonstration.displayBoard();
        System.out.print("\nThe winner of the game is the apprentice");

        System.out.println("\n\n"+acsiiArtProfAok(4));
        System.out.println("\nCongratulation ! You were a very good student and now you're ready to play this amazing ");
        System.out.println("game. Before you leaves, I have two things to tell you. First, if one day the game is too ");
        System.out.println("easy or you want to play with more difficulties in the option submenu you can deactivate");
        System.out.println("the option that allows to print the possibles squares when you select a pawn.");
        System.out.println("-------------------------------------\\    /----------------------------------------------------");
        System.out.println("                                      \\  / ");
        System.out.println("                                       \\/");
        System.out.println(acsiiArtProfAok(2));

        System.out.println("\nPress a key to continue");
        sc.nextLine();

        System.out.println("Second, I hope you found this lesson helpful. I wish you the best for your futures games");
        System.out.println("Oh, I almost forgot! To put all the chances on your side, I offer you this freshly picked " +
                "\nfour-leaf clover\n");
        System.out.println(acsiiArtProfAok(5));

        System.out.println("\nPress a key to return to the play's submenu");
        sc.nextLine();

        if(RWFile.isFile(Technical.findDataPath()+"tutorial.txt")){
            RWFile.deleteFile(Technical.findDataPath()+"tutorial.txt");
        }
    }

    /**
     * This method allows to ask to the user to write certain position.
     * @return The value enter by the user.
     */
    private static int[] askMovement(){
        int[] ret = new int[4];

        ret[0] = -1;
        ret[1] = -1;
        ret[2] = -1;
        ret[3] = -1;

        while(ret[0]<0 || ret[0]>= 11){
            System.out.println("Please enter the x position of the pawn that you want to move");
            String xPositionPawn = sc.nextLine();
            if(xPositionPawn == null){
                System.exit(0);
            }
            try {

                ret[0] = Integer.parseInt(xPositionPawn);
                if(ret[0]<0 || ret[0]>= 11){
                    throw new NumberFormatException("chooseTheMovementConsole : The value "+xPositionPawn+" entered is not correct");
                }

            }catch(NumberFormatException e){
                System.out.println("Please enter a correct number between 0 and 10 !");
            }
        }

        while(ret[1]<0 || ret[1]>= 11){
            System.out.println("Please enter the y position of the pawn that you want to move");
            String yPositionPawn = sc.nextLine();
            if(yPositionPawn == null){
                System.exit(0);
            }
            try {

                ret[1] = Integer.parseInt(yPositionPawn);
                if(ret[1]<0 || ret[1]>= 11){
                    throw new NumberFormatException("chooseTheMovementConsole : The value "+yPositionPawn+" entered is not correct");
                }

            }catch(NumberFormatException e){
                System.out.println("Please enter a correct number between 0 and 10 !");
            }
        }

        while(ret[2]<0 || ret[2]>= 11){
            System.out.println("Please enter the x position of the final position of the pawn");
            String xPositionFinal = sc.nextLine();
            if(xPositionFinal == null){
                System.exit(0);
            }
            try {

                ret[2] = Integer.parseInt(xPositionFinal);
                if(ret[2]<0 || ret[2]>= 11){
                    throw new NumberFormatException("chooseTheMovementConsole : The value "+xPositionFinal+" entered is not correct");
                }

            }catch(NumberFormatException e){
                System.out.println("Please enter a correct number between 0 and 10 !");
            }
        }

        while(ret[3]<0 || ret[3]>= 11){
            System.out.println("Please enter the y position of the final position of the pawn");
            String yPositionFinal = sc.nextLine();
            if(yPositionFinal == null){
                System.exit(0);
            }
            try {

                ret[3] = Integer.parseInt(yPositionFinal);
                if(ret[3]<0 || ret[3]>= 11){
                    throw new NumberFormatException("chooseTheMovementConsole : The value "+yPositionFinal+" entered is not correct");
                }

            }catch(NumberFormatException e){
                System.out.println("Please enter a correct number between 0 and 10 !");
            }
        }

        return ret;
    }


    /**
     * This method references every ascii art that I use in the tutorial.
     * @param i The reference of the ascii art that you want to print.
     * @return The selected ascii art.
     */
    private static String acsiiArtProfAok(int i){
        String ret = null;

        switch (i){
            case 0:
                /*
                Explaining
                 */
                ret =   "                     *                                                          \n" +
                        "                      *                      *******                            \n" +
                        "                       *                    (**,,,&,,,#*                        \n" +
                        "                        *                   #, &,,,&&(*                         \n" +
                        "                         *                  ,,,,,,,(.%/                         \n" +
                        "                          *,%                ,,////&,/                          \n" +
                        "                         %,%,,,           % #&(##,(&%&                          \n" +
                        "                          *&//,,          ####,,/%%###                          \n" +
                        "                            //.  ,     (   *######.                             \n" +
                        "                           %#         &     ######   (                          \n" +
                        "                             (      &      %#####(                              \n" +
                        "                               .      &  # ######           %                   \n" +
                        "                                  & . ,   .######        *                      \n" +
                        "                                          %%%%%%%                               \n" +
                        "                                          **&&(#%                               \n" +
                        "                                          ***#***         .*                    \n" +
                        "                                     * *  *******  (    ,%                      \n" +
                        "                                       *  *((#&**                               \n" +
                        "                                       * /***(***                               \n" +
                        "                                         #*******                               \n" +
                        "                                    #    (*******                               \n" +
                        "                                     (#,  *******        *(                     \n" +
                        "                                        ******(*******                          \n" +
                        "                                        ******&*******                          \n" +
                        "                                        ******(*******                          \n" +
                        "                                        *****(((******@                         \n" +
                        "                                       %%%%%%&&/%%%%%%,                         \n" +
                        "                                     %%%%%%.@&%&%%%%%%%                         \n" +
                        "                                                          ";

                break;

            case 1 :
                /*
                explaining
                 */
                ret =   "                                               */                               \n" +
                        "                                           /************,                       \n" +
                        "                                            ((,,,/(/,,,**                       \n" +
                        "                                            ((,(%,,#%#*(#                       \n" +
                        "                                             ,/,*,,,%/,//                       \n" +
                        "                                              ,,(////*,                         \n" +
                        "                                               ,,,/,,/%,,                       \n" +
                        "                                         (.../.%#%/%%%%,,..../                  \n" +
                        "                                        ......,#######%..*...,..                \n" +
                        "                                       *,......#######......,....               \n" +
                        "                                       .......,#######....,.......              \n" +
                        "                                       .......#######(.....*.......             \n" +
                        "                                     (*.....*.#######..**/(.,.......,           \n" +
                        "                           /,,,,,#......(.....#######./......,.......           \n" +
                        "                           *,,,,,,*/,,,,(....,#######.......*.......            \n" +
                        "                           ,*,,,,/%*#   .....##%#####............,.             \n" +
                        "                                        .....#**(//**,...,,,,,/(.               \n" +
                        "                                        .....,**%****(../,*.....                \n" +
                        "                                       ,......*#*****/..........,               \n" +
                        "                                       /...../**((****...#......(               \n" +
                        "                                       ......#**(##***...,.......               \n" +
                        "                                       ...*..#***( ***(.........(               \n" +
                        "                                       ,.....#***( ****..........               \n" +
                        "                                       ......#***( (***..........               \n" +
                        "                                       ,.....#****  ****(((((                   \n" +
                        "                                          ********/ /******/(,                  \n" +
                        "                                          *******/   ******((#                  \n" +
                        "                                          *******(   *****(#(,                  \n" +
                        "                                          *******(   .**/***(,                  \n" +
                        "                                         %%%%%%%&*    %%%%%%&&                  \n" +
                        "                                      %%%%%%%%%&&(    %%%%%%%%%%& ";
                break;

            case 2:
                /*
                Salutation
                 */
                ret =   "                                                                                \n" +
                        "                                   (        %&/                                 \n" +
                        "                               .                       (.                       \n" +
                        "                                ,,,...              ...,,,                      \n" +
                        "                                /(**#       **,.    ...,**                      \n" +
                        "                                %***#       ,*      ...(**                      \n" +
                        "                                 .** &%%&.        &%%&.//&                      \n" +
                        "                                 ..(     &&/   &&#* (...#                       \n" +
                        "                                  ,, ,(  &       *#  */.(. *                    \n" +
                        "                                  //                 ..,.(,                     \n" +
                        "                                   /                ...,(                       \n" +
                        "      .                              ..   /*******,,...                         \n" +
                        "  ( %  , (                              (..,##(/..,#                            \n" +
                        "  * #  .  *                          # (,*#,,,,,***(.#                          \n" +
                        ", # #  #  #                       %   *,,,%/,&******%../...%/                   \n" +
                        "/ * /  (    #  #                 *   *%,,/**@**/****(...#.....     #            \n" +
                        "  * *  *   *  /                %     /*,(**(****#**#*....*...       .           \n" +
                        " .  /  .     #                   .# (,,#,,,*,****%%*.,,...         ../          \n" +
                        " *    /     ,                 #     ,,,,,,,/,,,,,,,#     ,        .... %        \n" +
                        " %    #    .  *  *            (    #,,,,,,,,,,,,,,,*     *       *.....,        \n" +
                        "  &&.....*,#     .   .   .  . (    (,,,,,,,,,,,,,,,     (       ,........       \n" +
                        "  %....*,,*.  *   ...    .    .   .,,,,,,,,,,,,,,,(    *        %..........     \n" +
                        "    .....##......  &..     .   ,  #,,,,,,,,,,,,,,,/   /         *..........#    \n" +
                        "   /.....%..................   #  ,,,,,,,,,,,,,,,,.  /         ..............%  \n" +
                        "    *../...........,..**...... . (,,,,,,,,,,,,,,,, ,@&%%%%(((((((((((&#&....   ,\n" +
                        "      .  ..........(..%.........(%*,,,,,,,,,,,,,,# ..&%%#*,,,,,,#/,*,*(&/%/#.   \n" +
                        "        * ............*.........,(/,,,,,,,,,,,,,,%../%%%#(/******(#%%&@@,.......\n" +
                        "           #........( .......... //*,,,,(,,,,,,,,%.*&##&&&&%#(////////#(.......*\n" +
                        "                      ...........///,,,,,,,,,,,,,/../@/#&&##%%(/**,,,,/(&.....% \n" +
                        "                     ...........,#&@&#(/#******(%/.#&//,*.*,,/*******###.....%  \n" +
                        "                     *...........#%&@#......,&&&%#.#&*/ .  .  %***&&/#@&%...%   \n" +
                        "                     (..........,###(((%((%((((((%..#*..#..,...*#..*,,,   .(    \n" +
                        "                     #&(&.......,##(#((%((#((((((%...%.,.@..,,.....,#.%  .      \n" +
                        "                        .#......*#(((((&(((((((((#.......*(((%@%%@..../ .       \n" +
                        "                    * .  (......,#((((((((((((((#(...........,,,,...*           \n" +
                        "                    /    *.......((((((#&((#((((((/..................           \n" +
                        "                    .    ,.......(((((((#&#&#((((#&......../.........           \n" +
                        "                        .,.......(((#(((##%%##((((#........(... .....           \n" +
                        "                    ,  ..(.......%(((((((##&#((((((......../.      .,           \n" +
                        "                    # .../   ....#(((((((((#&(((((((.......*        .           \n" +
                        "                     ... (    ...#&((((((((/*#(((((#.....           ,           \n" +
                        "                     /   ,     ..#(((((((((**#((((((*..             #           \n" +
                        "                     *           %(%((((((%***((((%(%.              .           \n" +
                        "                                 %((%(((((#***%((((//               .           \n" +
                        "                      (          #((((((((/*,,,(/(#**(                          \n" +
                        "                       .         /((((((((     **/#**(             *            \n" +
                        "                        &****///((((((((((      #*(******//((((((((             \n" +
                        "                        .****#//(((((((((#       */*//*//**((((((((             \n" +
                        "                         (/////((((((((((#       #**///****/(((((((             \n" +
                        "                         ///(((((((((((((#        */**////*/(((((#(             \n" +
                        "                        ,**%(((((((((((((#        (/***////((((((((             \n" +
                        "                         ((////((%((((((((         (/*////((((((((&             \n" +
                        "                         ,///***//#((((((*         #(////((((((((((             \n" +
                        "                         //////***//(((((,         #((//#/////(((((,            \n" +
                        "                         (%%%%@%***//((((          /(((#//////*/(((%            \n" +
                        "                    (#(((((((##%%%%%%%%%%          /((///////***/(((            \n" +
                        "                  &(&#(#%@#(###(#%%%@%%%&           &@%%%&#((((((((%            \n" +
                        "                  &#(((((((###@%%&(                  ##(((#&(((((((#@%#         \n" +
                        "                    /@&&&&&&.                        %%#((((((((((#%%%%.        \n" +
                        "                                                        &%%@&####%%%%&%         \n";

                break;

            case 3:
                /*
                Angry
                 */
                ret =   "                              .                                                 \n" +
                        "                           ,(#(,.,%                                             \n" +
                        "                          .*#,*,,,*,,,*,,,*//                                   \n" +
                        "                        @,*,,,,.,,*,,,,,,,,,,,/.                                \n" +
                        "                        &&,,,(.....(,#.....*%,#/                                \n" +
                        "                         (,* *............./*#((/               .,(%%&%%(       \n" +
                        "                           #**@&#........(@&*%##*    *#######%%&,,,,,,.%&       \n" +
                        "                          ,. /.**%@(.((&#/ #*(%,%   @ &%%%%#%#%%,,*,,,,&%.      \n" +
                        "                          %.(/%#(#.....,.  #*/#*,,   */%%##%%%&%&,,,,,,(##      \n" +
                        "                            (,.....,@.......*##/     & &%##%#%%%./,,/,..&&      \n" +
                        "                            ,..............,*#       *./#%(##%..@.,#*.&%&%.     \n" +
                        "                               ,/..,*/*..*&.          @,#%%(.%..,..,./          \n" +
                        "                             # #(..&@@%%**@&%.              %..*****%           \n" +
                        "                      .(.   @#(##........%(##,    //.      ,..**/*/             \n" +
                        "                  ( %      (@(/*@%(/...#(#%#(( *        /,  //.      *          \n" +
                        "                 (  .     .     &(##(#(((((    .          ./#*       *          \n" +
                        "                &   ,     &    (@(##((#(((*    /           /         *          \n" +
                        "               .  . .       &   %(((((((((   #*            /*        .          \n" +
                        "               #   /.    (      #(#(((((((       .      .  /          .         \n" +
                        "              .    *.    /      #((#((((((      /....*  ,  (          #         \n" +
                        "              *    .      @    .((((((((((     /     ,  ( %(     .....#         \n" +
                        "             (      ,      /   ,((((((((((    @      .  &  *   .......*         \n" +
                        "            /       /      .   .((((((((((   /          % #   .......(          \n" +
                        "                   .#       #  *((((((((((  *        ,  #    /...*((            \n" +
                        "           *   .,..          * *#(#(#((##%  .           /                       \n" +
                        "            (......*         * /&&&%&&&&&& #            ,                       \n" +
                        "              ,*.../          %/&&&&&&&&&&              *                       \n" +
                        "                  %*           @&@&@&&&&&&/             ,                       \n" +
                        "                   *           &##(%%###(/*             /                       \n" +
                        "                   (           #****%*****/             /                       \n" +
                        "                   #       ,   (****%*****.   .***.,.,*,(                       \n" +
                        "                   /       *   (****%*****.   (         #                       \n" +
                        "                   .       ,   (****%*****    (         #                       \n" +
                        "                  .        .   (*/(%(#****    *         &                       \n" +
                        "                  %       /    (*/(%*(***(    *         %                       \n" +
                        "                  &       @    (*/(%*****#    #         /                       \n" +
                        "                  (,,,,,,*%    (*((%/*%**#    %         ,                       \n" +
                        "                  .            (*((%**%**#                                      \n" +
                        "                  .            /*((%**%**#                                      \n" +
                        "                  .            /*((%**&**/                                      \n" +
                        "                     /**********(((#  &***/**********(                          \n" +
                        "                     /**********(((#  @**************/                          \n" +
                        "                     /***********(#(  &**************/                          \n" +
                        "                     ************((#  ***************,                          \n" +
                        "                     /***********(((  ***************                           \n" +
                        "                     **********((((/  &*************/                           \n" +
                        "                     (******((&((((/  @*************%                           \n" +
                        "                    %****/(((((#((%   @*****/((((/**%                           \n" +
                        "                     @%%%%&%&&%((((   &*****/%&&%&&&/                           \n" +
                        "                   &%%%%%%%%%&&&@@     #%%%%%%%%%%%%%&                          \n" +
                        "                @%%%%%%%%%%&&&@@@@     &@&&%%%&%%%%%%%%%/                       ";


            case 4:
                /*
                Congrats
                 */
                ret = "                                ,.        ,.      ,.\n" +
                        "                                ||        ||      ||  ()\n" +
                        " ,--. ,-. ,.,-.  ,--.,.,-. ,-.  ||-.,.  ,.|| ,-.  ||-.,. ,-. ,.,-.  ,--.\n" +
                        "//`-'//-\\\\||/|| //-||||/`'//-\\\\ ||-'||  ||||//-\\\\ ||-'||//-\\\\||/|| ((`-'\n" +
                        "||   || |||| ||||  ||||   || || ||  || /|||||| || ||  |||| |||| ||  ``.\n" +
                        "\\\\,-.\\\\-//|| || \\\\-||||   \\\\-|| ||  ||//||||\\\\-|| ||  ||\\\\-//|| || ,-.))\n" +
                        " `--' `-' `' `'  `-,|`'    `-^-``'  `-' `'`' `-^-``'  `' `-' `' `' `--'\n" +
                        "                  //           .--------.\n" +
                        "              ,-.//          .: : :  :___`.\n" +
                        "              `--'         .'!!:::::  \\\\_\\ `.\n" +
                        "                      : . /%O!!::::::::\\\\_\\. \\\n" +
                        "                     [\"\"]/%%O!!:::::::::  : . \\\n" +
                        "                     |  |%%OO!!::::::::::: : . |\n" +
                        "                     |  |%%OO!!:::::::::::::  :|\n" +
                        "                     |  |%%OO!!!::::::::::::: :|\n" +
                        "            :       .'--`.%%OO!!!:::::::::::: :|\n" +
                        "          : .:     /`.__.'\\%%OO!!!::::::::::::/\n" +
                        "         :    .   /        \\%OO!!!!::::::::::/\n" +
                        "        ,-'``'-. ;          ;%%OO!!!!!!:::::'\n" +
                        "        |`-..-'| |   ,--.   |`%%%OO!!!!!!:'\n" +
                        "        | .   :| |_.','`.`._|  `%%%OO!%%'\n" +
                        "        | . :  | |--'    `--|    `%%%%'\n" +
                        "        |`-..-'| ||   | | | |     /__\\`-.\n" +
                        "        \\::::::/ ||)|/|)|)|\\|           /\n" +
                        "---------`::::'--|._ ~**~ _.|----------( -----------------------\n" +
                        "           )(    |  `-..-'  |           \\    ______\n" +
                        "           )(    |          |,--.       ____/ /  /\\\\ ,-._.-'\n" +
                        "        ,-')('-. |          |\\`;/   .-()___  :  |`.!,-'`'/`-._\n" +
                        "       (  '  `  )`-._    _.-'|;,|    `-,    \\_\\__\\`,-'>-.,-._\n" +
                        "        `-....-'     ````    `--'      `-._       (`- `-._`-.   ";

                break;

            case 5 :
                /*
                Luck
                 */
                ret = "                 ***          ***                     \n" +
                        "              ***....**     **...***                  \n" +
                        "             **........** **.......**                 \n" +
                        "      ***    **..........*.........**    ***\n" +
                        "   **.....**  **..................**  **.....**\n" +
                        " **.........**  **..............**  **.........**\n" +
                        "*..............*   *..........*   *..............*\n" +
                        " **..............*   *......*   *..............**\n" +
                        "   **..............** *....* **..............**\n" +
                        "     *......................................*\n" +
                        "   **..............**........**..............**\n" +
                        " **..............*    *....*....*..............**\n" +
                        "*..............*    *........* ...*..............*\n" +
                        " **.........**    *............* ...**.........**\n" +
                        "   **.....**   **...............**....**.....**\n" +
                        "      ***    **...................**.....***\n" +
                        "           **...........*...........**....*\n" +
                        "            **.........* *.........** *......*..*..*\n" +
                        "              *......**   **......*     *........*\n" +
                        "                **  *       * **           *...*\n" +
                        "                                              *";

            default:
                break;

        }

        return ret;
    }

    /**
     * This method allows to create the final example of the tutorial.
     * It has the position of the pawns of the final example.
     * @param player The player which you want to know the position of his pawn.
     * @return The position of the pawn according to the player past in parameter.
     */
    private static int[][] createExample(String player){
        int[][] ret = new int[12][2];

        switch (player){
            case "bot":
                //x
                ret[0][0] = 4;
                ret[1][0] = 7;
                ret[2][0] = 8;
                ret[3][0] = 7;
                ret[4][0] = 10;
                ret[5][0] = 5;
                ret[6][0] = 3;
                ret[7][0] = 1;
                ret[8][0] = 0;
                ret[9][0] = 0;
                //y
                ret[0][1] = 0;
                ret[1][1] = 0;
                ret[2][1] = 2;
                ret[3][1] = 6;
                ret[4][1] = 8;
                ret[5][1] = 10;
                ret[6][1] = 8;
                ret[7][1] = 3;
                ret[8][1] = 7;
                ret[9][1] = 8;

                break;

            case "Apprentice":
                //xx
                ret[0][0] = 0;
                ret[1][0] = 1;
                ret[2][0] = 2;
                ret[3][0] = 3;
                ret[4][0] = 4;
                ret[5][0] = 2;
                ret[6][0] = 3;
                ret[7][0] = 2;
                ret[8][0] = 4;
                ret[9][0] = 3;
                ret[10][0] = 10;
                ret[11][0] = 7;
                //y
                ret[0][1] = 1;
                ret[1][1] = 1;
                ret[2][1] = 3;
                ret[3][1] = 3;
                ret[4][1] = 3;
                ret[5][1] = 4;
                ret[6][1] = 4;
                ret[7][1] = 5;
                ret[8][1] = 5;
                ret[9][1] = 6;
                ret[10][1] = 3;
                ret[11][1] = 10;

                break;

            default:
                ret = null;
                break;
        }

        return ret;
    }
}
