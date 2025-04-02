package moes;

import product.Media;
import customer.Account;
import customer.Alacarte;
import customer.Student;
import customer.Unlimited;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;
/**
 * Creates a menu for user interface and registers media and points to students
 * 
 * @author              Paul Dang
 * @version             1.0
 * @since               1.0
 * @license.agreement   CC BY-SA International 4.0
 */

public class Moes 
{
    private ArrayList<Media> library = new ArrayList<Media>();
    private ArrayList<Student> customers = new ArrayList<Student>();

    public Moes()
    {
        super();
    }

    public Moes(BufferedReader br)  throws IOException
    {
        int sizeOfLibrary = Integer.parseInt(br.readLine());
        for(int i = 0; i<sizeOfLibrary; i++)
        {
            library.add(new Media(br));
        }
        
        int sizeOfCustomers = Integer.parseInt(br.readLine());
        for(int i = 0; i<sizeOfCustomers; i++)
        {
            customers.add(new Student(br));
        }
    }
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(""+library.size()+'\n');
        for(Media media : library)
        {
            media.save(bw);
        }

        bw.write(""+customers.size()+'\n');
        for(Student student : customers)
        {
            student.save(bw);
        }

    }
    /**
     * Creates library full of medias
     * 
     * @param media     media to add to ArrayList library
     * @since           1.0
     */
    public void addMedia(Media media)
    {
        library.add(media); 
    }
    /**
     * Creates menu for users to see what media is in the library
     * @returns         returns menu layout to print
     * @since           1.0
     */
    public String getMediaList()
     {
        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < library.size(); i++) 
        {
            menu.append(i).append(") ").append(library.get(i)).append("\n");
        }
        return menu.toString();
    }

    /**
     * Creates ArrayList of students
     * 
     * @param student   student information
     * @since           1.0
     */
    public void addStudent(Student student)
    {
        customers.add(student);
    }

    /**
     * Creates visual menu of students to printout
     * 
     * @return          returns menu list to print out
     * @since           1.0
     */
    public String getStudentList()
    {
        StringBuilder studentlist = new StringBuilder();
        for (int i = 0; i < customers.size(); i++) 
        {
            studentlist.append(i).append(") ").append(customers.get(i)).append("\n");
        }
        return studentlist.toString();
    }

    /**
     * Creates Media instance
     * 
     * @param studentIndex      User inputted student choice
     * @return                  returns either a number amount of points or an exception is thrown if there is an error
     * @since                   1.0
     */

    public int getPoints(int studentIndex) 
    {
        Account account = customers.get(studentIndex).getAccount();
        if (account instanceof Alacarte) 
        {
            Alacarte alacarte = (Alacarte) account;
            return alacarte.getPointsRemaining();
        } 
        else if (account instanceof Unlimited) 
        {
            return Integer.MAX_VALUE;
        } 
        else 
        {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }

    /**
     * Creates Media instance
     * 
     * @param studentIndex          User inputted choice of student
     * @param points                User inputted amount of points user wanted to add to an account
     * @return                      returns a statement with points or an exception when an error is found
     * @since                       1.0
     */

    public String buyPoints(int studentIndex, int points)
    {
        Account account = customers.get(studentIndex).getAccount();
        if(account instanceof Alacarte)
        {
            Alacarte alacarte = (Alacarte) account;
            alacarte.buyPoints(points);
            return "You have: "+alacarte.getPointsRemaining()+" points";
        }
        else if(account instanceof Unlimited)
        {
            return "Student has unlimited points in this account, no need for more";
        }
        else
        {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }

    /**
     * Creates Media instance
     * 
     * @param studentIndex          User inputted choice of student
     * @param mediaIndex            User inputted choice of which media wanted
     * @return                      returns a statement on selected student and their library of media
     * @since                       1.0
     */
    public String playMedia(int studentIndex, int mediaIndex)
    {
        return customers.get(studentIndex).requestMedia(library.get(mediaIndex));
    }
}