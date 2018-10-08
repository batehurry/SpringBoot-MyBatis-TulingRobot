package com.shawn.robot.controller;

import com.shawn.robot.model.Interlocution;
import com.shawn.robot.service.RobotInterlocutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xiwei Zhang
 */
@RestController
@RequestMapping("/api")
public class InterlocutionController {

	@Autowired
    private RobotInterlocutionService interlocutionService;
	@GetMapping("/interlocution")
    public ResponseEntity<?> getAnswer(@RequestParam("question")String question){
    	System.out.println("question: " + question);
        Interlocution result = interlocutionService.getInterlocation(question);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
