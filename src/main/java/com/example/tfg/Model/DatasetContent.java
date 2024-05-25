package com.example.tfg.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "datasetContent")
public class DatasetContent {
    @Id
    private String id;
    private String Datasetcontenido;
    private String fileType;

    public DatasetContent(String id, String Datasetcontenido,  String fileType ) {
        this.id = id;
        this.Datasetcontenido = Datasetcontenido;
        this.fileType = fileType;

    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatasetcontenido() {
        return Datasetcontenido;
    }

    public void setDatasetcontenido(String datasetcontenido) {
        this.Datasetcontenido = datasetcontenido;
    }
}
