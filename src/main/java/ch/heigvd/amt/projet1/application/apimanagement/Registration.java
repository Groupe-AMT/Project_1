package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class Registration implements Serializable {
    public String name;
    public String description;
    public String contact;
}
