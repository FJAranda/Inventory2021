package com.example.inventory;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class InventoryApplication extends Application {
    public static final String IDCHANNEL = "123456";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        //1- Definir la importancia del canal
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        //2- Definir el nombre del canal
        String nameChannel = "inventory_channel";
        //3- Crear el canal
        NotificationChannel channel = new NotificationChannel(IDCHANNEL, nameChannel, importance);
        //opcional: Configurar el comportamiento de las notificaciones
        channel.enableLights(true);
        //4- Crear el canal en el NotificationManager
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
}
