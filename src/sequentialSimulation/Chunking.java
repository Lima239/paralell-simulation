package sequentialSimulation;

import java.util.*;

public class Chunking implements Algorithms{
    private ArrayList<Double> tasks;
    private Iterator<Double> iteratorOfTasks;
    private int numberOfProcessors;
    private double latency;
    private ArrayList<Processor> processors;
    private int numberOfTasks;


    public Chunking(ArrayList<Double> tasks, int numberOfProcessors, double latency){
        this.tasks = tasks;
        this.numberOfProcessors = numberOfProcessors;
        this.latency = latency;
        this.iteratorOfTasks =  tasks.iterator();
        this.numberOfTasks = tasks.size();

        processors = new ArrayList<>();
        for (int i = 1; i < numberOfProcessors; i++){
            Processor processor = new Processor(this.latency);
            processors.add(processor);
        }
    }

    private Processor findMin(Map<Processor, Double> map){
        double min = Double.MAX_VALUE;
        Processor minProcessor = processors.get(0);
        for (int i = 1; i < numberOfProcessors; i++){
            if(min > map.get(processors.get(i))){
                min = map.get(processors.get(i));
                minProcessor = processors.get(i);
            }
        }
        return minProcessor;
    }

    @Override
    public void redistributeTasks() {
        Map<Processor, Double> map = new HashMap<>();
        for (int i = 1; i < numberOfProcessors; i++){
            ArrayList<Double> tmpList = new ArrayList<>();
            tmpList.add(iteratorOfTasks.next());
            processors.get(i).read_job(tmpList);
            map.put(processors.get(i), processors.get(i).next_idle());
        }
        while (iteratorOfTasks.hasNext()) {
            ArrayList<Double> tmpList = new ArrayList<>();
            tmpList.add(iteratorOfTasks.next());
            map.put(findMin(map), findMin(map).next_idle() + map.get(findMin(map)));
        }
    }
}
