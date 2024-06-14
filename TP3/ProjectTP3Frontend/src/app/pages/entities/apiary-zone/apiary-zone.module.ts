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

import { ApiaryZonePage } from './apiary-zone';
import { ApiaryZoneUpdatePage } from './apiary-zone-update';
import { ApiaryZone, ApiaryZoneService, ApiaryZoneDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class ApiaryZoneResolve implements Resolve<ApiaryZone> {
  constructor(private service: ApiaryZoneService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApiaryZone> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApiaryZone>) => response.ok),
        map((apiaryZone: HttpResponse<ApiaryZone>) => apiaryZone.body)
      );
    }
    return of(new ApiaryZone());
  }
}

const routes: Routes = [
  {
    path: '',
    component: ApiaryZonePage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApiaryZoneUpdatePage,
    resolve: {
      data: ApiaryZoneResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApiaryZoneDetailPage,
    resolve: {
      data: ApiaryZoneResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApiaryZoneUpdatePage,
    resolve: {
      data: ApiaryZoneResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [ApiaryZonePage, ApiaryZoneUpdatePage, ApiaryZoneDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class ApiaryZonePageModule {}
