package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.SiteclassificationFile;

import java.io.IOException;

public interface SiteclassificationFileReader {
    /**
     * Reads a file and returns an HIVFile representing its contents.
     * @param filename Filename to read
     * @return HIVFile instance containing the relevant information from the file.
     * @throws IOException If the file does not exist or any IO error occurred.
     * @throws FileFormatException If something is wrong with the file's format.
     */
    public SiteclassificationFile readFile(String filename) throws IOException, FileFormatException;

    /**
     * Check whether this reader can parse the given file.
     * @param filename Filename of file to be checked.
     * @return True if this reader can read the given file, false otherwise.
     */
    public boolean canReadFile(String filename);
}
