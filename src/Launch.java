import Display.*;
import utility.RWFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

/**
 * The launcher of the game.
 * @author Kierian Tirlemont
 */
public class Launch {

    /**
     * The entry point of the program.
     * @param args The arguments.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            createDataFile();
            Scanner sc = new Scanner(System.in);
            System.out.println("Write the mode that you want : ");
            System.out.println("- Graphical mode (Gui)");
            System.out.println("- Command line mode (Cli)");
            String str = sc.nextLine();
            while(!str.equalsIgnoreCase("Cli") && !str.equalsIgnoreCase("Gui")){
                System.out.println("Write Cli or Gui :");
                str = sc.nextLine();
            }
            if(str.equalsIgnoreCase("Cli")){
                MenuCommandLine f1 = new MenuCommandLine();
                f1.printFirstMenu();
            } else if (str.equalsIgnoreCase("Gui")) {
                new MainMenu();
            }
        });
    }

    /**
     * This method is able to create the data directory if
     * it doesn't exist.
     */
    private static void createDataFile(){
        if(!RWFile.isFile(findDataPath())){
            RWFile.createDirectory(findDataPath());
        }
    }

    /**
     * This method allows to find the path for the jar.
     * @return The path.
     */
    private static String findDataPath(){
        String path = null;
        try {
            path = URLDecoder.decode(Launch.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"UTF-8");
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
