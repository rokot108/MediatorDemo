package ru.volkov.model.model;

import ru.volkov.model.competentions.Competence;

import java.util.HashMap;

public interface JobContractor {

    String getName();

    void addCompetence(Competence competence);

    HashMap<Competence, Boolean> getCompetences();

    void signAContract();

    void doTheJob(Job job);

    boolean haveVacancy(SingleSpecialist specialist);

    void hireSpecialist(SingleSpecialist specialist);

    void fireSpecialist(SingleSpecialist specialist);
}
