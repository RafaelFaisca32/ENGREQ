import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { TranslateModule } from '@ngx-translate/core';
import { UserRouteAccessService } from 'src/app/services/auth/user-route-access.service';
import { EntitiesPage } from './entities.page';

const routes: Routes = [
  {
    path: '',
    component: EntitiesPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'apiary',
    loadChildren: () => import('./apiary/apiary.module').then(m => m.ApiaryPageModule),
  },
  {
    path: 'apiary-zone',
    loadChildren: () => import('./apiary-zone/apiary-zone.module').then(m => m.ApiaryZonePageModule),
  },
  {
    path: 'beekeeper',
    loadChildren: () => import('./beekeeper/beekeeper.module').then(m => m.BeekeeperPageModule),
  },
  {
    path: 'hive',
    loadChildren: () => import('./hive/hive.module').then(m => m.HivePageModule),
  },
  {
    path: 'frame',
    loadChildren: () => import('./frame/frame.module').then(m => m.FramePageModule),
  },
  {
    path: 'inspection',
    loadChildren: () => import('./inspection/inspection.module').then(m => m.InspectionPageModule),
  },
  {
    path: 'disease',
    loadChildren: () => import('./disease/disease.module').then(m => m.DiseasePageModule),
  },
  {
    path: 'unfolding',
    loadChildren: () => import('./unfolding/unfolding.module').then(m => m.UnfoldingPageModule),
  },
  {
    path: 'crest',
    loadChildren: () => import('./crest/crest.module').then(m => m.CrestPageModule),
  },
  {
    path: 'transhumance-request',
    loadChildren: () => import('./transhumance-request/transhumance-request.module').then(m => m.TranshumanceRequestPageModule),
  },
  {
    path: 'annual-inventory-declaration',
    loadChildren: () =>
      import('./annual-inventory-declaration/annual-inventory-declaration.module').then(m => m.AnnualInventoryDeclarationPageModule),
  },
  {
    path: 'apiary-information',
    loadChildren: () => import('./apiary-information/apiary-information.module').then(m => m.ApiaryInformationPageModule),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

@NgModule({
  imports: [IonicModule, CommonModule, FormsModule, RouterModule.forChild(routes), TranslateModule],
  declarations: [EntitiesPage],
})
export class EntitiesPageModule {}
