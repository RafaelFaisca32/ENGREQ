import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { TranslateModule } from '@ngx-translate/core';
import { TermsConditionsComponent } from './terms-conditions';

const routes: Routes = [
  {
    path: '',
    component: TermsConditionsComponent,
  },
];

@NgModule({
  imports: [IonicModule, CommonModule, FormsModule, RouterModule.forChild(routes), TranslateModule],
  declarations: [TermsConditionsComponent],
})
export class TermsConditionsModule {}
