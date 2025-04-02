package test;


import product.Media;


import java.net.URL;
import java.net.MalformedURLException;

public class TestMedia
{
    public static void main (String[] args)
    {
        int results = 0;
        int vector = 1;
        Media Media1 = new Media("Youtube","https://youtube.com",5);
        String expected = "Youtube (https://youtube.com, 5 points)";
        String[] validURL ={"http://youtube.com","file://media/lib/garp.mp4"};
        //Test to fail
        //String[] invalidURL ={"hello.world","htt://badurl.com","flub://badurl.com"};
        if(!Media1.toString().equals(expected))
        {
            System.err.printf("FAIL:\n\texpected string: %s\n\treturned string: %s",expected,Media1);
            results |= vector;
        }
        for(String i:validURL)
        {
            try
            {
                URL test1 = new URL(i);
                //System.err.printf("FAIL:\n\t\tInvalid URL: %s\n", test1);
                results |= vector;
            }
            catch(MalformedURLException e)
            {
                System.err.printf("FAIL:\n\t\tInvalid URL: %s\n", e);
                results |= vector;
            }
            catch(Exception e)
            {
                System.err.printf("FAIL: Something went wrong\n");
            }
        }
        
    }
}