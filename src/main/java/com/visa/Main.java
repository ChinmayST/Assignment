package com.visa;

import com.visa.config.RuleLoader;
import com.visa.engine.RuleRepository;
import com.visa.engine.VisaRuleEvaluator;
import com.visa.model.Country;
import com.visa.model.TravelPurpose;
import com.visa.model.VisaDecision;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Visa Rule Engine ===");

        RuleRepository repository = new RuleRepository();
        RuleLoader loader = new RuleLoader();

        String rulePath = "src/main/resources/rules.json";
        loader.loadRules(rulePath, repository);

        VisaRuleEvaluator evaluator = new VisaRuleEvaluator(repository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n------------------------------------------------");
                System.out.println("Enter Travel Details (or type 'exit' to quit)");

                System.out.print("Enter Source Country: ");
                String fromStr = scanner.nextLine().trim().toUpperCase();
                if ("EXIT".equals(fromStr))
                    break;
                Country from = Country.valueOf(fromStr);

                System.out.print("Enter Destination Country: ");
                String toStr = scanner.nextLine().trim().toUpperCase();
                Country to = Country.valueOf(toStr);

                System.out.print("Enter Travel Purpose (TOURISM, BUSINESS, STUDY): ");
                String purposeStr = scanner.nextLine().trim().toUpperCase();
                TravelPurpose purpose = TravelPurpose.valueOf(purposeStr);

                System.out.print("Duration (days): ");
                String durationStr = scanner.nextLine().trim();
                int duration = Integer.parseInt(durationStr);

                System.out.println("Evaluating...");
                VisaDecision decision = evaluator.evaluate(from, to, purpose, duration);

                System.out.println("\n>>> VISA DECISION <<<");
                System.out.println("Visa Required: " + decision.isVisaRequired());
                System.out.println("Visa Type: " + decision.getVisaType());
                System.out.println("Documents: " + decision.getRequiredDocuments());
                System.out.println("Processing Time: " + decision.getEstimatedProcessingDays() + " days");
                if (!decision.getWarnings().isEmpty()) {
                    System.out.println("WARNINGS: " + decision.getWarnings());
                }

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Invalid input. Please check spelling (e.g., INDIA, USA). " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
