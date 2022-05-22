package org.htw.prog2.aufgabe1.files;

import java.util.HashSet;

public class SequenceFile implements SiteclassificationFile {
    HashSet<String> seqs = new HashSet<>();
    String firstSeq = "";

    public void addSequence(String sequence) {
        if(seqs.isEmpty()) {
            firstSeq = sequence;
        }
        seqs.add(sequence);
    }

    public int getNumberOfSequences() {
        return seqs.size();
    }

    public HashSet<String> getSequences() {
        return seqs;
    }

    public String getFirstSequence() {
        return firstSeq;
    }

    public boolean containsSequence(String sequence) {
        return seqs.contains(sequence);
    }
}
