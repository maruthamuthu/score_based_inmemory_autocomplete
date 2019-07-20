package com.mm;


import com.mm.sample.SamplePairs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AutoCompleteAPI
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {

        AutoComplete autoComplete = readObject();

        if (autoComplete == null)
        {
            System.out.println("Creating new AutoComplete object");
            autoComplete = new AutoComplete();
            autoComplete.init(SamplePairs.getSamplePairs());
            writeObject(autoComplete);
            System.out.println("Completed writing AutoComplete object");
        }

        System.out.println("Search a:: " + autoComplete.search("a"));
        System.out.println("Search a_b:: " + autoComplete.search("a_b"));
        System.out.println("Search a_:: " + autoComplete.search("a_"));
        System.out.println("Search ab:: " + autoComplete.search("ab"));

    }

    private static void writeObject(AutoComplete autoComplete) throws IOException
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("./target/Autocomple.obj");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(autoComplete);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException ex)
        {
            System.out.println("Unable witite a object to file, due to:" + ex.getMessage());
            throw ex;
        }

    }

    private static AutoComplete readObject() throws IOException, ClassNotFoundException
    {
        try
        {
            File file = new File("./target/Autocomple.obj");
            if (file.exists())
            {
                System.out.println("Reading existing AutoComplete object.");
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                return  (AutoComplete) objectInputStream.readObject();
            }
            return null;
        }
        catch (IOException ex)
        {
            System.out.println("Unable witite a object to file, due to:" + ex.getMessage());
            throw ex;
        }
        catch (ClassNotFoundException classEx)
        {
            System.out.println("Unable to find the target class for creating object");
            throw classEx;
        }

    }
}
