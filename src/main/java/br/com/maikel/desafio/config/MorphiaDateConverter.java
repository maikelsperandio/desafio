package br.com.maikel.desafio.config;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class MorphiaDateConverter extends TypeConverter implements SimpleValueConverter{

	public MorphiaDateConverter() {
		super(Date.class);
	}

	@Override
	public Object decode(Class<?> clazz, Object obj, MappedField field) {
		if(obj instanceof Date){
			Date date = (Date) obj;
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.setTime(date);
			cal.setTimeZone(TimeZone.getTimeZone("UTC"));

			cal2.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			cal2.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			cal2.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
			cal2.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
			cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
			cal2.set(Calendar.SECOND, cal.get(Calendar.SECOND));
			cal2.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));

			return cal2.getTime();
		}
		return obj;
	}
}
