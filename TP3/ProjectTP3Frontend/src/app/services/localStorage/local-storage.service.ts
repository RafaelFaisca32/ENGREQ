import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage-angular';
import { FrameService } from '../../pages/entities/frame/frame.service';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor(private storage: Storage,
    public frameService: FrameService
    ) {
    this.storage.create();

    let dataToSave = {
      frames: [],
      apiaries: []
    }

    let dataSaved = {
      frames: [],
      apiaries: []
    }
    this.storage.set("dataToSave", dataToSave )
    this.storage.set("dataSaved", dataSaved )
  }

  // Save data to storage
  async setData(key: string, value: any): Promise<void> {
    await this.storage.set(key, value);
  }

  // Get data from storage
  async getData(key: string): Promise<any> {
    return await this.storage.get(key);
  }

  // Remove data from storage
  async removeData(key: string): Promise<void> {
    await this.storage.remove(key);
  }

  // Clear entire storage
  async clearStorage(): Promise<void> {
    await this.storage.clear();
  }

  async commitChanges(){

    this.getData("dataToSave").then(data => {
      data.frames.forEach(  (frameObj,index,array)  => {
        array.splice(index, 1);
        if(frameObj.isSaving){
          this.frameService.create(frameObj.frame)
        } else {
          this.frameService.update(frameObj.frame)
        }

        this.getData("dataSaved").then(data => {
          const newArray = data.frames.push(frameObj.frame);
          this.setData("dataSaved",newArray);
        })
      });
    })
  }
}
