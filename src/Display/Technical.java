package Display;

import gameElement.Game;
import gameElement.Mode;
import utility.RWFile;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * This method is the technical class that allows to do
 * methods that is present for many class or that allows to
 * simplify a part of a code.
 * @author Kierian Tirlemont
 */
public class Technical {

    /**
     * This method allows to find the position of the button
     * that the user press and return it.
     * @param jButton The JButton that the user press.
     * @return The position of the button.
     * ret[0] = 0
     * ret[1] = 1
     */
    static int[] findThePosition(JButton jButton){
        int[] ret = new int[2];

        for (int i = 0; i < DisplayMyGrid.squares.length; i++) {
            for (int j = 0; j < DisplayMyGrid.squares[i].length; j++) {
                if(jButton == DisplayMyGrid.squares[j][i]){
                    ret[0] = j;
                    ret[1] = i;
                }
            }
        }

        return ret;
    }

    /**
     * This method allows to launch a saved game.
     * @param savedGame The name of the saved game that will
     *                  be launch.
     */
    static void launchSavedGame(String savedGame){
        ArrayList<String> configuration = RWFile.readFile(findDataPath()+savedGame);
        boolean found = false;

        Mode mode = Mode.valueOf(configuration.get(0));
        boolean option = Boolean.parseBoolean(configuration.get(1));

        String[] stringZenPosition = configuration.get(2).split(":");
        int[] zenPosition = new int[2];
        zenPosition[0] = Integer.parseInt(stringZenPosition[0]);
        zenPosition[1] = Integer.parseInt(stringZenPosition[1]);

        String namePlayer1 = configuration.get(3);

        int[][] pawnsPlayer1 = new int[12][2];
        int[][] pawnsPlayer2 = new int[12][2];
        int i = 4;
        int j = 0;
        while(!found){
            String[] stringPosition = configuration.get(i).split(":");
            if(stringPosition[0].equalsIgnoreCase("end")){
                found = true;
            }else{
                pawnsPlayer1[j][0] = Integer.parseInt(stringPosition[0]);
                pawnsPlayer1[j][1] = Integer.parseInt(stringPosition[1]);
            }
            i++;
            j++;
        }
        found = false;

        String namePlayer2 = configuration.get(i);
        i++;
        j=0;

        while (!found){
            String[] stringPosition = configuration.get(i).split(":");
            if(stringPosition[0].equalsIgnoreCase("end")){
                found = true;
            }else{
                pawnsPlayer2[j][0] = Integer.parseInt(stringPosition[0]);
                pawnsPlayer2[j][1] = Integer.parseInt(stringPosition[1]);
            }
            i++;
            j++;
        }
        Game.startGui(namePlayer1,namePlayer2,mode,option,pawnsPlayer1,pawnsPlayer2,zenPosition,savedGame);
    }

    /**
     * This method allows to delete a saved game.
     * @param savedGame The saved game which will be delete.
     */
    public static void deleteSavedGame(String savedGame){
        int i = 0;

        String dataPath = findDataPath();

        String numberOfTheDeleteFile = savedGame.replaceAll("\\D+","");
        int numberSavedGame = Integer.parseInt(numberOfTheDeleteFile);
        System.out.println(numberSavedGame);
        String filename = dataPath+"saved_game"+i+".txt";

        while(RWFile.isFile(filename)){
            i++;
            filename = dataPath+"saved_game"+i+".txt";
        }

        RWFile.deleteFile(dataPath+savedGame);
        if((i-1) != numberSavedGame){
            RWFile.renameFile(dataPath+savedGame,dataPath+"saved_game"+(i-1)+".txt");
        }
    }

    /**
     * This method is able to find the path of the class
     */
    static String findDataPath(){
        String path = null;
        try {
            path = URLDecoder.decode(Technical.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String[] data = path.split("/");
        path ="";
        for(int i=0;i<data.length-2;i++){
            path+=data[i]+"/";
        }

        path += "data/";
        return path;
    }
}
