package io.labsit.bank.domain.model.enums.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import io.labsit.bank.domain.model.enums.TipoPessoaEnum;

@Converter
public class TipoPessoaEnumConverter implements AttributeConverter<TipoPessoaEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoPessoaEnum item) {
		return item.getCodigo();
	}

	@Override
	public TipoPessoaEnum convertToEntityAttribute(Integer codigo) {
		return TipoPessoaEnum.valueOf(codigo);
	}

}
