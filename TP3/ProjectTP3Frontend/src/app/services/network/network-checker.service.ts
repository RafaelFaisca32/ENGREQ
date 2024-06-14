import { Injectable } from '@angular/core';
import { ToastController } from '@ionic/angular';

import { Network } from '@capacitor/network';
import { LocalStorageService } from '../localStorage/local-storage.service';

@Injectable({
  providedIn: 'root',
})
export class NetworkCheckerService {
  onlineIndicator: boolean;
  constructor(
    private localStorage: LocalStorageService,
    protected toastCtrl: ToastController
    ) {
      this.openCheckNetwork();
    }

  openCheckNetwork() {
    Network.addListener('networkStatusChange', (status) => {
      console.log('Network status changed', status);
      this.onlineIndicator = status.connected;
      this.changed();
      if(this.onlineIndicator){
        this.localStorage.commitChanges();
      }
    });
  }

  async changed(){
    let onoff = "offline";
    if(this.onlineIndicator){
      onoff = "online"
    }
    const toast = await this.toastCtrl.create({ message: `You are ${onoff}.`, duration: 2000, position: 'middle' });
    await toast.present();
  }

  async logNetworkState() {
    const status = await Network.getStatus();
    this.onlineIndicator = status.connected;
    console.log('Network status:', status);
  }
}
