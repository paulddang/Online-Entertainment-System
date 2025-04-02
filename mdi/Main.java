package mdi;
import java.util.Scanner;

import product.Media;
import customer.Student;
import moes.Moes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Main 
{
    private boolean dirty = false;
    private Moes moes;
    private String output;
    private Menu menu;
    private boolean running = true;
    private static Scanner scan = new Scanner(System.in);
    private final String title = "                    /\\\n"
                                +"                   (^- ^7  \n"
                                +"                    |   \\ \n"
                                +"                    L L ,)/\n"
                                +"v------------------------------------------------v\n"
                                +">                                                <\n"
                                +">     Mavs Online Entertainment System(MOES)     <\n"
                                +">     Version 1.0                       2024     <\n"
                                +">                                                <\n"
                                +"^------------------------------------------------^\n";

    private static final String extension = ".txt";
    private static final String magicCookie = "UTAOES";
    private static final String fileVersion = "1.0";
    private String filename;

    private void newMoes()
    {
        if(dirty == true)
        {
            System.out.println("You have unsaved changes!\n"
                             + "Choose the index of the option for these unsaved changes:\n");
            int choice = getInt("1) Save to current file.\n"
                               +"2) Save to new file.\n"
                               +"3) Discard changes.\n"
                               +"4) Aboort this command.\n");
            StringBuilder builder;
            switch(choice)
            {
                case 1: if(filename == null) 
                        {
                            System.out.println("\n!!!! No filename specified. Saving to new file... !!!!\n");
                            saveAs();
                        }
                         else save(); break;
                case 2: saveAs(); break;
                case 3: builder = build("Discarding changing"); print(""+builder); break;
                case 4: builder = build("Aborting Command"); print(""+builder); return;
                default: break;
            }
        }
        moes = new Moes();
        this.filename = null;
        dirty = false;
    }

    private void save()
    { 
        if (filename == null) {
            print("No filename specified. Please use 'Save As' first.");
            return;
        }
        File existingFile = new File(filename);
        if (existingFile.exists()) 
        {
            File backup = new File(filename + '~');
            existingFile.renameTo(backup);
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            bw.write(magicCookie + '\n');
            bw.write(fileVersion + '\n');
            moes.save(bw);
            StringBuilder builder = build("Saved file as: "+filename);
            print(""+builder);
            dirty = false;

        }
        catch(Exception e)
        {
            System.err.println("Failed to write: "+e.getMessage());
            return;
        }
    }

    private void saveAs()
    {
        System.out.println("Current filename: " + filename);
        String newFileName = getString("Enter new filename: ").trim();
        if(newFileName.isEmpty())
        {
            return;
        }
        if(!newFileName.endsWith(extension))
        {
            newFileName+=extension;
        }
        this.filename = newFileName;
        save();
    }

    private void open()
    {
        if(dirty == true)
        {
            System.out.println("You have unsaved changes!\n"
                             + "Choose the index of the option for these unsaved changes:\n");
            int choice = getInt("1) Save to current file.\n"
                               +"2) Save to new file.\n"
                               +"3) Discard changes.\n"
                               +"4) Aboort this command.\n");
            StringBuilder builder;
            switch(choice)
            {
                case 1: if(filename == null) 
                        {
                            System.out.println("\n!!!! No filename specified. Saving to new file... !!!!\n");
                            saveAs();
                        }
                         else save(); break;
                case 2: saveAs(); break;
                case 3: builder = build("Discarding changing"); print(""+builder); break;
                case 4: builder = build("Aborting Command"); print(""+builder); return;
                default: break;
            }
        }
        System.out.println("Current filename: " + filename);
        String newFileName = getString("Enter new filename: ").trim();
        if(newFileName.isEmpty())
        {
            return;
        }
        if(!newFileName.endsWith(extension))
        {
            newFileName+=extension;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(newFileName)))
        {
            String magic = br.readLine();
            if(!magic.equals(magicCookie))
            {
                throw new IOException("Invalid file magicCooke");
            }

            String version = br.readLine();
            if(!version.equals(fileVersion))
            {
                throw new IOException("Invalid file version");
            }
            
            this.moes = new Moes(br);
            this.filename = newFileName;
            StringBuilder builder = build("Opened file: "+filename);
            print(""+builder);

        }
        catch(Exception e)
        {
            System.err.println("An unexpected error occurred: "+e.getMessage());
            return;
        }

    }


    private void addStudent()
    {

        String name = getString("Student name? ");

        int id = getInt("Student ID? ");

        String email = getString("Student email? ");

        String type = getString("(a)lacrate or (u)nlimited? ");
        boolean unlimited = true;
        if(type.equalsIgnoreCase("a"))
        {
            unlimited = false;
        }
        else if(type.equalsIgnoreCase("u"))
        {
            unlimited = true;
        }

        Student newStudent = new Student(name, id, email, unlimited);
        moes.addStudent(newStudent);
        StringBuilder builder = build("Added student "+newStudent+".");
        print(""+builder);
        dirty = true;
    }
    private void listStudents()
    {
        StringBuilder builder = build(moes.getStudentList());
        print(""+builder);
    }

    private void addMedia()
    {
        String title = getString("Title? ");

        String url = getString("URL? ");
        
        int points = getInt("Points? ");

        Media media = new Media(title, url, points);
        moes.addMedia(media);
        StringBuilder builder = build("Added media "+media);
        print(""+builder);
        dirty = true;

    }
    private void playMedia()
    {
        System.out.println(moes.getStudentList());

        int student = getInt("Student number? ");
        
        System.out.println(moes.getMediaList());

        int media = getInt("Media number? ");
        StringBuilder builder = build(moes.playMedia(student, media));
        print(""+builder);
        dirty = true;


    }
    private void listMedia()
    {
        StringBuilder builder = build(moes.getMediaList());
        print(""+builder);
    }
    private void listAvailablePoints()
    {

        System.out.println(moes.getStudentList());

        int student = getInt("Student number? ");
        StringBuilder builder = build("Student has: "+moes.getPoints(student)+" points.");
        print(""+builder);
    }
    private void buyPoints()
    {

        System.out.println(moes.getStudentList());

        int student = getInt("Student number? ");

        System.out.println("Student has: "+moes.getPoints(student)+" points.");

        int points = getInt("Points to buy? ");

        moes.buyPoints(student, points);
        StringBuilder builder = build("Student now has "+moes.getPoints(student)+" points added.");
        print(""+builder);
        dirty = true;

    }

    public Main(Moes moes)
    {
        this.menu = new Menu();
        this.output = "";
        this.moes = moes;
        this.running = running;

        menu.addMenuItem(new MenuItem("Exit\n",    () -> endApp()));
        menu.addMenuItem(new MenuItem("Play media",     () -> playMedia()));
        menu.addMenuItem(new MenuItem("List media",     () -> listMedia()));
        menu.addMenuItem(new MenuItem("List available points",      () -> listAvailablePoints()));
        menu.addMenuItem(new MenuItem("Buy Points",     () -> buyPoints()));
        menu.addMenuItem(new MenuItem("Add media\n",      () -> addMedia()));
        menu.addMenuItem(new MenuItem("List all students",      () -> listStudents()));
        menu.addMenuItem(new MenuItem("Add a student\n",      () -> addStudent()));
        menu.addMenuItem(new MenuItem("New MOES",      () -> newMoes()));
        menu.addMenuItem(new MenuItem("Open file",      () -> open()));
        menu.addMenuItem(new MenuItem("Save file",      () -> save()));
        menu.addMenuItem(new MenuItem("Save as new file",      () -> saveAs()));

    }
    public static void main(String[] args)
    {
        Moes moes = new Moes();
        (new Main(moes)).mdi();
    }
    // Show the main menu and return the char selected
    private static String clearScreen = "\n".repeat(255);
    private Integer selectFromMenu() {
        System.out.println(clearScreen + title + menu + '\n' + output);
        output = "";  // Printing the menu is really easy ^^^^
        return getInt("Selection? ");
    }
    private void mdi()
    {
        while(running) {
            try {
                // Select an item from the menu
                Integer i = selectFromMenu();
                if(i == null) continue;
                menu.run(i); // The "dispatch table" is now a single method call!
            } catch (Exception e) {
                print("#### Invalid command");
            } 
        }
    }
    private StringBuilder build(String temp)
    {
        StringBuilder builder = new StringBuilder
        ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"
        +temp+
        "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        return builder;
    }
    // /////////////////////////////////////////////////////////////////////////
    //                          U S E R   O U T P U T
    // Instead of System.out.println, which would put output ABOVE the menu,
    //   collect output in a String field and print it BETWEEN the menu
    //   and the prompt. This looks MUCH nicer!
    private void print(String s) {
        output += s + '\n';
    }
    private void endApp()
    {
        running = false;
    }   
    
    // /////////////////////////////////////////////////////////////////////////
    //                          U S E R   I N P U T
    // Show the prompt and return an Integer (or null)
    public static Integer getInt(String prompt) {
        Integer i = null;
        while(true) {
            try  {
                String s = getString(prompt);
                if(s != null && !s.isEmpty()) i = Integer.parseInt(s);
                break;
            } catch(Exception e) {
                System.err.println("Invalid input!");
            }
        }
        return i;
    }
        // Show the prompt and return a Boolean (or null)
    public static Boolean getBoolean(String prompt) 
    {
        Boolean b = null;
        while(true)
        {
            try  
            {
                String s = getString(prompt);
                if(s != null && !s.isEmpty()) b = Boolean.parseBoolean(s);
                break;
            } 
            catch(Exception e) 
            {
                System.err.println("Invalid input!");
            }
        }
    return b;
    }
    public static String getString(String prompt) 
    {
        String s = null;
        while(true) 
        {
            try  
            {
                System.out.print(prompt);
                s = scan.nextLine().trim();
                break;
            } 
            catch(Exception e) 
            {
                System.err.println("Invalid input!");
            }
        }
        return s;
    }
    public static Character getChar(String prompt) 
    {
        Character c = null;
        while(true) 
        {
            try  
            {
                String s = getString(prompt);
                if(s != null && !s.isEmpty()) c = s.charAt(0);
                break;
            } 
            catch(Exception e) 
            {
                System.err.println("Invalid input!");
            }
        }
        return c;
    }
}
