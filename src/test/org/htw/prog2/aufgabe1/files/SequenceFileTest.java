package org.htw.prog2.aufgabe1.files;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceFileTest {

    private static SequenceFile sequenceFile;

    @BeforeAll
    static void init() {
        sequenceFile = new SequenceFile();
        sequenceFile.addSequence("AGTTGATG");
        sequenceFile.addSequence("AGTTGATG");
        sequenceFile.addSequence("AGTTGAGG");
        sequenceFile.addSequence("CCTTGATG");
    }

    @Test
    void containsSequence() {
        assertTrue(sequenceFile.containsSequence("AGTTGATG"));
        assertTrue(sequenceFile.containsSequence("AGTTGAGG"));
        assertTrue(sequenceFile.containsSequence("CCTTGATG"));
        assertFalse(sequenceFile.containsSequence("AGTTGATGG"));
        assertFalse(sequenceFile.containsSequence(""));
    }
}