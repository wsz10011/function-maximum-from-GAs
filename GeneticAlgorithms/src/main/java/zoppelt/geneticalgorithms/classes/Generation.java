package zoppelt.geneticalgorithms.classes;

import java.util.ArrayList;
import java.util.List;

import net.objecthunter.exp4j.Expression;
import zoppelt.geneticalgorithms.classes.selection.SelectionMethod;

public class Generation {
	private List<Chromosome> chromosomes;
	private int generationNumber;
	private int populationSize;
	private int lengthOfChromosome;
	
	public Generation() {
		setGenerationNumber(-1);
	}
	
	public Generation(int generationNumber, int populationSize, int lengthOfChromsome) {
		this.generationNumber = generationNumber;
		this.populationSize = populationSize;
		this.lengthOfChromosome = lengthOfChromsome;
		setChromosomes(generateNewPopulation());
	}
	
	public List<Chromosome> getChromosomes() {
		return chromosomes;
	}
	public void setChromosomes(List<Chromosome> chromosomes) {
		this.chromosomes = chromosomes;
	}
	
	public int getGenerationNumber() {
		return generationNumber;
	}
	public void setGenerationNumber(int generationNumber) {
		this.generationNumber = generationNumber;
	}
	
	public int getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getLengthOfChromosome() {
		return lengthOfChromosome;
	}
	public void setLengthOfChromosome(int lengthOfChromosome) {
		this.lengthOfChromosome = lengthOfChromosome;
	}

	public void addChromosome(Chromosome chromosome) {
		chromosomes.add(chromosome);
	}
	public Chromosome getChromosome(int i) {
		return chromosomes.get(i);
	}
	
	private List<Chromosome> generateNewPopulation() {
		List<Chromosome> newGeneration = new ArrayList<>(populationSize);
		
		//Loop through and n amount of new blank chromosomes
		for(int i = 0; i < populationSize; i++) {
			Chromosome chromosome = new Chromosome();
			chromosome.setName("X" + i);
			chromosome.setLength(lengthOfChromosome);
			newGeneration.add(chromosome);
		}
		
		return newGeneration;
	}
		
	public void calculateFitnessRatio(Expression fitnessFunction) {
		//Calculate fitness for all chromosomes
		double fitnessSum = 0;
		for (int i = 0; i < chromosomes.size(); i++) {
			chromosomes.get(i).setFitness(fitnessFunction);
			fitnessSum += chromosomes.get(i).getFitness();
		}
		
		for (int i = 0; i < chromosomes.size(); i++) {
			chromosomes.get(i).setFitnessRatio((chromosomes.get(i).getFitness() / fitnessSum));
		}	
	}
	
	public void printGeneration() {
		System.out.println("Generation " + generationNumber + ":");
		for(Chromosome chromosome : chromosomes) {
			chromosome.printChromosome();
		}
	}
}
 