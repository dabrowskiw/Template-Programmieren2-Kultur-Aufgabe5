package org.htw.prog2.aufgabe1.files;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutationTest {

    @Test
    void getSequence() {
        String reference = "PQVTLWQRPIVTIKIGGQLKEALLDTGADDTVLEEMSLPGKWKPKMIGGIGGFIKVRQYDQVSIEICGHKAIGTVLIGPTPVNIIGRNLLTQLGCTLNF";
        Mutation mut = new Mutation("46I,54V,82A,90M", null);
        assertEquals("PQVTLWQRPIVTIKIGGQLKEALLDTGADDTVLEEMSLPGKWKPKIIGGIGGFVKVRQYDQVSIEICGHKAIGTVLIGPTPANIIGRNLMTQLGCTLNF", mut.getSequence(reference));
        mut = new Mutation("82A", null);
        assertEquals("PQVTLWQRPIVTIKIGGQLKEALLDTGADDTVLEEMSLPGKWKPKMIGGIGGFIKVRQYDQVSIEICGHKAIGTVLIGPTPANIIGRNLLTQLGCTLNF", mut.getSequence(reference));
    }
}