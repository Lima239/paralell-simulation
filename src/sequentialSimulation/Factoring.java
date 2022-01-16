package sequentialSimulation;

import java.util.ArrayList;
import java.util.Iterator;

public class Factoring implements Algorithms{
    private ArrayList<Double> tasks;
    private Iterator<Double> iteratorOfTasks;
    private int numberOfProcessors;
    private double latency;
    private ArrayList<Processor> processors;
    private float factor;

    public Factoring(ArrayList<Double> tasks, int numberOfProcessors, double latency, float factor){
        this.tasks = tasks;
        this.numberOfProcessors = numberOfProcessors;
        this.latency = latency;
        this.iteratorOfTasks =  tasks.iterator();
        this.factor = factor;

        processors = new ArrayList<>();
        for (int i = 1; i < numberOfProcessors; i++){
            Processor processor = new Processor(this.latency);
            processors.add(processor);
        }
    }

    @Override
    public void redistributeTasks() {

    }
}
