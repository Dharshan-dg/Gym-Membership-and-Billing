package model;

public class Trainer {
    private int trainerId;
    private String name;
    private String specialty;

    public Trainer(int trainerId, String name, String specialty) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialty = specialty;
    }

    public int getTrainerId() { return trainerId; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    @Override
    public String toString() {
        return trainerId + " - " + name + " (" + specialty + ")";
    }
}
