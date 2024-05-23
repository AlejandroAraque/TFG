package com.example.tfg.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class RequestedAccessRequestDTO {

    private String id;
    private String datasetId;
    private String datasetName;
    private String datasetDescription;
    private BigDecimal datasetPrice;
    private Date datasetDate;
    private String datasetAccessTerms;
    private String datasetTermsOfUse;
    private String status;
    private String message;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public BigDecimal getDatasetPrice() {
        return datasetPrice;
    }

    public void setDatasetPrice(BigDecimal datasetPrice) {
        this.datasetPrice = datasetPrice;
    }

    public Date getDatasetDate() {
        return datasetDate;
    }

    public void setDatasetDate(Date datasetDate) {
        this.datasetDate = datasetDate;
    }

    public String getDatasetAccessTerms() {
        return datasetAccessTerms;
    }

    public void setDatasetAccessTerms(String datasetAccessTerms) {
        this.datasetAccessTerms = datasetAccessTerms;
    }

    public String getDatasetTermsOfUse() {
        return datasetTermsOfUse;
    }

    public void setDatasetTermsOfUse(String datasetTermsOfUse) {
        this.datasetTermsOfUse = datasetTermsOfUse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
