package com.homework.gomoku.controller;

import java.util.TimerTask;

public class TimeCount extends TimerTask{
	public int secondsD = 0;
	public int minD = 0;
	
	int saver = 0;
	public void run() {
		saver = saver+1;
		minD = saver/60;
		secondsD = saver%60;
	}
}