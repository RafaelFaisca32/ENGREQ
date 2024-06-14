import { Component,OnInit } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { ApiaryZone } from './apiary-zone.model';
import { ApiaryZoneService } from './apiary-zone.service';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-apiary-zone',
  templateUrl: 'apiary-zone.html',
})
export class ApiaryZonePage implements OnInit {
  apiaryZones: ApiaryZone[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private apiaryZoneService: ApiaryZoneService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private network: NetworkCheckerService
  ) {
    this.apiaryZones = [];
  }

  ngOnInit(){
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.apiaryZoneService
      .query()
      .pipe(
        filter((res: HttpResponse<ApiaryZone[]>) => res.ok),
        map((res: HttpResponse<ApiaryZone[]>) => res.body)
      )
      .subscribe(
        (response: ApiaryZone[]) => {
          this.apiaryZones = response;
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

  trackId(index: number, item: ApiaryZone) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/apiary-zone/new');
  }

  async edit(item: IonItemSliding, apiaryZone: ApiaryZone) {
    await this.navController.navigateForward('/tabs/entities/apiary-zone/' + apiaryZone.id + '/edit');
    await item.close();
  }

  async delete(apiaryZone) {
    this.apiaryZoneService.delete(apiaryZone.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'ApiaryZone deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(apiaryZone: ApiaryZone) {
    await this.navController.navigateForward('/tabs/entities/apiary-zone/' + apiaryZone.id + '/view');
  }
}
