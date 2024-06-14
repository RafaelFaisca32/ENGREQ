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

import { UnfoldingPage } from './unfolding';
import { UnfoldingUpdatePage } from './unfolding-update';
import { Unfolding, UnfoldingService, UnfoldingDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class UnfoldingResolve implements Resolve<Unfolding> {
  constructor(private service: UnfoldingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Unfolding> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Unfolding>) => response.ok),
        map((unfolding: HttpResponse<Unfolding>) => unfolding.body)
      );
    }
    return of(new Unfolding());
  }
}

const routes: Routes = [
  {
    path: '',
    component: UnfoldingPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UnfoldingUpdatePage,
    resolve: {
      data: UnfoldingResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UnfoldingDetailPage,
    resolve: {
      data: UnfoldingResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UnfoldingUpdatePage,
    resolve: {
      data: UnfoldingResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [UnfoldingPage, UnfoldingUpdatePage, UnfoldingDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class UnfoldingPageModule {}
