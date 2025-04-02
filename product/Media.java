package product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Creates Media String and Price of Media
 * 
 * @author              Paul Dang
 * @version             1.0
 * @since               1.0
 * @license.agreement   CC BY-SA International 4.0
 */

public class Media
{
    private int points;
    private String title;
    private String url;
    /**
     * Creates Media instance
     * 
     * @param title     the title of media
     * @param url       the link of where the media is
     * @param points    the cost to purchase media
     * @since           1.0
     */
    public Media(String title, String url, int points)
    {
        this.title = title;
        this.url = url;
        try
        {
            URL test = new URI(url).toURL();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("FAIL: "+ url+ " is invalid url.");
        }
        this.points = points;
    }

    public Media(BufferedReader br) throws IOException
    {
        this.title = br.readLine();
        this.url = br.readLine();
        this.points = Integer.parseInt(br.readLine());
    }
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(title + '\n');
        bw.write(url + '\n');
        bw.write(Integer.toString(points) + '\n');
    }


    /**
     * Returns instance of points
     * 
     * @returns         returns cost to purchase media
     * @since           1.0
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * @returns         returns the string writing the title of media followed by the link and how much it cost to buy
     * @since           1.0
     */
    @Override
    public String toString()
    {
        return title + " (" +url+", "+points+" points)";
    }
}