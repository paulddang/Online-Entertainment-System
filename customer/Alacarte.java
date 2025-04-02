package customer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import product.Media;

/**
* Account under this class has a select amount of points and will change
* 
* @author              Paul Dang
* @version             1.0
* @since               1.0
* @license.agreement   CC BY-SA International 4.0
*/
public class Alacarte extends Account 
{
    private int pointsRemaining;
    /**
     * Adds points based on user input into account
     * 
     * @param points    points to add into account based on user input
     * @since           1.0
     */

     public Alacarte()
     {
        super();
        this.pointsRemaining = pointsRemaining;
     }
     public Alacarte(BufferedReader br) throws IOException
     {
        super(br);
        this.pointsRemaining = Integer.parseInt(br.readLine());
     }

     public void save(BufferedWriter bw) throws IOException
     {
        super.save(bw);
        bw.write(Integer.toString(pointsRemaining)+'\n');
     }

    public void buyPoints(int points)
    {
        pointsRemaining+=points;
    }

    /**
     * Points in account to be used
     * 
     * @return          returns points to account
     * @since           1.0
     */
    public int getPointsRemaining()
    {
        return pointsRemaining;
    }

    /**
     * Compares points in the account to media cost
     * 
     * @param media     media selected by user
     * @return          returns a statement based on if account can afford the cost of media selected by user
     * @since           1.0
     */
    @Override
    public String play(Media media)
    {
        if((pointsRemaining<media.getPoints()))
        {
            return "Buy more points: Requires "+media.getPoints()+" points, you have "+pointsRemaining;
        }
        else
        {
            pointsRemaining-=media.getPoints();
            return "Playing "+media;
        }
    }
}
