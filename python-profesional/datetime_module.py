from datetime import datetime, date

my_datetime = datetime.now()
print("Datetime:", my_datetime)
print("Year:", my_datetime.year)
print("Month:", my_datetime.month)
print("Day:", my_datetime.day)
print("Hour:", my_datetime.hour)
print("Minute:", my_datetime.minute)
print("Second:", my_datetime.second)
print("Microsecond:", my_datetime.microsecond)

my_datetime_str_latam = my_datetime.strftime("%d/%m/%Y")
print("Format LATAM:", my_datetime_str_latam)

my_datetime_str_usa = my_datetime.strftime("%m/%d/%Y")
print("Format USA:", my_datetime_str_usa)

my_datetime_str_custom = my_datetime.strftime("Son las %H horas con %M minutos y %S segundos")
print("Format custom:", my_datetime_str_custom)

my_day = date.today()
print("Date:", my_day)
print("Year:", my_day.year)
print("Month:", my_day.month)
print("Day:", my_day.day)

my_time_utc = datetime.utcnow()
print("UTC datetime:", my_time_utc)
