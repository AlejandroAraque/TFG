package com.example.tfg.Service;

import com.example.tfg.Model.Dataset;

public class DatasetWithUsername {
    private Dataset dataset;
    private String username;

    public DatasetWithUsername(Dataset dataset, String username) {
        this.dataset = dataset;
        this.username = username;
    }

    // Getters y setters
    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
