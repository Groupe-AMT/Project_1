package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class EntityAPIResultsByUser implements Serializable {
    String name;
    Object result;
}
