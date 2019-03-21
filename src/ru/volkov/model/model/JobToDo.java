package ru.volkov.model.model;

import ru.volkov.model.competentions.Competence;

import java.util.HashMap;

public class JobToDo implements Job {

    private HashMap<Competence, Boolean> competencesDone = new HashMap<>();

    public JobToDo(Competence... competences) {
        for (Competence c : competences) {
            competencesDone.put(c, false);
        }

    }

    @Override
    public void addCompetence(Competence competence) {
        competencesDone.put(competence, false);
    }

    @Override
    public HashMap<Competence, Boolean> getCompetencesDone() {
        return competencesDone;
    }

    @Override
    public boolean isDone() {
        for (Boolean b : competencesDone.values()) {
            if (!b) {
                return b;
            }
        }
        return true;
    }
}
