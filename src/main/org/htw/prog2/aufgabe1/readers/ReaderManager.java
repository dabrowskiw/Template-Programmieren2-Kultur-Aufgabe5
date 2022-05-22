package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.NoValidReadersException;

import java.util.LinkedList;

public class ReaderManager<T extends SiteclassificationFileReader> {
    private LinkedList<T> readers = new LinkedList<>();

    public void addReader(T reader) {
        readers.add(reader);
    }

    public T getReaderForFile(String filename) throws NoValidReadersException {
        for(T reader : readers) {
            try {
                if(reader.canReadFile(filename)) {
                    return reader;
                }
            } catch(Exception e) { }
        }
        throw new NoValidReadersException("Cannot read format of file " + filename);
    }
}
