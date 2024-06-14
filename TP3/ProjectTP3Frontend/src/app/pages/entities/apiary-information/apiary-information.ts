import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { ApiaryInformation } from './apiary-information.model';
import { ApiaryInformationService } from './apiary-information.service';

@Component({
  selector: 'page-apiary-information',
  templateUrl: 'apiary-information.html',
})
export class ApiaryInformationPage {
  apiaryInformations: ApiaryInformation[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private apiaryInformationService: ApiaryInformationService,
    private toastCtrl: ToastController,
    public plt: Platform
  ) {
    this.apiaryInformations = [];
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.apiaryInformationService
      .query()
      .pipe(
        filter((res: HttpResponse<ApiaryInformation[]>) => res.ok),
        map((res: HttpResponse<ApiaryInformation[]>) => res.body)
      )
      .subscribe(
        (response: ApiaryInformation[]) => {
          this.apiaryInformations = response;
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

  trackId(index: number, item: ApiaryInformation) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/apiary-information/new');
  }

  async edit(item: IonItemSliding, apiaryInformation: ApiaryInformation) {
    await this.navController.navigateForward('/tabs/entities/apiary-information/' + apiaryInformation.id + '/edit');
    await item.close();
  }

  async delete(apiaryInformation) {
    this.apiaryInformationService.delete(apiaryInformation.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({
          message: 'ApiaryInformation deleted successfully.',
          duration: 3000,
          position: 'middle',
        });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(apiaryInformation: ApiaryInformation) {
    await this.navController.navigateForward('/tabs/entities/apiary-information/' + apiaryInformation.id + '/view');
  }
}
