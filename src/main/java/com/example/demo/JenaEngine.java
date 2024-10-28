package com.example.demo;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;

public class JenaEngine {

    private static final String RDF = "http://www.rescuefood.org/ontologies/2024/rescue-food-ontology#";

    // Method to load the ontology model from a file
    public static Model readModel(String inputDataFile) {
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(inputDataFile);
        if (in == null) {
            System.out.println("Ontology file: " + inputDataFile + " not found");
            return null;
        }
        model.read(in, "");
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Error closing input stream for ontology file.");
            return null;
        }
        return model;
    }

    // Method to create an inferred model based on rule file
    public static Model readInferencedModelFromRuleFile(Model model, String inputRuleFile) {
        InputStream in = FileManager.get().open(inputRuleFile);
        if (in == null) {
            System.out.println("Rule File: " + inputRuleFile + " not found");
            return null;
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Error closing input stream for rule file.");
            return null;
        }
        List<Rule> rules = Rule.rulesFromURL(inputRuleFile);
        GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
        reasoner.setOWLTranslation(true);
        reasoner.setTransitiveClosureCaching(true);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);
        return inf;
    }

    // Simplified executeQuery method to handle only SELECT and ASK queries
    public static OutputStream executeQuery(Model model, String queryString) {
        Query query = QueryFactory.create(queryString);
        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }
            public String toString() {
                return this.string.toString();
            }
        };

        try (QueryExecution qe = QueryExecutionFactory.create(query, model)) {
            if (query.isAskType()) {
                boolean result = qe.execAsk();
                output.write((result ? "true" : "false").getBytes());
            } else if (query.isSelectType()) {
                ResultSet results = qe.execSelect();
                ResultSetFormatter.outputAsJSON(output, results);
            } else {
                System.out.println("Unsupported query type. Only SELECT and ASK queries are allowed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    // Execute an ASK query from a file
    public static boolean executeAskQueryFile(Model model, String filepath) {
        File queryFile = new File(filepath);
        InputStream in = FileManager.get().open(filepath);
        if (in == null) {
            System.out.println("Query file: " + filepath + " not found");
            return false;
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String queryString = FileTool.getContents(queryFile);
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qe = QueryExecutionFactory.create(query, model)) {
            return query.isAskType() && qe.execAsk();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
