import { Component, OnInit } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Frame } from './frame.model';
import { FrameService } from './frame.service';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';
import { LocalStorageService } from '../../../services/localStorage/local-storage.service';

@Component({
  selector: 'page-frame',
  templateUrl: 'frame.html',
})
export class FramePage implements OnInit {
  frames: Frame[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private frameService: FrameService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private network: NetworkCheckerService,
    private localStorage: LocalStorageService
  ) {
    this.frames = [];
  }

  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
  }

  async ionViewWillEnter() {
    if(this.network.onlineIndicator){
      await this.loadAll();
    } else {
      await this.loadAllOffline();
    }
  }

  async loadAllOffline(){
    this.localStorage.getData("dataToSave").then(data => {
      data.frames.forEach(frameObj => {
        frameObj.frame.id = "0"
        //console.log(frame)
        if(!this.frames.some(aux => aux.code == frameObj.frame.code)){
          this.frames.push(frameObj.frame)
        }

      })
    })
  }



  async loadAll(refresher?) {
    this.frameService
      .query({size: 100})
      .pipe(
        filter((res: HttpResponse<Frame[]>) => res.ok),
        map((res: HttpResponse<Frame[]>) => res.body)
      )
      .subscribe(
        (response: Frame[]) => {
          this.frames = response;
          if (typeof refresher !== 'undefined') {
            setTimeout(() => {
              refresher.target.complete();
            }, 750);
          }
        },
        async error => {
          console.error(error);
          const toast = await this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
          await toast.present();
        }
      );
  }

  trackId(index: number, item: Frame) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/frame/new');
  }

  async edit(item: IonItemSliding, frame: Frame) {
    await this.navController.navigateForward('/tabs/entities/frame/' + frame.id + '/edit');
    await item.close();
  }

  async delete(frame) {
    this.frameService.delete(frame.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Frame deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(frame: Frame) {
    await this.navController.navigateForward('/tabs/entities/frame/' + frame.id + '/view');
  }
}
