import { Component, OnInit } from '@angular/core';
import { AnnualInventoryDeclaration } from './annual-inventory-declaration.model';
import { AnnualInventoryDeclarationService } from './annual-inventory-declaration.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { NetworkCheckerService } from '../../../services/network/network-checker.service';


@Component({
  selector: 'page-annual-inventory-declaration-detail',
  templateUrl: 'annual-inventory-declaration-detail.html',
})
export class AnnualInventoryDeclarationDetailPage implements OnInit {
  annualInventoryDeclaration: AnnualInventoryDeclaration = {};

  constructor(
    private navController: NavController,
    private annualInventoryDeclarationService: AnnualInventoryDeclarationService,
    private activatedRoute: ActivatedRoute,
    private alertController: AlertController,
    private network: NetworkCheckerService
  ) {}

  ngOnInit(): void {

    //this.network.openCheckNetwork();
    this.network.logNetworkState();

    this.activatedRoute.data.subscribe(response => {
      this.annualInventoryDeclaration = response.data;
    });
  }

  open(item: AnnualInventoryDeclaration) {
    this.navController.navigateForward('/tabs/entities/annual-inventory-declaration/' + item.id + '/edit');
  }

  async deleteModal(item: AnnualInventoryDeclaration) {
    const alert = await this.alertController.create({
      header: 'Confirm the deletion?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          cssClass: 'secondary',
        },
        {
          text: 'Delete',
          handler: () => {
            this.annualInventoryDeclarationService.delete(item.id).subscribe(() => {
              this.navController.navigateForward('/tabs/entities/annual-inventory-declaration');
            });
          },
        },
      ],
    });
    await alert.present();
  }
}
