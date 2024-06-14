import { Component, OnInit } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Unfolding } from './unfolding.model';
import { UnfoldingService } from './unfolding.service';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';

@Component({
  selector: 'page-unfolding',
  templateUrl: 'unfolding.html',
})
export class UnfoldingPage implements OnInit{
  unfoldings: Unfolding[];

  // todo: add pagination

  constructor(
    private navController: NavController,
    private unfoldingService: UnfoldingService,
    private toastCtrl: ToastController,
    public plt: Platform,
    private network: NetworkCheckerService
  ) {
    this.unfoldings = [];
  }
  ngOnInit(): void {
    //this.network.openCheckNetwork();
    this.network.logNetworkState();
  }

  async ionViewWillEnter() {
    await this.loadAll();
  }

  async loadAll(refresher?) {
    this.unfoldingService
      .query()
      .pipe(
        filter((res: HttpResponse<Unfolding[]>) => res.ok),
        map((res: HttpResponse<Unfolding[]>) => res.body)
      )
      .subscribe(
        (response: Unfolding[]) => {
          this.unfoldings = response;
          if (typeof refresher !== 'undefined') {
            setTimeout(() => {
              refresher.target.complete();
            }, 750);
          }
        },
        async error => {
          console.error(error);
          const toast = await this.toastCtrl.create({ message: 'Erro a carregar a informação', duration: 2000, position: 'middle' });
          await toast.present();
        }
      );
  }

  trackId(index: number, item: Unfolding) {
    return item.id;
  }

  async new() {
    await this.navController.navigateForward('/tabs/entities/unfolding/new');
  }

  async edit(item: IonItemSliding, unfolding: Unfolding) {
    await this.navController.navigateForward('/tabs/entities/unfolding/' + unfolding.id + '/edit');
    await item.close();
  }

  async delete(unfolding) {
    this.unfoldingService.delete(unfolding.id).subscribe(
      async () => {
        const toast = await this.toastCtrl.create({ message: 'Desdobramento eliminado com sucesso.', duration: 3000, position: 'middle' });
        await toast.present();
        await this.loadAll();
      },
      error => console.error(error)
    );
  }

  async view(unfolding: Unfolding) {
    await this.navController.navigateForward('/tabs/entities/unfolding/' + unfolding.id + '/view');
  }
}
