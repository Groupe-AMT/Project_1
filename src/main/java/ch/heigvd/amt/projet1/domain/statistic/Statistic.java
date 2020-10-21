package ch.heigvd.amt.projet1.domain.statistic;

import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Statistic {
    // Variables
    protected int nbUsers;
    protected int nbQuestions;
    protected int nbAnswers;
    protected int nbComments;

    protected int nbSelfQuestions;
    protected int nbSelfAnswers;
    protected int nbSelfComments;

    public static class StatisticBuilder {
        public Statistic build() {
            return new Statistic(nbUsers, nbQuestions, nbAnswers, nbComments, nbSelfQuestions, nbSelfAnswers, nbSelfComments);
        }
    }
}
