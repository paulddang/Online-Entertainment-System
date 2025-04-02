package test;


import product.Media;
import customer.Student;
import customer.Unlimited;
import customer.Alacarte;

public class TestStudent
{
    public static void main(String[] args)
    {
        int vector = 1;
        int result = 0;
        Student studentUnlimited = new Student("Paul",1002095832,"pdd5832@mavs.uta.edu",true);
        Student studentAlacarte = new Student("Paul",1002095832,"pdd5832@mavs.uta.edu",false);

        String expected = "Paul (1002095832, pdd5832@mavs.uta.edu, Account 1)";
        Media media = new Media("Youtube","https://youtube.com",5);
        Unlimited unlimited = new Unlimited();
        Alacarte alacarte = new Alacarte();
        alacarte.buyPoints(5);

        Alacarte alacarteAccount = (Alacarte) studentAlacarte.getAccount();
        alacarteAccount.buyPoints(5);
        

        if(!(studentUnlimited.toString().equals(expected)))
        {
            System.err.printf("To String is not proper: %s\n%s\n",studentUnlimited.toString(),expected);
        }
        
        try
        {
            Student notUTAEmail = new Student("Paul",109283,"NotAUTAEmail@gmail.com",false);
            vector |= result;
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("FAIL:\n\tNon-UTA Email address: "+e);
            vector |= result;
        }
        catch(Exception e)
        {
            System.err.println("FAIL: Something went wrong");
            vector |= result;
        }

        // Testing media requests for Unlimited account
        System.out.println(studentUnlimited.requestMedia(media));
        if(!studentUnlimited.requestMedia(media).equals(unlimited.play(media)))
        {
            System.err.println("Expected student media: "+studentUnlimited.requestMedia(media)+"\nUnlimited account media print: "+unlimited.play(media));
        }
        for(int i = 0; i<2;i++)
        {
            String output = studentAlacarte.requestMedia(media);
            String expectedAlacarte = alacarte.play(media);
            if(!output.equals(expectedAlacarte))
            {
                System.err.println("\nExpected student media: "+output+"\nAlacarte account media print: "+expectedAlacarte);
            }
        }
    }
}
