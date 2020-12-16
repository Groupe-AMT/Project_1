package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class Badge implements Serializable {

    private long id;

    private String name;
    private String image;
}
