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

import { FramePage } from './frame';
import { FrameUpdatePage } from './frame-update';
import { Frame, FrameService, FrameDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class FrameResolve implements Resolve<Frame> {
  constructor(private service: FrameService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Frame> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Frame>) => response.ok),
        map((frame: HttpResponse<Frame>) => frame.body)
      );
    }
    return of(new Frame());
  }
}

const routes: Routes = [
  {
    path: '',
    component: FramePage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FrameUpdatePage,
    resolve: {
      data: FrameResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FrameDetailPage,
    resolve: {
      data: FrameResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FrameUpdatePage,
    resolve: {
      data: FrameResolve,
    },
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  declarations: [FramePage, FrameUpdatePage, FrameDetailPage],
  imports: [IonicModule, FormsModule, ReactiveFormsModule, CommonModule, TranslateModule, RouterModule.forChild(routes)],
})
export class FramePageModule {}
