import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { IonicModule } from '@ionic/angular';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../../services/user/user.service';
import { ApiaryInformationUpdatePage } from './apiary-information-update';

describe('ApiaryInformationUpdatePage', () => {
  let component: ApiaryInformationUpdatePage;
  let fixture: ComponentFixture<ApiaryInformationUpdatePage>;

  const entityMock = {
    id: 0,
    user: {},
  };

  const userServiceMock = {
    findAll: (): any => of([]),
  };

  const activatedRouteMock = { data: of({ data: entityMock }) } as any as ActivatedRoute;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ApiaryInformationUpdatePage],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      imports: [
        TranslateModule.forRoot(),
        NgxWebstorageModule.forRoot(),
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
        ReactiveFormsModule,
        IonicModule,
      ],
      providers: [
        { provide: ActivatedRoute, useValue: activatedRouteMock },
        { provide: UserService, useValue: userServiceMock },
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiaryInformationUpdatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('OnInit', () => {
    expect(component.apiaryInformation).toEqual(entityMock);
  });
});
