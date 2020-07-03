package com.excilys.mappersDTO;

import com.excilys.beans.Computer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mappers.DateMapper;

public class ComputerMapperDTO {
	
	public static Computer ComputerDtoToComputer(ComputerDTO computerDto) {
		Computer computer = new Computer((int)Integer.valueOf(computerDto.getId()),computerDto.getName(),DateMapper.stringToLocalDate(computerDto.getDiscontinued()),DateMapper.stringToLocalDate(computerDto.getIntroduced()),(int)Integer.valueOf(computerDto.getCompany_id()));
		return computer;
	}
	
	public static ComputerDTO computertoComputerDto(Computer computer) {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setId(String.valueOf(computer.getId()));
		computerDto.setName(computer.getName());
		computerDto.setDiscontinued(computer.getDiscontinued().toString());
		computerDto.setIntroduced(computer.getIntroduced().toString());
		computerDto.setCompany_id(String.valueOf(computer.getCompany_id()));
		return computerDto;
	}

}
