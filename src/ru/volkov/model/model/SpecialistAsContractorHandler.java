package ru.volkov.model.model;

import ru.volkov.model.competentions.Competence;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class SpecialistAsContractorHandler implements InvocationHandler {

    private SingleSpecialist specialist;

    public SpecialistAsContractorHandler(SingleSpecialist specialist) {
        super();
        this.specialist = specialist;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        switch (method.getName()) {
            case "doTheJob":
                Job job = (Job) args[0];
                specialist.makeAnOperation(job);
            case "addCompetence":
                addCompetence((Competence) args[0]);
                break;
            case "getCompetences":
                return getCompetences();
            case "signAContract":
                break;
            case "haveVacancy":
                return false;
            case "getName":
                return specialist.getName();
        }

        return null;
    }

    public void addCompetence(Competence competence) {
        specialist.setCompetence(competence);
    }

    public HashMap<Competence, Boolean> getCompetences() {
        HashMap<Competence, Boolean> competences = new HashMap<>();
        competences.put(specialist.getCompetence(), true);
        return competences;
    }
}
