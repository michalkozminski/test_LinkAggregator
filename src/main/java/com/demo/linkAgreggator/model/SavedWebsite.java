package com.demo.linkAgreggator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "savedWebsites")
public class SavedWebsite {
    @Id
    public String id;

    @NotBlank
    public String url;

    @NotNull
    private Date createdAt = new Date();

    public SavedWebsite() {

    }

    public SavedWebsite(String url) {
        this.url = url;
    }

}
