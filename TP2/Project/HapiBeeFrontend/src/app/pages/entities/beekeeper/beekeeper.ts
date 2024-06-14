import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Beekeeper } from './beekeeper.model';
import { BeekeeperService } from './beekeeper.service';

@Component({
  selector: 'page-beekeeper',
  templateUrl: 'beekeeper.html',
})
export class BeekeeperPage {
  beekeepers: Beekeeper[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private beekeeperService: BeekeeperService,
    private toastCtrl: ToastController,
    public plt: Platform
  ) {
    this.beekeepers = [];
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.beekeeperService
      .query()
      .pipe(
        filter((res: HttpResponse<Beekeeper[]>) => res.ok),
        map((res: HttpResponse<Beekeeper[]>) => res.body)
      )
      .subscribe(
        (response: Beekeeper[]) => {
          this.beekeepers = response;
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

  trackId(index: number, item: Beekeeper) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/beekeeper/new');
  }

  async edit(item: IonItemSliding, beekeeper: Beekeeper) {
    await this.navController.navigateForward('/tabs/entities/beekeeper/' + beekeeper.id + '/edit');
    await item.close();
  }

  async delete(beekeeper) {
    this.beekeeperService.delete(beekeeper.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Beekeeper deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(beekeeper: Beekeeper) {
    await this.navController.navigateForward('/tabs/entities/beekeeper/' + beekeeper.id + '/view');
  }
}
