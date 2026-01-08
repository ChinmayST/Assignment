package com.visa.config;

import com.visa.model.Country;
import com.visa.model.DocumentType;
import com.visa.model.TravelPurpose;
import com.visa.model.VisaType;

import java.util.List;

public class RuleConfig {
    private Country from;
    private Country to;
    private TravelPurpose purpose;
    private int maxDays;

    private VisaType visaType;
    private List<DocumentType> documents;
    private int processingDays;

    public Country getFrom() {
        return from;
    }

    public void setFrom(Country from) {
        this.from = from;
    }

    public Country getTo() {
        return to;
    }

    public void setTo(Country to) {
        this.to = to;
    }

    public TravelPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(TravelPurpose purpose) {
        this.purpose = purpose;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public VisaType getVisaType() {
        return visaType;
    }

    public void setVisaType(VisaType visaType) {
        this.visaType = visaType;
    }

    public List<DocumentType> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentType> documents) {
        this.documents = documents;
    }

    public int getProcessingDays() {
        return processingDays;
    }

    public void setProcessingDays(int processingDays) {
        this.processingDays = processingDays;
    }
}
