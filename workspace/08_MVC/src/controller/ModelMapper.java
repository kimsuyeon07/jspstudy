package controller;

import model.AverageCommand;
import model.GradeCommand;

public class ModelMapper {

	// private한 생성자 : Singleton 처리
	// 자신의 생성자를 생성_ 한번에 하나의 Model만 반환할 수 있도록!
	private static ModelMapper mapper = new ModelMapper();
	private ModelMapper() {};
	public static ModelMapper getInstance() {
		if (mapper == null) {
			mapper = new ModelMapper();
		}
		return mapper; // static 생성자를 반환한다.
	}
	
	public AverageCommand getModel(String cmd) {
		AverageCommand command = null;
		switch (cmd) {
		case "inquiry.do":
			command = new GradeCommand();
			break;
		}
		return command;
	}
	
	
	
}
