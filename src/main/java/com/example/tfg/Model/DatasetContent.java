package com.example.tfg.Model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "datasetContent")
@AllArgsConstructor
public class DatasetContent {
    @Id
    private String id;
    private String DatasetContent;

    public DatasetContent() {
    }

    public DatasetContent(String datasetContent) {
        DatasetContent = datasetContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasetContent() {
        return DatasetContent;
    }

    public void setDatasetContent(String datasetContent) {
        DatasetContent = datasetContent;
    }
}
