package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.Mutation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CSVFileReader implements MutationFileReader {

    public MutationFile readFile(String filename) throws IOException, FileFormatException {
        MutationFile res = new MutationFile();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            // Ignore empty lines
            if(line.strip().length() == 0) {
                continue;
            }
            // Ignore lines starting with #
            if(line.charAt(0) == '#')
                continue;
            if(line.startsWith("\"Mutation Pattern\"")) {
                for(String organism : parseOrganisms(line)) {
                    res.addOrganism(organism);
                }
                firstLine = false;
                continue;
            }
            // If this happens, then the first line was not a definition line.
            if(firstLine) {
                throw new FileFormatException("The first line of the mutation patterns CSV file must be a definition line.");
            }
            String[] data = line.split(";");
            if(1 + res.getOrganisms().size() != data.length) {
                throw new FileFormatException("Each line must have the same number of columns.");
            }
            if(data.length < 3)
                continue;
            else {
                String pattern = data[0];
                HashMap<String, Double> sus = parseGSScores(data, res.getOrganisms());
                res.addMutation(new Mutation(pattern, sus));
            }
        }
        return res;
    }

    /**
     * Parst die Definitionszeile.
     * @param line Definitionszeile aus der CSV-Datei
     * @return Liste der Organismennamen aus der Definitionszeile
     */
    public static List<String> parseOrganisms(String line) throws FileFormatException {
        LinkedList<String> res = new LinkedList<>();
        String[] data = line.split(";");
        if(!data[0].equals("\"Mutation Pattern\"")) {
            throw new FileFormatException("First element of definition line should be \"Mutation Pattern\"");
        }
        for(int i=1; i<data.length; i++) {
            String organism = data[i];
            if(!organism.endsWith(" GS\"")) {
                throw new FileFormatException("All organism definitions in first line must end with \"GS\"\".");
            }
            // Der Organismenname steht vor dem Leerzeichen und hinter dem Anführungszeichen -
            // raussplitten und speichern.
            res.add(organism.split(" ")[0].split("\"")[1]);
        }
        return res;
    }

    public static HashMap<String, Double> parseGSScores(String[] data, List<String> organisms) {
        HashMap<String, Double> res = new HashMap<>();
        for(int i = 1; i< data.length; i++) {
            String organism = organisms.get(i-1);
            try {
                res.put(organism, Double.parseDouble(data[i]));
            } catch(NumberFormatException e) {
                res.put(organism, -1d);
            }
        }
        return res;
    }

    public boolean canReadFile(String filename) {
        // Any text file could theoretically be a CSV file. Extend this in case further formats come in.
        return true;
    }
}
