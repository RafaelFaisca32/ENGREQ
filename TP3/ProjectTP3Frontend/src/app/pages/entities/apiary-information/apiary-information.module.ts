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

import { ApiaryInformationPage } from './apiary-information';
import { ApiaryInformationUpdatePage } from './apiary-information-update';
import { ApiaryInformation, ApiaryInformationService, ApiaryInformationDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class ApiaryInformationResolve implements Resolve<ApiaryInformation> {
  constructor(private service: ApiaryInformationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApiaryInformation> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApiaryInformation>) => response.ok),
        map((apiaryInformation: HttpResponse<ApiaryInformation>) => apiaryInformation.body)
      );
    }
    return of(new ApiaryInformation());
  }
}

const routes: Routes = [
  {
    path: '',
    component: ApiaryInformationPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApiaryInformationUpdatePage,
    resolve: {
      data: ApiaryInformationResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApiaryInformationDetailPage,
    resolve: {
      data: ApiaryInformationResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApiaryInformationUpdatePage,
    resolve: {
      data: ApiaryInformationResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [ApiaryInformationPage, ApiaryInformationUpdatePage, ApiaryInformationDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class ApiaryInformationPageModule {}
