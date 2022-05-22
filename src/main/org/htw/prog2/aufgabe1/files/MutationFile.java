package org.htw.prog2.aufgabe1.files;

import java.util.LinkedList;

public class MutationFile implements SiteclassificationFile {
    LinkedList<String> organisms = new LinkedList<>();
    LinkedList<Mutation> mutations = new LinkedList<>();

    public void addOrganism(String organism) {
        organisms.add(organism);
    }

    public LinkedList<String> getOrganisms() {
        return organisms;
    }

    public void addMutation(Mutation variant) {
        mutations.add(variant);
    }

    public LinkedList<Mutation> getMutations() {
        return mutations;
    }

    public int getNumberOfMutations() {
        return mutations.size();
    }
}
