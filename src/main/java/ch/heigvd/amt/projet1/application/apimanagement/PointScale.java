package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class PointScale implements Serializable { //fields put to public because field.get() causes exception otherwise
    public String name;
    public int scale;
}
