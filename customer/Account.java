package customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import product.Media;
/**
* Creates a menu for user interface and registers media and points to students
* 
* @author              Paul Dang
* @version             1.0
* @since               1.0
* @license.agreement   CC BY-SA International 4.0
*/

public abstract class Account
{
    private int accountNumber;
    private static int nextAccountNumber = 1;
    /**
     * Builds new account number
     * 
     * @since           1.0
     */
    public Account()
    {
        this.accountNumber = nextAccountNumber++;        
    }

    public Account(BufferedReader br) throws IOException
    {
        this.accountNumber = Integer.parseInt(br.readLine());
        nextAccountNumber = Integer.parseInt(br.readLine());
    }
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(""+accountNumber+'\n');
        bw.write(""+nextAccountNumber+'\n');
    }
    /**
     * Newly built account number used to create new account
     * 
     * @return          returns account number to be used for new account
     * @since           1.0
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }
    /**
     * Initiates the play method to notify whether a media will be played or not
     * 
     * @param media     media selected by user
     * @since           1.0
     */
    public abstract String play(Media media);
}