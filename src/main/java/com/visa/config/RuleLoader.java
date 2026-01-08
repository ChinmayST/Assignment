package com.visa.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visa.engine.RuleRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RuleLoader {
    private final ObjectMapper mapper = new ObjectMapper();

    public void loadRules(String filePath, RuleRepository repository) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.err.println("Warning: Config file not found at " + filePath);
                return;
            }
            List<RuleConfig> rules = mapper.readValue(file, new TypeReference<List<RuleConfig>>() {
            });
            repository.addRules(rules);
            System.out.println("Loaded " + (rules != null ? rules.size() : 0) + " rules from " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load rules config", e);
        }
    }
}
