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

import { HivePage } from './hive';
import { HiveUpdatePage } from './hive-update';
import { Hive, HiveService, HiveDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class HiveResolve implements Resolve<Hive> {
  constructor(private service: HiveService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Hive> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Hive>) => response.ok),
        map((hive: HttpResponse<Hive>) => hive.body)
      );
    }
    return of(new Hive());
  }
}

const routes: Routes = [
  {
    path: '',
    component: HivePage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HiveUpdatePage,
    resolve: {
      data: HiveResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HiveDetailPage,
    resolve: {
      data: HiveResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HiveUpdatePage,
    resolve: {
      data: HiveResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [HivePage, HiveUpdatePage, HiveDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class HivePageModule {}
