package ch.heigvd.amt.projet1.application.votemanagement;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VoteCommand {
    private String author;
    private boolean note;
    private String type;
    private String Id;
}
