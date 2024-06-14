import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Zone } from './zone.model';
import { ZoneService } from './zone.service';

@Component({
  selector: 'page-zone',
  templateUrl: 'zone.html',
})
export class ZonePage {
  zones: Zone[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private zoneService: ZoneService,
    private toastCtrl: ToastController,
    public plt: Platform
  ) {
    this.zones = [];
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.zoneService
      .query()
      .pipe(
        filter((res: HttpResponse<Zone[]>) => res.ok),
        map((res: HttpResponse<Zone[]>) => res.body)
      )
      .subscribe(
        (response: Zone[]) => {
          this.zones = response;
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

  trackId(index: number, item: Zone) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/zone/new');
  }

  async edit(item: IonItemSliding, zone: Zone) {
    await this.navController.navigateForward('/tabs/entities/zone/' + zone.id + '/edit');
    await item.close();
  }

  async delete(zone) {
    this.zoneService.delete(zone.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Zone deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(zone: Zone) {
    await this.navController.navigateForward('/tabs/entities/zone/' + zone.id + '/view');
  }
}
