package customer;

import product.Media;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
* Creates a menu for user interface and registers media and points to students
* 
* @author              Paul Dang
* @version             1.0
* @since               1.0
* @license.agreement   CC BY-SA International 4.0
*/

public class Student
{
    private String name;
    private int id;
    private String email;
    private Account account;
    /**
     * Constructor to Student information
     *
     * @param name      name of student
     * @param id        id numbers of student
     * @param email     email of student
     * @param unlimited states whether or not account is unlimited or alacarte
     * @since           1.0
     */
    public Student(String name, int id, String email, boolean unlimited)
    {
        this.account = unlimited ? new Unlimited() : new Alacarte();
        boolean atUta = email.endsWith("@uta.edu");
        boolean atMav = email.endsWith("@mavs.uta.edu");
        if(!(atUta || atMav))
        {
            throw new  IllegalArgumentException("Non-UTA email address: " + email);
        }
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public Student(BufferedReader br) throws IOException
    {
        this.name = br.readLine();
        this.id = Integer.parseInt(br.readLine());
        this.email = br.readLine();
        String accountType = br.readLine();
        switch (accountType) {
            case "customer.Alacarte":
                this.account = new Alacarte(br);
                break;
            case "customer.Unlimited":
                this.account = new Unlimited(br);
                break;
            default:
                throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }
    public void save(BufferedWriter bw) throws IOException
    {   
        bw.write(name+'\n');
        bw.write(Integer.toString(id)+'\n');
        bw.write(email+'\n');

        bw.write(account.getClass().getName() + '\n');
        account.save(bw);

    }

    /**
     * Compares information of account to requested media to tell whether or not account can afford the media 
     * 
     * @param media     media information
     * @return          returns user choice of media to play
     * @since           1.0
     */
    public String requestMedia(Media media)
    {
        return account.play(media);
    }
    /**
     * Gets account information
     *
     * @return          returns the account information
     * @since           1.0
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * Student information collected into one account
     * 
     * @return          returns all student information into one account
     * @since           1.0
     */
    @Override
    public String toString()
    {
        return name+" ("+id+", "+email+", "+"Account "+account.getAccountNumber()+")";
    }
}