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

import { TranshumanceRequestPage } from './transhumance-request';
import { TranshumanceRequestUpdatePage } from './transhumance-request-update';
import { TranshumanceRequest, TranshumanceRequestService, TranshumanceRequestDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class TranshumanceRequestResolve implements Resolve<TranshumanceRequest> {
  constructor(private service: TranshumanceRequestService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TranshumanceRequest> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TranshumanceRequest>) => response.ok),
        map((transhumanceRequest: HttpResponse<TranshumanceRequest>) => transhumanceRequest.body)
      );
    }
    return of(new TranshumanceRequest());
  }
}

const routes: Routes = [
  {
    path: '',
    component: TranshumanceRequestPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TranshumanceRequestUpdatePage,
    resolve: {
      data: TranshumanceRequestResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TranshumanceRequestDetailPage,
    resolve: {
      data: TranshumanceRequestResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TranshumanceRequestUpdatePage,
    resolve: {
      data: TranshumanceRequestResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [TranshumanceRequestPage, TranshumanceRequestUpdatePage, TranshumanceRequestDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class TranshumanceRequestPageModule {}
