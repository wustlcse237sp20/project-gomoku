package com.homework.gomoku.controller;

import java.util.TimerTask;

public class TimeCount extends TimerTask{
	public int secondsD = 0;
	public int minD = 0;
	
	int saver = 0;
	public void run() {
		if(saver>60) {
			minD = saver/60;
			secondsD = saver%60;
		}
		else {
			minD = 0;
			secondsD = saver;
		}
		saver = saver+1;
	}
}