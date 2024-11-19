# ServicesTest
# 9.1 Services. Создание первого сервиса
# 9.2 Перезапуск сервисов
START_STICKY - сели система убъёт сервис, то он будет пересоздан
START_NOT_STICKY если система убила сервис, его не нужно перезапускать
START_REDELIVER_INTENT как START_STICKY но передаётся первоначальный Intent
# 9.3 Работа с уведомлениями. NotificationManager
Начиная с andoid 8,API 26 сервис должен показывать уведомление