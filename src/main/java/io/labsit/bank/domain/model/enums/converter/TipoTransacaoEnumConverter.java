package io.labsit.bank.domain.model.enums.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import io.labsit.bank.domain.model.enums.TipoTransacaoEnum;


@Converter(autoApply = true)
public class TipoTransacaoEnumConverter implements AttributeConverter<TipoTransacaoEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoTransacaoEnum item) {
		return item.getCodigo();
	}

	@Override
	public TipoTransacaoEnum convertToEntityAttribute(Integer codigo) {
		return TipoTransacaoEnum.valueOf(codigo);
	}

}
