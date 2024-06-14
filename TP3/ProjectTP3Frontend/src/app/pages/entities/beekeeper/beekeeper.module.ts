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

import { BeekeeperPage } from './beekeeper';
import { BeekeeperUpdatePage } from './beekeeper-update';
import { Beekeeper, BeekeeperService, BeekeeperDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class BeekeeperResolve implements Resolve<Beekeeper> {
  constructor(private service: BeekeeperService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Beekeeper> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Beekeeper>) => response.ok),
        map((beekeeper: HttpResponse<Beekeeper>) => beekeeper.body)
      );
    }
    return of(new Beekeeper());
  }
}

const routes: Routes = [
  {
    path: '',
    component: BeekeeperPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BeekeeperUpdatePage,
    resolve: {
      data: BeekeeperResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BeekeeperDetailPage,
    resolve: {
      data: BeekeeperResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BeekeeperUpdatePage,
    resolve: {
      data: BeekeeperResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [BeekeeperPage, BeekeeperUpdatePage, BeekeeperDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class BeekeeperPageModule {}
