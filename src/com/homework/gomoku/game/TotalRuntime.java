package com.homework.gomoku.game;

public class TotalRuntime {
	
	long startTime;
	public TotalRuntime() {
		//set up a get timer for the total things to be displayed
		 startTime = System.nanoTime();
		
	}
	public int totalRT() {
		// TODO Auto-generated method stub
		long endTime = System.nanoTime();
		return (int) (endTime - startTime);
	}

}
