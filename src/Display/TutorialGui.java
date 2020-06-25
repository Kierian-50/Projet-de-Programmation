package Display;

import javax.swing.*;
import java.awt.*;


/**
 * This class allows to create and display a tutorial.
 * @author Kierian Tirlemont
 */
class TutorialGui {

    /**
     * The frame which displays the tutorial.
     */
    static JFrame tutorialGui;
    /**
     * There is different pages for the tutorial, so this String
     * represents the current page.
     */
    static String currentPage;
    /**
     * First page.
     */
    static Container page1;
    /**
     * Second page.
     */
    static Container page2;
    /**
     * Third page.
     */
    static Container page3;
    /**
     * Fourth page.
     */
    static Container page4;
    /**
     * Fifth page.
     */
    static Container page5;
    /**
     * Sixth page.
     */
    static Container page6;
    /**
     * The seventh page.
     */
    static Container page7;

    /**
     * The constructor of the class which initializes the attributes
     * of the frame.
     */
    TutorialGui(){
        tutorialGui = new JFrame("Zen l'Initie : Tutorial");
        tutorialGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        currentPage = "Page1";
        page1 = page1();
        page2 = page2();
        page3 = page3();
        page4 = page4();
        page5 = page5();
        page6 = page6();
        page7 = page7();

        tutorialGui.add(page1);

        tutorialGui.setMinimumSize(tutorialGui.getSize());
        tutorialGui.pack();
        tutorialGui.setVisible(true);
    }

    /**
     * This method creates the first page by creating the container
     * which contains every elements of the first page.
     * @return The container which contains every elements of  the
     * first page of the tutorial.
     */
    private Container page1(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(createTextPage1(),BorderLayout.NORTH);
        ret.add(imageSalutation(),BorderLayout.CENTER);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * first page.
     * @return a JPanel which contains the text of the first page.
     */
    private JPanel createTextPage1(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(6,1));

        ret.add(new JLabel("Hello, I'm the professor Oak, known in France as the professor \"Chen\". May be you already"));
        ret.add(new JLabel("know me because I was the professor in Pokemon. But I left Pokemon for a better game : Zen"));
        ret.add(new JLabel("l'Initie. In this part, I will teach you how to play this amazing game which is Zen l'Initie."));
        ret.add(new JLabel("Listen carefully to what I'm going to teach you, it could be useful when you will be in a duel."));
        ret.add(new JLabel("The presentation made. Let's start !"));

        return ret;
    }

    /**
     * This method creates the second page by creating the container
     * which contains every elements of the second page.
     * @return The container which contains every elements of the
     * second page of the tutorial.
     */
    private Container page2(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(imageGrid(),BorderLayout.NORTH);
        ret.add(createTextPage2(),BorderLayout.CENTER);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * second page.
     * @return a JPanel which contains the text of the second page.
     */
    private JPanel createTextPage2(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(12,1));

        ret.add(new JLabel("This is on this 11 by 11 square board that the duel take place. You see that there is two teams:"));
        ret.add(new JLabel("the green circle and the blue cross. You see in the center that there is special red pawn, I"));
        ret.add(new JLabel("speak about it later. The goal of the game is to align all your pawns before your opponent."));
        ret.add(new JLabel("Now, we will answer to the question \"How can this pawns be move? What is the rules ?\""));
        ret.add(new JLabel("The pawns can move in every direction : horizontal, vertical and diagonal. To know the number"));
        ret.add(new JLabel("of squares that you can pass, you must count the number of pawn on a axe and it will the size "));
        ret.add(new JLabel("of your movement in Square. But if there are an enemy pawn on the path you can't jump over : the"));
        ret.add(new JLabel("movement is forbidden. But if the enemy pawn is an the final square you can capture his enemy pawn."));
        ret.add(new JLabel("And you can jump over your pawns and the zen pawn."));

        return ret;
    }

    /**
     * This method creates a JPanel which contains the image of
     * the teacher which explains.
     * @return A JPanel which contains the image of the teacher
     * which explains.
     */
    private JPanel imageExplaining1(){
        JPanel ret = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("explaining.png"));
        ret.add(new JLabel(img));
        return ret;
    }

    /**
     * This method creates a JPanel which contains the image of
     * the teacher which salute.
     * @return A JPanel which contains the image of the teacher
     * which salute.
     */
    private JPanel imageSalutation(){
        JPanel ret = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("salutation.png"));
        ret.add(new JLabel(img));
        return ret;
    }

    /**
     * This method creates a JPanel which contains a JButton to allow to
     * the user to continue the tutorial.
     * @return A JPanel which contains a JButton to allow to the user to
     * continue the tutorial.
     */
    private JPanel createContinueButton(){
        JPanel ret = new JPanel();
        JButton continueButton = new JButton("Continue");
        Action.buttonListener(continueButton,"Continue");
        ret.add(continueButton);
        return ret;
    }

    /**
     * This method creates a JPanel which contains the image of
     * a grid game.
     * @return A JPanel which contains the image of a grid game.
     */
    private JPanel imageGrid(){
        JPanel ret = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("Grid.JPG"));
        ret.add(new JLabel(img));
        return ret;
    }

    /**
     * This method creates the third page by creating the container
     * which contains every elements of the third page.
     * @return The container which contains every elements of the
     * third page of the tutorial.
     */
    private Container page3(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(imageGrid(),BorderLayout.NORTH);
        ret.add(createTextPage3(),BorderLayout.CENTER);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * third page.
     * @return a JPanel which contains the text of the third page.
     */
    private JPanel createTextPage3(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(4,1));

        ret.add(new JLabel("As an example is worth as a thousand words, the pawn in (0,5) can move in two directions."));
        ret.add(new JLabel("First, it can move in (0,8) because there is three pawns on this axe and also (0,2) for the"));
        ret.add(new JLabel("same reason. And it can move in (3,5) because there is three pawns on this axe. But it can't"));
        ret.add(new JLabel("move diagonally because the enemy pawns avoid this movement."));

        return ret;
    }

    /**
     * This method creates the fourth page by creating the container
     * which contains every elements of the fourth page.
     * @return The container which contains every elements of the
     * fourth page of the tutorial.
     */
    private Container page4(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(createTextPage4(),BorderLayout.NORTH);
        ret.add(imageExplaining1(),BorderLayout.CENTER);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * fourth page.
     * @return a JPanel which contains the text of the fourth page.
     */
    private JPanel createTextPage4(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(6,1));

        ret.add(new JLabel("Now, we will speak about the red pawn : the zen. This is a special pawn. The zen pawn is "));
        ret.add(new JLabel("common to the two players : it can be move by you or your opponent. You can consider it as"));
        ret.add(new JLabel("your friend or your enemy, this is to say that you can capture it with your pawns. But the zen"));
        ret.add(new JLabel("respects specials rules. First, when a player moves it, his opponent can't replace it at his old"));
        ret.add(new JLabel("square. Secondly, it's forbidden to move it, if the zen pawn is not in touch with a pawn"));
        ret.add(new JLabel("(whatever if it's one of your pawns or one of the pawns of your opponent)."));

        return ret;
    }

    /**
     * This method creates the fifth page by creating the container
     * which contains every elements of the fifth page.
     * @return The container which contains every elements of the
     * fifth page of the tutorial.
     */
    private Container page5(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(createTextPage5(),BorderLayout.NORTH);
        ret.add(imageExplaining2(),BorderLayout.CENTER);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * fifth page.
     * @return a JPanel which contains the text of the fifth page.
     */
    private JPanel createTextPage5(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(12,1));

        ret.add(new JLabel("Now, we will see how to finish a game and win. It's really easy : to win, you must align"));
        ret.add(new JLabel("all your pawns before your opponent. To align, your pawn, you can capture your enemy pawns."));
        ret.add(new JLabel("But if the zen is always on the board, you must include the zen to your chain else you will"));
        ret.add(new JLabel("your chain will not be valid."));
        ret.add(new JLabel("And there are two case, we're the game end by a draw between the player : "));
        ret.add(new JLabel("- First, when you move the zen pawn and it align and finish your opponent's chain"));
        ret.add(new JLabel("- Secondly, when you capture the only pawn of your opponent which is not in the chain, the"));
        ret.add(new JLabel("game end by a draw whereas the opponent's win."));
        ret.add(new JLabel("To finish before the exercise, you can play a single game but you can also play a match in"));
        ret.add(new JLabel("several sleeves. At each sleeve, we count the number of pawn of the looser which is not link"));
        ret.add(new JLabel("to the main chain and add it to the score of the winner. The first at seven points is the winner."));
        ret.add(new JLabel("(Only available in command line)"));

        return ret;
    }

    /**
     * This method creates a JPanel which contains the image of
     * the teacher.
     * @return A JPanel which contains the image of the teacher.
     */
    private JPanel imageExplaining2(){
        JPanel ret = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("Explaining2.jpg"));
        ret.add(new JLabel(img));
        return ret;
    }

    /**
     * This method creates the sixth page by creating the container
     * which contains every elements of the sixth page.
     * @return The container which contains every elements of the
     * sixth page of the tutorial.
     */
    private Container page6(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(createTextPage6(),BorderLayout.CENTER);
        ret.add(imageGridExample(),BorderLayout.NORTH);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * sixth page.
     * @return a JPanel which contains the text of the sixth page.
     */
    private JPanel createTextPage6(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(2,1));

        ret.add(new JLabel("On this example, you see that the black will make a chain by moving the pawn in (6,0) to"));
        ret.add(new JLabel("the position in (1,5) and the player with black pawns will win."));

        return ret;
    }

    /**
     * This method creates a JPanel which contains the image of
     * a grid game.
     * @return A JPanel which contains the image of a grid game.
     */
    private JPanel imageGridExample(){
        JPanel ret = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("GridExample.JPG"));
        ret.add(new JLabel(img));
        return ret;
    }

    /**
     * This method creates the seventh page by creating the container
     * which contains every elements of the seventh page.
     * @return The container which contains every elements of the
     * seventh page of the tutorial.
     */
    private Container page7(){
        Container ret = new Container();
        ret.setLayout(new BorderLayout());

        ret.add(createTextPage7(),BorderLayout.NORTH);
        ret.add(imageFourLeaf(),BorderLayout.CENTER);
        ret.add(createContinueButton(),BorderLayout.SOUTH);

        return ret;
    }

    /**
     * This method creates a JPanel which contains the text of the
     * seventh page.
     * @return a JPanel which contains the text of the seventh page.
     */
    private JPanel createTextPage7(){
        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(7,1));

        ret.add(new JLabel("Congratulation ! You were a very good student and now you're ready to play this amazing "));
        ret.add(new JLabel("game. Before you leaves, I have two things to tell you. First, if one day the game is too "));
        ret.add(new JLabel("easy or you want to play with more difficulties in the option submenu you can deactivate"));
        ret.add(new JLabel("the option that allows to print the possibles squares when you select a pawn."));
        ret.add(new JLabel("Second, I hope you found this lesson helpful. I wish you the best for your futures games"));
        ret.add(new JLabel("Oh, I almost forgot! To put all the chances on your side, I offer you this freshly picked"));
        ret.add(new JLabel("four-leaf clover"));

        return ret;
    }

    /**
     * This method creates a JPanel with the image of a
     * four leaf.
     * @return A JPanel with the image of a
     * four leaf.
     */
    private JPanel imageFourLeaf(){
        JPanel ret = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("four-leaf.png"));
        ret.add(new JLabel(img));
        return ret;
    }
}
