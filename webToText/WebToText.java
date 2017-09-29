/*
 * Robert Roland
 * 
 * 
 * 
 * Reference the credit card assignment.
 * 
 * This program uses baby name ranking program so far.
 * 
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class WebToText
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner (System.in);

        String myURL;
        int userOption;

        System.out.println("Web To Txt!");
        System.out.print("https://www.");
        myURL = input.nextLine();

        // Make a URL to the web page
        URL url = new URL("https://www." + myURL);
        System.out.println(" | Connecting to " + myURL);
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println(" | Connected!");

        System.out.print("1 - Run, 2 - Debug, 3 - Full HTML");
        userOption = input.nextInt();

        System.out.println("------------------------------" + myURL + "------------------------------");

        String line = null; //Holds the current line of HTML
        // read each line and write to System.out
        while ((line = br.readLine()) != null)
        {
            if (userOption == 2)
            {
                line = line.replaceAll(">",">\n");
                line = line.replaceAll("(?s)<meta.*?/>","REMOVED META");
                line = line.replaceAll("(?s)<script>.*?</script>","REMOVED SCRIPT");
                line = line.replaceAll("(?s)<script.*?</script>","REMOVED SCRIPT 2");
                line = line.replaceAll("(?s)<link.*?/>","REMOVED LINK");
                line = line.replaceAll("(?s)<a.*?</a>","REMOVED LINK 2");
                line = line.replaceAll("(?s)<link.*?>","REMOVED LINK 3");
                line = line.replaceAll("(?s)<!.*?>","REMOVED COMMENT");
                line = line.replaceAll("(?s)</div>","REMOVED CLOSING DIV");
                line = line.replaceAll("(?s)<img.*?/>","REMOVED IMAGE");
                line = line.replaceAll("(?s)<span .*?</span>","REMOVED SPAN");
                line = line.replaceAll("(?s)<header.*?</header>","REMOVED HEADER"); //NO PROOF THIS WORKS
                line = line.replaceAll("(?s)<style>.*?</style>","REMOVED STYLE");
                line = line.replaceAll("(?s)<body.*?>","REMOVED BODY");
                line = line.replaceAll("(?s)<h1.*?>","REMOVED HEADER 1");
                line = line.replaceAll("(?s)</h1>","REMOVED HEADER 1 CLOSE");
                line = line.replaceAll("(?s)<h2.*?>","REMOVED HEADER 2");
                line = line.replaceAll("(?s)</h2>","REMOVED HEADER 2 CLOSE");
                line = line.replaceAll("(?s)<h3.*?>", "REMOVED HEADER 3");
                line = line.replaceAll("(?s)</h3>", "REMOVED HEADER 3 CLOSE");
                line = line.replaceAll("(?s)<noscript>.*?</noscript>","REMOVED NOSCRIPT");
                line = line.replaceAll("(?s)<option.*?</option>","REMOVED OPTION");
                line = line.replaceAll("(?s)<select.*?>","REMOVED SELECT");
                line = line.replaceAll("(?s)</select>", "REMOVED SELECT CLOSE");
                line = line.replaceAll("(?s)<div.*?>","REMOVED DIV");
                line = line.replaceAll("(?s)<ul.*?>","REMOVED LIST");
                line = line.replaceAll("(?s)</ul>","REMOVED LIST 1");
                line = line.replaceAll("(?s)<li.*?>","REMOVED LIST2");
                line = line.replaceAll("(?s)</li>","REMOVED LIST 3");
                line = line.replaceAll("(?s)<input.*?/>","REMOVED INPUT");
                line = line.replaceAll("(?s)<form.*?>","REMOVED FORM");
                line = line.replaceAll("(?s)</form>","REMOVED FORM CLOSE");
                line = line.replaceAll("(?s)<html.*?>","REMOVED HTML OPEN");
                line = line.replaceAll("(?s)</html>","REMOVED HTML CLOSE");
            }
            else if (userOption == 1)
            {
                line = line.replaceAll("<p>","\n");
                line = line.replaceAll("</p>","\n");
                line = line.replaceAll("(?s)<meta.*?/>","");
                line = line.replaceAll("(?s)<script>.*?</script>","");
                line = line.replaceAll("(?s)<script.*?</script>","");
                line = line.replaceAll("(?s)<link.*?/>","");
                line = line.replaceAll("(?s)<a.*?</a>","");
                line = line.replaceAll("(?s)<link.*?>","");
                line = line.replaceAll("(?s)<!.*?>","");
                line = line.replaceAll("(?s)</div>","");
                line = line.replaceAll("(?s)<img.*?/>","");
                line = line.replaceAll("(?s)<span .*?</span>","");
                line = line.replaceAll("(?s)<header.*?</header>",""); //NO PROOF THIS WORKS
                line = line.replaceAll("(?s)<style>.*?</style>","");
                line = line.replaceAll("(?s)<body.*?>","");
                line = line.replaceAll("(?s)<h1.*?>","");
                line = line.replaceAll("(?s)</h1>","");
                line = line.replaceAll("(?s)<h2.*?>","\n");
                line = line.replaceAll("(?s)</h2>","\n");
                line = line.replaceAll("(?s)<h3.*?>", "\n");
                line = line.replaceAll("(?s)</h3>", "\n");
                line = line.replaceAll("(?s)<noscript>.*?</noscript>","");
                line = line.replaceAll("(?s)<option.*?</option>","");
                line = line.replaceAll("(?s)<select.*?>","");
                line = line.replaceAll("(?s)</select>", "");
                line = line.replaceAll("(?s)<div.*?>","");
                line = line.replaceAll("(?s)<ul.*?>","");
                line = line.replaceAll("(?s)</ul>","");
                line = line.replaceAll("(?s)<li.*?>","");
                line = line.replaceAll("(?s)</li>","");
                line = line.replaceAll("(?s)<input.*?/>","");
                line = line.replaceAll("(?s)<form.*?>","");
                line = line.replaceAll("(?s)</form>", "");
                line = line.replaceAll("(?s)<html.*?>","");
                line = line.replaceAll("(?s)</html>","");
            }
            else
            {
                line = line.replaceAll(">",">\n");
            }

            System.out.print(line);

        } //end while
    } //end main
} //end class
