import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Disease } from './disease.model';
import { DiseaseService } from './disease.service';

@Component({
  selector: 'page-disease',
  templateUrl: 'disease.html',
})
export class DiseasePage {
  diseases: Disease[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private diseaseService: DiseaseService,
    private toastCtrl: ToastController,
    public plt: Platform
  ) {
    this.diseases = [];
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.diseaseService
      .query()
      .pipe(
        filter((res: HttpResponse<Disease[]>) => res.ok),
        map((res: HttpResponse<Disease[]>) => res.body)
      )
      .subscribe(
        (response: Disease[]) => {
          this.diseases = response;
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

  trackId(index: number, item: Disease) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/disease/new');
  }

  async edit(item: IonItemSliding, disease: Disease) {
    await this.navController.navigateForward('/tabs/entities/disease/' + disease.id + '/edit');
    await item.close();
  }

  async delete(disease) {
    this.diseaseService.delete(disease.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Disease deleted successfully.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(disease: Disease) {
    await this.navController.navigateForward('/tabs/entities/disease/' + disease.id + '/view');
  }
}
