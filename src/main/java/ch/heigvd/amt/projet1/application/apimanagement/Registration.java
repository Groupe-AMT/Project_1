package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class Registration implements Serializable {
    private String name;
    private String description;
    private String contact;
}
