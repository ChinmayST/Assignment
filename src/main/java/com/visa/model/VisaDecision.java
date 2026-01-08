package com.visa.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VisaDecision {
    private final boolean visaRequired;
    private final VisaType visaType;
    private final List<DocumentType> requiredDocuments;
    private final int estimatedProcessingDays;
    private final List<String> warnings;

    public VisaDecision(boolean visaRequired, VisaType visaType, List<DocumentType> requiredDocuments,
            int estimatedProcessingDays, List<String> warnings) {
        this.visaRequired = visaRequired;
        this.visaType = Objects.requireNonNull(visaType, "VisaType cannot be null");
        this.requiredDocuments = (requiredDocuments == null) ? Collections.emptyList()
                : Collections.unmodifiableList(requiredDocuments);
        this.estimatedProcessingDays = estimatedProcessingDays;
        this.warnings = (warnings == null) ? Collections.emptyList() : Collections.unmodifiableList(warnings);
    }

    public boolean isVisaRequired() {
        return visaRequired;
    }

    public VisaType getVisaType() {
        return visaType;
    }

    public List<DocumentType> getRequiredDocuments() {
        return requiredDocuments;
    }

    public int getEstimatedProcessingDays() {
        return estimatedProcessingDays;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    @Override
    public String toString() {
        return "VisaDecision{" +
                "visaRequired=" + visaRequired +
                ", visaType=" + visaType +
                ", requiredDocuments=" + requiredDocuments +
                ", estimatedProcessingDays=" + estimatedProcessingDays +
                ", warnings=" + warnings +
                '}';
    }
}
