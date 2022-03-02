from datetime import datetime
import pytz

bogota_timezone = pytz.timezone("America/Bogota")
bogota_date = datetime.now(bogota_timezone)
print("Bogotá:", bogota_date.strftime("%d/%m/%Y %H:%M:%S"))

madrid_timezone = pytz.timezone("Europe/Madrid")
madrid_date = datetime.now(madrid_timezone)
print("Madrid:", madrid_date.strftime("%d/%m/%Y %H:%M:%S"))

mexico_timezone = pytz.timezone("America/Mexico_City")
mexico_date = datetime.now(mexico_timezone)
print("Ciudad de México:", mexico_date.strftime("%d/%m/%Y %H:%M:%S"))
