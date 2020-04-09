package com.example.barbearia3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

public class onReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Toast.makeText(context, "Mensagem teste", Toast.LENGTH_LONG).show();
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_alarm)
                        .setContentTitle("AGENDAMENTO")
                        .setContentText("Voce possui agendamento!\nVerifique o horario.");

        Intent in = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, in, 0);
        mBuilder.setContentIntent(pendingIntent).setAutoCancel(true);


        mNotificationManager.notify(1, mBuilder.build());
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
    }
}