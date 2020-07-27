package com.excilys.mappersDTO;

import com.excilys.beans.Computer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mappers.DateMapper;

/**
 * Convertir le résultat de la bdd pour chaque ordis en objet
 */
public class ComputerMapperDTO {
	
	public static Computer ComputerDtoToComputer(ComputerDTO computerDto) {
		Computer computer = new Computer();
		computer.setName(computerDto.getName());
		if (!computerDto.getIntroduced().isEmpty()) {
			computer.setIntroduced(DateMapper.stringToLocalDate(computerDto.getIntroduced()));
		}
		if (!computerDto.getDiscontinued().isEmpty()) {
			computer.setDiscontinued(DateMapper.stringToLocalDate(computerDto.getDiscontinued()));
		}
		computer.setCompany_id((int)Integer.valueOf(computerDto.getCompany_id()));
		if(computerDto.getId() != null) {
			computer.setId(Integer.parseInt(computerDto.getId()));
		}
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
