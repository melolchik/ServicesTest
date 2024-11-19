# ServicesTest
# 9.1 Services. Создание первого сервиса
# 9.2 Перезапуск сервисов
START_STICKY - сели система убъёт сервис, то он будет пересоздан
START_NOT_STICKY если система убила сервис, его не нужно перезапускать
START_REDELIVER_INTENT как START_STICKY но передаётся первоначальный Intent
# 9.3 Работа с уведомлениями. NotificationManager
Начиная с andoid 8,API 26 сервис должен показывать уведомление
# 9.4 Foreground Service
startForegroundService - то же, что и startService, но при его вызове вы обещаете, что в течение 5 секунд вы покажете уведомление пользователю
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
+ нужна проверка разрешений

# 9.5 Остановка сервисов
stopSelf - внутри сервиса
stopService(MyForegroundService.newIntent(this)) снаружи