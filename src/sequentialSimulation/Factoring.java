package sequentialSimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Factoring implements Algorithms{
    private ArrayList<Double> tasks;
    private Iterator<Double> iteratorOfTasks;
    private int numberOfProcessors;
    private double latency;
    private ArrayList<Processor> processors;
    private double factor;
    private int numberOfTasks;

    public Factoring(ArrayList<Double> tasks, int numberOfProcessors, double latency, double factor){
        this.tasks = tasks;
        this.numberOfProcessors = numberOfProcessors;
        this.numberOfTasks = tasks.size();
        this.latency = latency;
        this.iteratorOfTasks =  tasks.iterator();
        this.factor = factor;

        processors = new ArrayList<>();
        for (int i = 0; i < numberOfProcessors; i++){
            Processor processor = new Processor(this.latency);
            processors.add(processor);
        }
    }

    private Processor findMin(Map<Processor, Double> map){
        double min = Double.MAX_VALUE;
        Processor minProcessor = processors.get(0);
        for (int i = 0; i < numberOfProcessors; i++){
            if(min > map.get(processors.get(i))){
                min = map.get(processors.get(i));
                minProcessor = processors.get(i);
            }
        }
        return minProcessor;
    }

    private Double findMax(Map<Processor, Double> map){
        double max = Double.MIN_VALUE;
        Processor maxProcessor = processors.get(0);
        for (int i = 0; i < numberOfProcessors; i++){
            if(max < map.get(processors.get(i))){
                max = map.get(processors.get(i));
                maxProcessor = processors.get(i);
            }
        }
        return map.get(maxProcessor);
    }

    @Override
    public Double redistributeTasks() {
        Map<Processor, Double> map = new HashMap<>();

        for(int i = 0; i < numberOfProcessors; i++){
            map.put(processors.get(i), 0.0);
        }

        int K;
        int counter;

        while(numberOfTasks > 0) {
            K = Math.max((int) Math.floor(numberOfTasks / factor), 1);
            counter = 0;
            while (counter < numberOfProcessors) {
                if(iteratorOfTasks.hasNext()) {
                    counter++;
                    ArrayList<Double> listOfKTasks = new ArrayList<>();
                    for (int i = 0; i < K; i++){
                        listOfKTasks.add(iteratorOfTasks.next());
                        numberOfTasks--;
                    }
                    Processor p = findMin(map);
                    Double value = map.get(p);
                    p.read_job(listOfKTasks);
                    map.put(p, value + p.executeJob());
                } else {
                    break;
                }
            }
        }

        return findMax(map);
    }
}
