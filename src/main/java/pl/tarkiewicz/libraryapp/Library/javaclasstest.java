package pl.tarkiewicz.libraryapp.Library;

import pl.tarkiewicz.libraryapp.Library.Service.LibraryService;

public class javaclasstest {

    public static void main(String[] args) {
        LibraryService l = new LibraryService();
        System.out.println(l.getLibraryByUserId(1L));



    }
}
