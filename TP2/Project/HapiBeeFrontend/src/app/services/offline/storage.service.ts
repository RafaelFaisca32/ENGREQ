import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage-angular';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor(private storage: Storage) {
    this.createStorage();
  }

  async createStorage() {
    await this.storage.create();
  }

  // Functions to interact with storage
  async setValue(key: string, value: any) {
    await this.storage.set(key, value);
  }

  async getValue(key: string) {
    return await this.storage.get(key);
  }
}
