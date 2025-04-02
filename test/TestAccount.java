package test;


import customer.Unlimited;
import customer.Alacarte;
import customer.Account;
import customer.Student;
import product.Media;

public class TestAccount
{
    public static void main(String[] args)
    {
        int[] expectedAlacarteAccountNumbers = {1, 2};
        int[] expectedUnlimitedAccountNumbers = {3, 4};

        Alacarte[] alacarteAccounts = {new Alacarte(), new Alacarte()};
        Unlimited[] unlimitedAccounts = {new Unlimited(), new Unlimited()};

        for (int i = 0; i < alacarteAccounts.length; i++)
        {
            Alacarte account = alacarteAccounts[i];
            int expectedAccountNumber = expectedAlacarteAccountNumbers[i];
            if (account.getAccountNumber() != expectedAccountNumber)
            {
                System.err.printf("FAIL:\n\tExpected Alacarte Account number %d\n\tResulted Account number %d\n", expectedAccountNumber, account.getAccountNumber());
            }
        }

        for (int i = 0; i < unlimitedAccounts.length; i++)
        {
            Unlimited account = unlimitedAccounts[i];
            int expectedAccountNumber = expectedUnlimitedAccountNumbers[i];
            if (account.getAccountNumber() != expectedAccountNumber)
            {
                System.err.printf("FAIL:\n\tExpected Unlimited Account number %d\n\tResulted Account number %d\n", expectedAccountNumber, account.getAccountNumber());
            }
        }
    }
}
