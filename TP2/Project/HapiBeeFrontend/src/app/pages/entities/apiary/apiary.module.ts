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

import { ApiaryPage } from './apiary';
import { ApiaryUpdatePage } from './apiary-update';
import { Apiary, ApiaryService, ApiaryDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class ApiaryResolve implements Resolve<Apiary> {
  constructor(private service: ApiaryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Apiary> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Apiary>) => response.ok),
        map((apiary: HttpResponse<Apiary>) => apiary.body)
      );
    }
    return of(new Apiary());
  }
}

const routes: Routes = [
  {
    path: '',
    component: ApiaryPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApiaryUpdatePage,
    resolve: {
      data: ApiaryResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApiaryDetailPage,
    resolve: {
      data: ApiaryResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApiaryUpdatePage,
    resolve: {
      data: ApiaryResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [ApiaryPage, ApiaryUpdatePage, ApiaryDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class ApiaryPageModule {}
