/*
 * Robert Roland
 * Baby Name Ranking
 * Connects to ssa.gov to find the rank of a name
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class BabyNameRanker
{

    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner (System.in);

        String[][] boyNames = new String[200][2]; //Create array to hold 200 boy names and # of people named that
        String[][] girlNames = new String[200][2]; //Create array to hold 200 boy names and # of people named that
        int decade = 0; //Holds the decade the user wishes to search

        System.out.println("Baby Name Ranking");
        System.out.println("Enter a decade to start! (1880 - 2010)");
        System.out.print("Decade > ");
        decade = input.nextInt();

        // Make a URL to the web page
        URL url = new URL("https://www.ssa.gov/oact/babynames/decades/names" + Integer.toString(decade) + "s.html");
        System.out.println("Connecting to www.ssa.gov");
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.print("Connected. Loading list. ");
        String line = null; //Holds the current line of HTML
        String name = "null"; //Holds the piece of into that we want
        int pos = 0; //Used to trim strings where we need it
        // read each line and write to System.out
        while ((line = br.readLine()) != null)
        {
            if (line.contains("align=\"center\"") && !line.contains("scope=")) //We know the info we are looking for contains this
            {
                name = line.replace("<td align=\"center\">", ""); //Delete the opening tag
                name = name.replace("</td>", ""); //Delete the closing tag
                name = name.trim(); //Trim our result
                if (name.contains("<td>")) //We also know it will contain this tag
                {
                    name = name.replace("<td>",""); //So delete the opening tag
                    name = name.replace("</tr>",""); //And the closing tag
                    name = name.trim(); //Save the info we want and trim it
                }
            }
            int j = 0; //This will iterate through the cases when inserting into the arrays
            int len = 0; //We are saving different parts of the string, so we have to remember where we are in the string
            if (name != "") //Prevent junk HTML from messing up our saving
            {
                for (int i = 0; i < name.length(); i++) //Iterare char by char through the current string
                {
                    char n = name.charAt(i); //'n' is the current char
                    if ((n == ' ' || n == '\n') && pos < 200) //We are looking for spaces. This is our data delimeter
                    {
                        switch (j)
                        {
                            case 0: //First piece of info is the boy's name
                                boyNames[pos][0] = name.substring(0,i);
                                len = boyNames[pos][0].length(); //Save where we are in the string
                                j++; //Iterate to the next switch case
                                break;
                            case 1: //Second piece of into is the number of boys named that name
                                boyNames[pos][1] = name.substring(len+1, i); //Pick up where we left off in the string
                                len += boyNames[pos][1].length(); //Save where we are now in the string
                                j++; //Iterate to the next switch case
                                break;
                            case 2: //Third and fourth piece of info is the girl's name and the number named that
                                girlNames[pos][0] = name.substring(len+2, i); //Pick up where we left off in the same string
                                len += girlNames[pos][0].length(); //Save where we are again
                                girlNames[pos][1] = name.substring(len+3,name.length()); //The rest of the string is the number
                                pos++; //Iterate to the next position in the array
                                break;
                        }//end switch
                    } //end if
                } //end for
            }
            name = ""; //Set name to NULL so we can detect new, useful information
        } //end while

        char sex = 'o'; //Stores the array the user wants to search
        boolean foundName = false; //Lets us know if the user's name was found or not

        System.out.println("Done!");
        System.out.print("Search Male or Female names? (M/F)");
        sex = input.next().charAt(0);
        input.nextLine();

        if(sex == 'M')
        {
            System.out.println("Selecting Male Names.");
            System.out.print("Type a name: ");
            String userName = input.nextLine();
            userName = userName.trim();
            for (int l = 0; l < 200; l++)
            {
                if (userName.equals(boyNames[l][0]))
                {
                    System.out.println(boyNames[l][0] + " is ranked #" + (l+1) + " as " + boyNames[l][1] + " boys were named this in the decade " + decade);
                    foundName = true;
                    break;
                }
            }
            if (foundName == false)
            {
                System.out.println("Sorry, " + userName + " was not that popular in the " + decade + "'s");
            }
        }
        else
        {
            System.out.println("Selecting Female Names.");
            System.out.print("Type a name: ");
            String userName = input.nextLine();
            userName = userName.trim();
            for (int l = 0; l < 200; l++)
            {
                if (userName.equals(girlNames[l][0]))
                {
                    System.out.println(girlNames[l][0] + " is ranked #" + (l+1) + " as " + girlNames[l][1] + " girls were named this in the decade " + decade);
                    foundName = true;
                    break;
                }
            }
            if (foundName == false)
            {
                System.out.println("Sorry, " + userName + " was not that popular in the " + decade + "'s");
            }
        }
    } //end main
} //end class