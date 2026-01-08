package com.visa.engine;

import com.visa.config.RuleConfig;
import com.visa.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VisaRuleEvaluator {
    private final RuleRepository repository;

    public VisaRuleEvaluator(RuleRepository repository) {
        this.repository = repository;
    }

    public VisaDecision evaluate(Country from, Country to, TravelPurpose purpose, int durationDays) {

        if (from == null || to == null || purpose == null) {
            return new VisaDecision(false, VisaType.NONE, null, 0,
                    Collections.singletonList("Invalid Input: Missing mandatory fields"));
        }

        if (durationDays < 0) {
            return new VisaDecision(false, VisaType.NONE, null, 0,
                    Collections.singletonList("Invalid Input: Duration cannot be negative"));
        }

        if ((from == Country.INDIA && to == Country.PAKISTAN) ||
                (from == Country.PAKISTAN && to == Country.INDIA)) {
            return new VisaDecision(false, VisaType.NONE, null, 0,
                    Collections.singletonList("TRAVEL BAN: Travel between India and Pakistan is restricted."));
        }

        List<RuleConfig> matchingRules = new ArrayList<>();
        for (RuleConfig rule : repository.findAll()) {
            if (rule.getFrom() == from &&
                    rule.getTo() == to &&
                    rule.getPurpose() == purpose &&
                    rule.getMaxDays() >= durationDays) {
                matchingRules.add(rule);
            }
        }

        if (matchingRules.isEmpty()) {
            return new VisaDecision(true, VisaType.NONE, null, 0, Collections
                    .singletonList("No applicable visa rule found for this criteria. Please check manual consulates."));
        }

        matchingRules.sort(Comparator.comparingInt(RuleConfig::getMaxDays));
        RuleConfig rule = matchingRules.get(0);

        return new VisaDecision(true, rule.getVisaType(), rule.getDocuments(), rule.getProcessingDays(),
                Collections.emptyList());
    }
}
