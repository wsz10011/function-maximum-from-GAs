package zoppelt.geneticalgorithms.classes;

import net.objecthunter.exp4j.Expression;

public class Chromosome {
	private int length;
	private String geneticInformation;
	private String name;
	private double fitness;
	private double fitnessRatio;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getGeneticInformation() {
		return geneticInformation;
	}

	public void setGeneticInformation(String geneticInformation) {
		this.geneticInformation = geneticInformation;
	}

	public int getIntegerInformation() {
		return Integer.parseInt(geneticInformation, 2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(Expression fitnessFunction) {
		fitnessFunction.setVariable("x", getIntegerInformation());
		double result = fitnessFunction.evaluate();
		this.fitness = result;
	}

	public double getFitnessRatio() {
		return fitnessRatio;
	}

	public void setFitnessRatio(double fitnessRatio) {
		this.fitnessRatio = fitnessRatio;
	}

	public void printChromosome() {
		System.out.printf("Chromosome " + name + ": " + geneticInformation + " : " + getIntegerInformation() + " : "
				+ fitness + " : %.2f\n", (fitnessRatio * 100));
	}
}
