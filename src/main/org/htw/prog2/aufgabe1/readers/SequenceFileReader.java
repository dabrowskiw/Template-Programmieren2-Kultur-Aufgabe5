package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.IOException;

public interface SequenceFileReader extends SiteclassificationFileReader {
    public SequenceFile readFile(String filename) throws IOException, FileFormatException;
}
