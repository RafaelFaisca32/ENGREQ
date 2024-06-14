import { NgModule, Injectable } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserRouteAccessService } from '../../../services/auth/user-route-access.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';

import { AnnualInventoryDeclarationPage } from './annual-inventory-declaration';
import { AnnualInventoryDeclarationUpdatePage } from './annual-inventory-declaration-update';
import { AnnualInventoryDeclaration, AnnualInventoryDeclarationService, AnnualInventoryDeclarationDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class AnnualInventoryDeclarationResolve implements Resolve<AnnualInventoryDeclaration> {
  constructor(private service: AnnualInventoryDeclarationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AnnualInventoryDeclaration> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AnnualInventoryDeclaration>) => response.ok),
        map((annualInventoryDeclaration: HttpResponse<AnnualInventoryDeclaration>) => annualInventoryDeclaration.body)
      );
    }
    return of(new AnnualInventoryDeclaration());
  }
}

const routes: Routes = [
  {
    path: '',
    component: AnnualInventoryDeclarationPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnnualInventoryDeclarationUpdatePage,
    resolve: {
      data: AnnualInventoryDeclarationResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnnualInventoryDeclarationDetailPage,
    resolve: {
      data: AnnualInventoryDeclarationResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnnualInventoryDeclarationUpdatePage,
    resolve: {
      data: AnnualInventoryDeclarationResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [AnnualInventoryDeclarationPage, AnnualInventoryDeclarationUpdatePage, AnnualInventoryDeclarationDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class AnnualInventoryDeclarationPageModule {}
