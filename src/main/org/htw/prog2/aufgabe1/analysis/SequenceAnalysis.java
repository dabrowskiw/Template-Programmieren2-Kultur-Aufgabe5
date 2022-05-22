package org.htw.prog2.aufgabe1.analysis;

import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.util.HashMap;

public abstract class SequenceAnalysis {

    public SequenceAnalysis(String reference, SequenceFile sequences, MutationFile mutations) {
    }

    public abstract void calculateErrorOdds();

    public HashMap<String, Double> getErrorOdds() {
        return null;
    }

    public String getMostLikelyOrganism() {
        return "";
    }

    public String getOrganismDescriptions() {
        return "";
    }

    public SequenceFile getSequences() {
        return null;
    }

    public MutationFile getMutations() {
        return null;
    }

    public String getReference() {
        return "";
    }

    public double getBestErrorOdds() {
        return 0;
    }
}
