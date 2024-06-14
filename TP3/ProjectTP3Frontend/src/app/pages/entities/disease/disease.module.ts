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

import { DiseasePage } from './disease';
import { DiseaseUpdatePage } from './disease-update';
import { Disease, DiseaseService, DiseaseDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class DiseaseResolve implements Resolve<Disease> {
  constructor(private service: DiseaseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Disease> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Disease>) => response.ok),
        map((disease: HttpResponse<Disease>) => disease.body)
      );
    }
    return of(new Disease());
  }
}

const routes: Routes = [
  {
    path: '',
    component: DiseasePage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DiseaseUpdatePage,
    resolve: {
      data: DiseaseResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DiseaseDetailPage,
    resolve: {
      data: DiseaseResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DiseaseUpdatePage,
    resolve: {
      data: DiseaseResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [DiseasePage, DiseaseUpdatePage, DiseaseDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class DiseasePageModule {}
