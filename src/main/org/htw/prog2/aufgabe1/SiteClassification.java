package org.htw.prog2.aufgabe1;
import org.apache.commons.cli.*;
import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;
import org.htw.prog2.aufgabe1.readers.*;
import org.htw.prog2.aufgabe1.ui.SiteClassificationCLI;
import org.htw.prog2.aufgabe1.ui.SiteClassificationGUI;

public class SiteClassification {

    public static void main(String[] args) {
        if(args.length == 0) {
            SiteClassificationGUI gui = new SiteClassificationGUI();
        }
        else {
            SiteClassificationCLI cli = new SiteClassificationCLI(args);
        }
    }
}
