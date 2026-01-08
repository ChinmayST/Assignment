package com.visa.engine;

import com.visa.config.RuleConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RuleRepository {
    private final List<RuleConfig> rules = new ArrayList<>();

    public void addRules(List<RuleConfig> newRules) {
        if (newRules != null) {
            this.rules.addAll(newRules);
        }
    }

    public List<RuleConfig> findAll() {
        return Collections.unmodifiableList(rules);
    }
}
