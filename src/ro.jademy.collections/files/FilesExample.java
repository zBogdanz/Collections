package files;

import java.io.File;
import java.io.IOException;

public class FilesExample {
    public static void main(String[] args) {

        File f =new File("resources/contacts.txt");
        f.getParentFile ().mkdirs ();//java adauga si restul pathului
        System.out.println ("Absolute path of file is "+f.getAbsolutePath ());//vedem unde va creea fisierul sau unde este deja
        System.out.println ("Does the file exist? "+f.exists ());//verificam daca exista fisierul
        try {
            f.createNewFile ();//se creeaza fisierul
        } catch(IOException ex){
            System.out.println ("Could  not create file");
        }
        System.out.println ("Does it exist now? "+f.exists () );
        f.deleteOnExit ();

    }
}
