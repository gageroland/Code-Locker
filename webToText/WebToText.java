/*
 * Robert Roland
 * WebToText
 * User enters a web address and the programs removes everything
 * but the text meant to be read.
 *
 * Feel free to report issues to contact@wulf.design.
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

        System.out.print("1 - Run, 2 - Full HTML: ");
        userOption = input.nextInt();

        System.out.println("------------------------------" + myURL + "------------------------------");

        String line = null; //Holds the current line of HTML
        // read each line and write to System.out

        int check = 1;

        while ((line = br.readLine()) != null)
        {
            if (userOption == 1)
            {
                line = line.replaceAll("<p>","\n");
                line = line.replaceAll("</p>","\n");
                line = line.replaceAll("</title>","\n");
                line = line.replaceAll("(?s)</header>","\n");

                line = line.replaceAll("(?s)<p.*?>","");
                line = line.replaceAll("<title>","");
                line = line.replaceAll("<meta.*?>","");
                line = line.replaceAll("(?s)<script>.*?</script>","");
                line = line.replaceAll("(?s)<script.*?</script>","");
                line = line.replaceAll("(?s)<link.*?/>","");
                line = line.replaceAll("(?s)<a.*?>","");
                line = line.replaceAll("(?s)</a>"," ");
                line = line.replaceAll("(?s)<link.*?>","");
                line = line.replaceAll("(?s)<!.*?>","");
                line = line.replaceAll("(?s)</div>","");
                line = line.replaceAll("(?s)<img.*?/>","");
                line = line.replaceAll("(?s)<span .*?</span>","");
                line = line.replaceAll("(?s)<header.*?>","");
                line = line.replaceAll("(?s)<style>.*?</style>","");
                line = line.replaceAll("(?s)<body.*?>","");
                line = line.replaceAll("(?s)<h1.*?>","");
                line = line.replaceAll("(?s)</h1>","");
                line = line.replaceAll("(?s)<h2.*?>","\n");
                line = line.replaceAll("(?s)</h2>","\n");
                line = line.replaceAll("(?s)<h3.*?>", "\n");
                line = line.replaceAll("(?s)</h3>", "\n");
                line = line.replaceAll("(?s)<h4>","");
                line = line.replaceAll("(?s)</h4>","");
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
                line = line.replaceAll("(?s)<span>.*?</span>","");
                line = line.replaceAll("(?s)<span.*?>","");
                line = line.replaceAll("(?s)</span>","");
                line = line.replaceAll("(?s)<sup.*?>","");
                line = line.replaceAll("(?s)</sup","");
                line = line.replaceAll("(?s)<i.*?>","");
                line = line.replaceAll("(?s)</i>","");
                line = line.replaceAll("(?s)<hr.*?>","");
                line = line.replaceAll("(?s)<table.*?>","");
                line = line.replaceAll("(?s)<caption.*?</caption>","");
                line = line.replaceAll("(?s)<td.*?>","");
                line = line.replaceAll("(?s)</td>","");
                line = line.replaceAll("(?s)<th.*?</th>","");
                line = line.replaceAll("(?s)<tr>","");
                line = line.replaceAll("(?s)</tr>","");
                line = line.replaceAll("(?s)<code>","");
                line = line.replaceAll("(?s)</code>","");
                line = line.replaceAll("(?s)<b.*?>","");
                line = line.replaceAll("(?s)</b>","");
                line = line.replaceAll("(?s)</pre>","");
                line = line.replaceAll("(?s)<ol.*?>","");
                line = line.replaceAll("(?s)</ol>","");
                line = line.replaceAll("(?s)</cite>","");

                if(line.contains("<footer")) //Once the footer begins, there is no need to continue reading
                {
                    break;
                }
                if (check == 1) //If the document is in the head of the HTML
                {
                    if(!line.contains("</head>")) //If not at the end of the head,
                    {
                        line = ""; //Remove the line
                    }
                    else //If at the end of head
                    {
                        check = 0; //Stop ignoring lines
                        line = ""; //Except the closing head line.
                    }
                }
                line = line.replaceAll("    "," ");
                line = line.replaceAll("  "," ");
            }
            else
            {
                line = line.replaceAll(">",">\n");
            }

            System.out.print(line);

        } //end while
    } //end main
} //end class
