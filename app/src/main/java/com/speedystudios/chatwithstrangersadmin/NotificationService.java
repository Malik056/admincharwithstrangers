package com.speedystudios.chatwithstrangersadmin;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static com.speedystudios.chatwithstrangersadmin.MainActivity.CHANNEL_ID;

public class NotificationService extends Service {

	Context mContext;
	int myChannel;


	@Override
	public void onCreate() {
		super.onCreate();
		Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
				.setContentTitle("")
				.setContentText("").build();

		startForeground(1, notification);
	}

	int id = 0;

	public NotificationService() {
		super();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		mContext = this;
		DatabaseReference reports = FirebaseDatabase.getInstance().getReference().child("reports");
		reports.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {
				if(!pDataSnapshot.getChildren().iterator().next().hasChild("done"))
				{
					pDataSnapshot.getChildren().iterator().next().getRef().child("done").setValue(1);
					String userID = pDataSnapshot.getKey();
					String reportedUser = pDataSnapshot.getChildren().iterator().next().getKey();
					Intent intent = new Intent(mContext, Report_Details.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
					String desc = userID + " reprted user " + reportedUser;
					String title = "Report";
					showNotification(pendingIntent,title,desc);

				}
			}

			@Override
			public void onChildChanged(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

			}

			@Override
			public void onChildRemoved(@NonNull DataSnapshot pDataSnapshot) {

			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

			}

			@Override
			public void onCancelled(@NonNull DatabaseError pDatabaseError) {

			}
		});

		DatabaseReference feedback = FirebaseDatabase.getInstance().getReference().child("feedbacks");
		feedback.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

				String user = pDataSnapshot.getKey();
				for(DataSnapshot dss : pDataSnapshot.getChildren())
				{
					if(!dss.hasChild("done"))
					{
						dss.getRef().child("done").setValue(1);
						String feedback = (String) dss.child("feedback").getValue();
						String name = "Feedback";
						String desc = user + " provided a feedback.";
						Intent intent = new Intent(mContext, Feedback_Details.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
						showNotification(pendingIntent, name, desc);
					}
				}

			}

			@Override
			public void onChildChanged(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

			}

			@Override
			public void onChildRemoved(@NonNull DataSnapshot pDataSnapshot) {

			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

			}

			@Override
			public void onCancelled(@NonNull DatabaseError pDatabaseError) {

			}
		});


		return START_STICKY;
	}


	private void showNotification(PendingIntent pIntent, String title, String description)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID )
				.setSmallIcon(R.drawable.logo_small)
				.setContentTitle(title)
				.setContentText(description)
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setContentIntent(pIntent);

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);

// notificationId is a unique int for each notification that you must define
		boolean added = false;
		while(!added) {
			try {
				notificationManager.notify(id+=10, builder.build());
				added = true;
			} catch (Exception ignored) {
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Intent broadcastIntent = new Intent(mContext, NotificationServiceRestarter.class);
		sendBroadcast(broadcastIntent);
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		super.onTaskRemoved(rootIntent);
		Intent broadcastIntent = new Intent(mContext, NotificationServiceRestarter.class);
		sendBroadcast(broadcastIntent);
	}
}
