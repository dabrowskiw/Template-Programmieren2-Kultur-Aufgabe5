package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.*;
import java.util.HashSet;

public class FASTAFileReader implements SequenceFileReader {

    /**
     * Reads the specified FASTA file.
     */
    public SequenceFile readFile(String filename) throws IOException, FileFormatException {
        SequenceFile res = new SequenceFile();
        File infile = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(infile));
        StringBuilder seq = new StringBuilder();
        String line = reader.readLine();
        if(line.charAt(0) != '>') {
            throw new FileFormatException("FASTA File does not start with sequence header line.");
        }
        while((line = reader.readLine()) != null) {
            if(line.length() == 0) {
                continue;
            }
            if(line.charAt(0) == '>') {
                // This can only happen if two sequence headers directly follow each other. This is not valid.
                if(seq.length() == 0) {
                    throw new FileFormatException("Two header lines are directly following each other.");
                }
                res.addSequence(seq.toString());
                seq = new StringBuilder();
            }
            else {
                seq.append(line.strip());
            }
        }
        // This would be the case if the last line in the file was a sequence header. This is not valid.
        if(seq.length() == 0) {
            throw new FileFormatException("The last line is a sequence header.");
        }
        res.addSequence(seq.toString());
        return res;
    }

    public boolean canReadFile(String filename) {
        try {
            File infile = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(infile));
            String line = reader.readLine().strip();
            // FASTA files have to start with a sequence header
            if(!line.startsWith(">") || line.length() < 2) {
                return false;
            }
            line = reader.readLine().strip();
            // The header has to be followed by a non-empty sequence line
            if(!line.matches("[a-zA-Z]+")) {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
