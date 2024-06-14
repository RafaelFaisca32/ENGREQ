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

import { CrestPage } from './crest';
import { CrestUpdatePage } from './crest-update';
import { Crest, CrestService, CrestDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class CrestResolve implements Resolve<Crest> {
  constructor(private service: CrestService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Crest> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Crest>) => response.ok),
        map((crest: HttpResponse<Crest>) => crest.body)
      );
    }
    return of(new Crest());
  }
}

const routes: Routes = [
  {
    path: '',
    component: CrestPage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CrestUpdatePage,
    resolve: {
      data: CrestResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CrestDetailPage,
    resolve: {
      data: CrestResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CrestUpdatePage,
    resolve: {
      data: CrestResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [CrestPage, CrestUpdatePage, CrestDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class CrestPageModule {}
