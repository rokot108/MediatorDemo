package ru.volkov.model.model;

import ru.volkov.model.competentions.Competence;

import java.util.HashMap;

public interface Job {

    HashMap<Competence, Boolean> getCompetencesDone();

    void addCompetence(Competence competence);

    boolean isDone();
}
