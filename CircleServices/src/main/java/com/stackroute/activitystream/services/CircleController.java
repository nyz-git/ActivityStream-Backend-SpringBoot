package com.stackroute.activitystream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stackroute.activitystream.dao.CircleDAO;
import com.stackroute.activitystream.model.Circle;

@Controller
public class CircleController {

	@Autowired
	CircleDAO circleDAO;

	@RequestMapping(value = "/createcircle", method = RequestMethod.POST)
	public ResponseEntity createCircle(@RequestBody Circle circle) {
		try {
			Boolean newCircle = circleDAO.createCircle(circle);
			return new ResponseEntity(newCircle, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/updatecircle", method = RequestMethod.POST)
	public ResponseEntity<?> updateCircle(@RequestBody Circle circle) {
		try {
			circleDAO.updateCircle(circle);
			return new ResponseEntity<Circle>(circle, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deletecircle", method = RequestMethod.POST)
	public ResponseEntity<?> deleteCircle(@RequestBody int circleId) {
		try {
			circleDAO.deleteCircle(circleId);

			return new ResponseEntity<Integer>(circleId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getallcircles", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCircles() {
		try {
			List<Circle> circleList = circleDAO.getAllCircles();

			return new ResponseEntity<List<Circle>>(circleList, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
