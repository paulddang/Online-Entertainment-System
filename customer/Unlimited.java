package customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import product.Media;
/**
* Account has unlimited amount of points to use on medias
* 
* @author              Paul Dang
* @version             1.0
* @since               1.0
* @license.agreement   CC BY-SA International 4.0
*/
public class Unlimited extends Account
{
    public Unlimited()
    {
        super();
    }

    public Unlimited(BufferedReader br) throws IOException
    {
        super(br);
    }

    public void save(BufferedWriter bw) throws IOException
    {
        super.save(bw);
    }
    /**
     * Account has unlimited amount of points will automatically play media
     * 
     * @param media     media selected by user
     * @return          returns to notify user that media is playing
     * @since           1.0
     */
    @Override
    public String play(Media media)
    {
        return "Playing "+media;
    }
}
