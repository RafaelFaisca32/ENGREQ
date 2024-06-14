import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { TranshumanceRequest } from './transhumance-request.model';
import { TranshumanceRequestService } from './transhumance-request.service';

@Component({
  selector: 'page-transhumance-request',
  templateUrl: 'transhumance-request.html',
})
export class TranshumanceRequestPage {
  transhumanceRequests: TranshumanceRequest[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private transhumanceRequestService: TranshumanceRequestService,
    private toastCtrl: ToastController,
    public plt: Platform
  ) {
    this.transhumanceRequests = [];
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.transhumanceRequestService
      .query()
      .pipe(
        filter((res: HttpResponse<TranshumanceRequest[]>) => res.ok),
        map((res: HttpResponse<TranshumanceRequest[]>) => res.body)
      )
      .subscribe(
        (response: TranshumanceRequest[]) => {
          this.transhumanceRequests = response;
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

  trackId(index: number, item: TranshumanceRequest) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/transhumance-request/new');
  }

  async edit(item: IonItemSliding, transhumanceRequest: TranshumanceRequest) {
    await this.navController.navigateForward('/tabs/entities/transhumance-request/' + transhumanceRequest.id + '/edit');
    await item.close();
  }

  async delete(transhumanceRequest) {
    this.transhumanceRequestService.delete(transhumanceRequest.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({
          message: 'TranshumanceRequest deleted successfully.',
          duration: 3000,
          position: 'middle',
        });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(transhumanceRequest: TranshumanceRequest) {
    await this.navController.navigateForward('/tabs/entities/transhumance-request/' + transhumanceRequest.id + '/view');
  }
}
