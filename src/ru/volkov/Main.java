package ru.volkov;

import ru.volkov.model.Mediator;
import ru.volkov.model.competentions.JavaProgramming;
import ru.volkov.model.competentions.ServerSetter;
import ru.volkov.model.model.*;

public class Main {

    public static void main(String[] a) {

        Mediator mediator = new Mediator();

        mediator.addSpecialist(new SingleSpecialist("Vasya", 50, new JavaProgramming()));

        JobContractor contractor = new ITCompany("VeloInventors");
        contractor.addCompetence(new JavaProgramming());
        contractor.addCompetence(new ServerSetter());

        mediator.addContractor(contractor);
        mediator.addSpecialist(new SingleSpecialist("Petya", 50, new JavaProgramming()));
        mediator.addSpecialist(new SingleSpecialist("Vanya", 50, new ServerSetter()));

        Job job = new JobToDo(new JavaProgramming(), new ServerSetter());

        mediator.makeAJob(job);

        mediator.shutDown();
    }
}