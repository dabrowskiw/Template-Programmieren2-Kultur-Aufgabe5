package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FASTQFileReader implements SequenceFileReader {

    private enum SeqState {
        HEADER,
        SEQUENCE,
        SEPARATOR,
        QUALITIES
    }

    /**
     * Reads the specified FASTA file.
     */
    public SequenceFile readFile(String filename) throws IOException, FileFormatException {
        SequenceFile res = new SequenceFile();
        File infile = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(infile));
        StringBuilder seq = new StringBuilder();
        String line = reader.readLine();
        if(line.charAt(0) != '@') {
            throw new FileFormatException("FASTQ File does not start with sequence header line.");
        }
        SeqState state = SeqState.SEQUENCE;
        while((line = reader.readLine()) != null) {
            if(line.charAt(0) == '@' && state == SeqState.HEADER) {
                // This can only happen if two sequence headers directly follow each other. This is not valid.
                if(seq.length() == 0) {
                    throw new FileFormatException("Two header lines are directly following each other.");
                }
                res.addSequence(seq.toString());
                seq = new StringBuilder();
                state = SeqState.SEQUENCE;
            }
            else if(state == SeqState.SEQUENCE){
                seq.append(line.strip());
                state = SeqState.SEPARATOR;
            }
            else if(state == SeqState.SEPARATOR){
                if(!line.startsWith("+")) {
                    throw new FileFormatException("Sequence and qualities must be separated by a line starting with +");
                }
                state = SeqState.QUALITIES;
            }
            else if(state == SeqState.QUALITIES) {
                state = SeqState.HEADER;
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
            // FASTQ files have to start with a sequence header
            if(!line.startsWith("@") || line.length() < 2) {
                return false;
            }
            line = reader.readLine().strip();
            // The header has to be followed by a non-empty sequence line
            if(!line.matches("[a-zA-Z]+")) {
                return false;
            }
            int seqlength = line.length();
            line = reader.readLine().strip();
            // The sequence line has to be followed by a line starting with +
            if(!line.startsWith("+")) {
                return false;
            }
            line = reader.readLine().strip();
            // The quality line length has to have the same length as the sequence line length
            if(line.length() != seqlength) {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
