package ch.heigvd.amt.projet1.application.commentmanagement;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentCommand {
    private String author;
    private String content;
    private String type;
    private String Id;
}
