import React from 'react'
import {View, Text, NativeModules, DeviceEventEmitter, EventEmitter } from 'react-native'
import RNDrawOverlay from 'react-native-draw-overlay';
import PushNotification from "react-native-push-notification";
import BackgroundJob from 'react-native-background-job';

const backgroundJob = {
  jobKey: "scheduleJob",
  job: () => {
    DrawOverModule.displayDrawOver();
  }
 };
 
 BackgroundJob.register(backgroundJob);

 var backgroundSchedule = {
  jobKey: "scheduleJob",
  allowExecutionInForeground: true
 }
 
 BackgroundJob.schedule(backgroundSchedule)
   .then(() => console.log("Success"))
   .catch((err:any) => console.error(err));

PushNotification.configure({
  onRegister: ({token}) => {
    console.log('FCM TOKEN', token);
  },
  onNotification: (notification) => {
    console.log(notification);
    if(notification.data.schedule) {
      DrawOverModule.displayDrawOver();
    }
  }
})

const {DrawOverModule} = NativeModules;

const App = () => {

  React.useEffect(() => {
    RNDrawOverlay.askForDispalayOverOtherAppsPermission()
	     .then((res:any) => {
          // DrawOverModule.displayDrawOver();
	     })
	     .catch((e: any) => {

	     })

    DeviceEventEmitter.addListener('schedule', () => {
      console.log('SCHEDULE BUTTON CLICKED');
    })
  }, []);

  return <View>
    <Text>Yoo!!</Text>
  </View>
}   

export default App;