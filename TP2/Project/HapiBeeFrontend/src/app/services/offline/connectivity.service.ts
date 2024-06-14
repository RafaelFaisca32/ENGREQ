import { Injectable } from '@angular/core';

import { Network } from '@ionic-native/network/ngx';

@Injectable({
  providedIn: 'root'
})
export class ConnectivityService {
  constructor(private network: Network) {

    // Subscribe to network status changes
    this.network.onDisconnect().subscribe(() => {
      console.log('Disconnected from the internet');
      // Handle disconnection if needed
    });

    this.network.onConnect().subscribe(() => {
      console.log('Connected to the internet');
      // Handle connection if needed
    });
  }

  // Function to check if the device is online
  isOnline(): boolean {
    return this.network.type !== 'none';
  }
}
