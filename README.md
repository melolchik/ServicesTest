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

# 9.6 Intent Service
- Работает не в главном потоке
- onHandleIntent метод где работает сервис, 
останавливается автоматически после выполнения
- Может быть запущен как обычный сервис, либо как foreground
- Каждый запуск сервиса работает последовательно, обычные сервисы запускаются параллельно

в методе onCreate

 /*
        true - аналог START_REDELIVER_INTENT
		false - аналог START_NOT_STICKY
         */
 setIntentRedelivery(true)

#9.7 JobService. Создание сервиса
     onStartJob(params: JobParameters?): Boolean
возвращает //sync = false, работа завершена
           //async = true, работа не завершена

jobFinished(params, true) - остановка сервиса, второй параметр = true, если нужно перезапустить сервис через какое-то время

	onStopJob(params: JobParameters?): Boolean - вызывается при опред.условиях,( например отключили от зарядки, от wi-fi)
Если сами завершаем работу сервиса, метод не вызовется, т.е если сервис система
возвращает //= false, не нужно перезапускать
           //= true, нужно перезапустить

#9.8 JobScheduler. Запуск сервиса

<service
            android:name=".MyJobService"
            android:exported="true"
            android:permission="android.permission.BIND_JOB_SERVICE" /> - в манифесте

.setPersisted(true) - сервис запускается после перезагрузки устройства
.setPeriodic() сервис выполняется раз в период, не обязательно ровно через этот период
.setRequiresCharging(true) - при включенной зарядке
.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) - при включенном вай-фай
