package org.htw.prog2.aufgabe1.analysis;

import org.htw.prog2.aufgabe1.files.Mutation;
import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FullLengthSequenceAnalysisTest {
    static MutationFile mutationFile;
    static String reference;

    @BeforeAll
    static void init() {
        mutationFile = new MutationFile();
        mutationFile.addOrganism("D1");
        mutationFile.addOrganism("D2");
        mutationFile.addOrganism("D3");
        HashMap<String, Double> gs = new HashMap<>();
        gs.put("D1", 12.5);
        gs.put("D2", 2.5);
        gs.put("D3", 1.0);
        mutationFile.addMutation(new Mutation("2M", gs));
        gs = new HashMap<>();
        gs.put("D1", 10.5);
        gs.put("D2", 20.0);
        gs.put("D3", 1.0);
        mutationFile.addMutation(new Mutation("2L,3M", gs));
        reference = "MKYLM";
    }

    @Test
    void calculateResistancesNoResistances() {
        SequenceFile sequenceFile = new SequenceFile();
        sequenceFile.addSequence("MKYLM");
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("D1", 1.0);
        expected.put("D2", 1.0);
        expected.put("D3", 1.0);
        assertEquals(expected, new FullLengthSequenceAnalysis(reference, sequenceFile, mutationFile).getErrorOdds());
    }

    //TODO: Test rounded values, not exact.

    @Test
    void calculateResistancesOneResistance() {
        SequenceFile sequenceFile = new SequenceFile();
        sequenceFile.addSequence("MKYLM");
        sequenceFile.addSequence("MMYLM");
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("D1", 0.05623413251903491);
        expected.put("D2", 0.5623413251903491);
        expected.put("D3", 0.7943282347242815);
        HashMap<String, Double> result = new FullLengthSequenceAnalysis(reference, sequenceFile, mutationFile).getErrorOdds();
        for(String org : expected.keySet()) {
            assertEquals(expected.get(org), result.get(org), 0.001);
        }
    }

    @Test
    void calculateResistancesTwoResistances() {
        SequenceFile sequenceFile = new SequenceFile();
        sequenceFile.addSequence("MKYLM");
        sequenceFile.addSequence("MMYLM");
        sequenceFile.addSequence("MLMLM");
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("D1", 0.005011872336272723);
        expected.put("D2", 0.005623413251903491);
        expected.put("D3", 0.6309573444801932);
        HashMap<String, Double> result = new FullLengthSequenceAnalysis(reference, sequenceFile, mutationFile).getErrorOdds();
        for(String org : expected.keySet()) {
            assertEquals(expected.get(org), result.get(org), 0.001);
        }
    }
}