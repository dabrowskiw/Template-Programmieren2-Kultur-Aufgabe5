package org.htw.prog2.aufgabe1.analysis;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.exceptions.NoValidReadersException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SequenceAnalysisManagerTest {

    @Test
    void performAnalysis_throwsWrongFile() {
        assertThrows(FileFormatException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                SequenceAnalysisManager.performAnalysis("data/GREB1_reference.fasta",
                        "data/site_sequences.fasta",
                        "data/GREB1_patterns_wrongHeader.csv");
            }
        });
    }

    @Test
    void performAnalysis() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                SequenceAnalysis analysis = SequenceAnalysisManager.performAnalysis("data/GREB1_reference.fasta",
                        "data/site_sequences_chimp.fasta",
                        "data/GREB1_patterns.csv");
                assertEquals("Chimp", analysis.getMostLikelyOrganism());
                assertEquals(3.162277660168378E-30, analysis.getBestErrorOdds(), 0.00001);
            }
        });
    }
}