package controller;

import model.CircleCommand;
import model.RectangleCommand;
import model.ShapeCommand;
import model.TriangleCpmmand;

public class ModelMapper {

	// 한 번에 하나의 Model만 반환할 수 있도록 Singleton 처리
	private static ModelMapper mapper = new ModelMapper();
	private ModelMapper() {};
	public static ModelMapper getInstance() {
		 if (mapper == null) {
			 mapper = new ModelMapper();
		 }
		 return mapper;
	}
	
	// Model을 반환시키는 getModel() 메소드 생성
	public ShapeCommand getModel(String cmd) {
		ShapeCommand command = null;
		switch (cmd) {
		case "rectangle.do": 
			command = new RectangleCommand();
			break;
		case "triangle.do": 
			command = new TriangleCpmmand();
			break;
		case "circle.do": 
			command = new CircleCommand();
			break;
		}
		return command;
	}
	
}
