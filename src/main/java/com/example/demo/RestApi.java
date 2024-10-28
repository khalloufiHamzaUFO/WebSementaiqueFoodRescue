package com.example.demo;


import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {

    Model model = JenaEngine.readModel("data/rescuefood.owl");


    @GetMapping("/avion")
    @CrossOrigin(origins = "http://localhost:3000")
    public String afficherAvion() {
        String NS = "";
        if (model != null) {

            NS = model.getNsPrefixURI("");


            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            OutputStream res = JenaEngine.executeQuery(inferedModel, "data/query_SELECT_RestaurantFranchiseLocation.txt");


            System.out.println(res);
            return res.toString();


        } else {
            return ("Error when reading model from ontology");
        }
    }
    //YES
    @GetMapping("/ACKOrder")
    @CrossOrigin(origins = "http://localhost:3000")
    public String afficherBateau() {
        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_ASK_OrderCollected.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_ASK_OrderCollected.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }
    //YES
    @GetMapping("/OrderDetails")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getOrderDetails() {
        if (model != null) {
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_OrderDetails.txt");
            String queryString = FileTool.getContents(queryFile);
            OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
            return res.toString();
        } else {
            return "Error when reading model from ontology";
        }
    }




    @GetMapping("/RestaurantExistence")
    @CrossOrigin(origins = "http://localhost:3000")
    public String restaurantExistence() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_ASK_RestaurantExistence.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            // Check the query type and execute accordingly
            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_ASK_RestaurantExistence.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }

    @GetMapping("/UserDetails")
    @CrossOrigin(origins = "http://localhost:3000")
    public String select_UserDetails() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_UserDetails.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            // Check the query type and execute accordingly
            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_Select_UserDetails.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }

    @GetMapping("/AdminUserDetails")
    @CrossOrigin(origins = "http://localhost:3000")
    public String selectAdminUserDetails() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_AdminUserDetails.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            // Check the query type and execute accordingly
            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_Select_AdminUserDetails.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }

    @GetMapping("/RestaurantFranchiseDetails")
    @CrossOrigin(origins = "http://localhost:3000")
    public String selectRestaurantFranchiseDetails() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_RestaurantFranchiseDetails.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            // Check the query type and execute accordingly
            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_Select_RestaurantFranchiseDetails.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }

    @GetMapping("/FoodDetails")
    @CrossOrigin(origins = "http://localhost:3000")
    public String selectFoodDetails() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_FoodDetails.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            // Check the query type and execute accordingly
            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_Select_FoodDetails.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }

    @GetMapping("/OrderUserFoodDetails")
    @CrossOrigin(origins = "http://localhost:3000")
    public String selectOrderUserFoodDetails() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_OrderUserFoodDetails.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_Select_OrderUserFoodDetails.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }

    @GetMapping("/UserCommentsOnFood")
    @CrossOrigin(origins = "http://localhost:3000")
    public String selectUserCommentsOnFood() {
        String NS = "";
        if (model != null) {
            NS = model.getNsPrefixURI("");
            Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");

            // Load query as string
            File queryFile = new File("data/query_Select_UserCommentsOnFood.txt");
            String queryString = FileTool.getContents(queryFile);
            Query query = QueryFactory.create(queryString);

            if (query.isAskType()) {
                boolean result = JenaEngine.executeAskQueryFile(inferedModel, "data/query_Select_UserCommentsOnFood.txt");
                return Boolean.toString(result);
            } else {
                OutputStream res = JenaEngine.executeQuery(inferedModel, queryString);
                System.out.println(res);
                return res.toString();
            }
        } else {
            return "Error when reading model from ontology";
        }
    }




}






