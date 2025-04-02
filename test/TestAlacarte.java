package test;

import customer.Alacarte;
import product.Media;

public class TestAlacarte
{
    public static void main(String[] args)
    {
        Media media = new Media("Youtube","https://youtube.com",20);
        String expectedMedia = "Playing Youtube (https://youtube.com, 20 points)";
        Alacarte TestAccount = new Alacarte();
        String expectedMediaNoPoints = "Buy more points: Requires 20 points, you have 0";

        TestAccount.buyPoints(10);
        //functioning like if someone were to buy points more than once
        TestAccount.buyPoints(10);
        if(TestAccount.getPointsRemaining()!=20)
        {
            System.err.println("TestAccont points expected: 20 \nTestAccount actual points: "+TestAccount.getPointsRemaining());
        }
        if(!TestAccount.play(media).equals(expectedMedia))
        {
            System.err.println("TestAccount playing media string expected: "+expectedMedia+"\nTestAccount playing media string received: "+TestAccount.play(media));
        }
        if(TestAccount.getPointsRemaining()!=0)
        {
            System.err.println("TestAccount points after media expected: 20\nTEstAccount points after media recieved: "+TestAccount.getPointsRemaining());
        }
        if(!TestAccount.play(media).equals(expectedMediaNoPoints))
        {
            System.err.println("TestAccount playing media no points expected: "+expectedMediaNoPoints+"\nTestAccount playing media no points received: "+TestAccount.play(media));
        }
    }
}