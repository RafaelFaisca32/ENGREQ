import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { AnnualInventoryDeclaration } from './annual-inventory-declaration.model';
import { AnnualInventoryDeclarationService } from './annual-inventory-declaration.service';
import {LocalStorage} from "ngx-webstorage";
import {Apiary, ApiaryService} from "../apiary";
import {Hive} from "../hive";

@Component({
  selector: 'page-annual-inventory-declaration',
  templateUrl: 'annual-inventory-declaration.html',
})
export class AnnualInventoryDeclarationPage {
  annualInventoryDeclarations: AnnualInventoryDeclaration[];


  // todo: add pagination
  private apiaries: Apiary[];

  constructor(
    private apiaryService: ApiaryService,
    private navController: NavController,
    private annualInventoryDeclarationService: AnnualInventoryDeclarationService,
    private toastCtrl: ToastController,
    public plt: Platform
  ) {
    this.annualInventoryDeclarations = [];
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.annualInventoryDeclarationService
      .query()
      .pipe(
        filter((res: HttpResponse<AnnualInventoryDeclaration[]>) => res.ok),
        map((res: HttpResponse<AnnualInventoryDeclaration[]>) => res.body)
      )
      .subscribe(
        (response: AnnualInventoryDeclaration[]) => {
          this.annualInventoryDeclarations = response;
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

  trackId(index: number, item: AnnualInventoryDeclaration) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/new');
  }



  async edit(item: IonItemSliding, annualInventoryDeclaration: AnnualInventoryDeclaration) {
    await this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/' + annualInventoryDeclaration.id + '/edit');
    await item.close();
  }

  async delete(annualInventoryDeclaration) {
    this.annualInventoryDeclarationService.delete(annualInventoryDeclaration.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({
          message: 'AnnualInventoryDeclaration deleted successfully.',
          duration: 3000,
          position: 'middle',
        });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(annualInventoryDeclaration: AnnualInventoryDeclaration) {
    await this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/' + annualInventoryDeclaration.id + '/view');
  }
}
