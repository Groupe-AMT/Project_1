package ch.heigvd.amt.projet1.application.apimanagement;

import lombok.Builder;

import java.io.Serializable;

@Builder
class RuleIf{
    private String action;
    private String attribute;
}

@Builder
class RuleThen{
    private String pointscale;
    private int amount;
}

@Builder
public class Rule implements Serializable {
    private String name;
    private String badge;
    private RuleIf _if;
    private RuleThen then;
}
