package org.htw.prog2.aufgabe1.analysis;

import org.htw.prog2.aufgabe1.files.Mutation;
import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.util.HashMap;

public class FullLengthSequenceAnalysis extends SequenceAnalysis {

    public FullLengthSequenceAnalysis(String reference, SequenceFile sequences, MutationFile mutations) {
        super(reference, sequences, mutations);
    }

    public void calculateErrorOdds() {
    }

}
