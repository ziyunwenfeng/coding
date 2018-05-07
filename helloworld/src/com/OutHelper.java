package com;

public class OutHelper {
	IOutputGenerator outputGenerator;

	public void genarateOutput(){
		outputGenerator.generateOutput();
	}

	public void setOutputGenerator(IOutputGenerator outputGenerator) {
		this.outputGenerator = outputGenerator;
	}
	
}
