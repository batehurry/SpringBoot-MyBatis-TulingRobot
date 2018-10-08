package com.shawn.robot.service;

import java.util.List;

import com.shawn.robot.model.Interlocution;

/**
 * created by Xiwei Zhang
 */
public interface RobotInterlocutionService {
	public String getAnswer(String question);
    public Interlocution getInterlocation(String question);
}
