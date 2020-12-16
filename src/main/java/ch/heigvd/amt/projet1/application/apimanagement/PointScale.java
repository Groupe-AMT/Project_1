package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class PointScale implements Serializable {
    private String name;
    private int scale;
}
