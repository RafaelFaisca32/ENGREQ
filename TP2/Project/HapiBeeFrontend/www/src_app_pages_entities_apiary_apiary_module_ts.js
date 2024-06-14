"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_apiary_apiary_module_ts"],{

/***/ 37588:
/*!********************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary-update.ts ***!
  \********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryUpdatePage": () => (/* binding */ ApiaryUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _apiary_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./apiary-update.html?ngResource */ 7290);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _apiary_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary.model */ 23447);
/* harmony import */ var _apiary_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./apiary.service */ 17586);
/* harmony import */ var _zone__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../zone */ 88434);
/* harmony import */ var _beekeeper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../beekeeper */ 1439);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! rxjs/operators */ 86942);











let ApiaryUpdatePage = class ApiaryUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, zoneService, beekeeperService, apiaryService) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.zoneService = zoneService;
        this.beekeeperService = beekeeperService;
        this.apiaryService = apiaryService;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            name: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            state: [null, []],
            number: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            intensive: ['false', [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            transhumance: ['false', [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            zoneId: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required]],
            beekeeperId: [null, []],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    ngOnInit() {
        this.zoneService.query({ filter: 'apiary-is-null' }).subscribe(data => {
            this.zones = [];
            if (!this.apiary.zoneId) {
                for (let i = 0; i < data.body.length; i++) {
                    this.apiaryService.query({ 'zoneId.equals': data.body[i].id }).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_6__.filter)((res) => res.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_7__.map)((res) => res.body)).subscribe(apiaryData => {
                        if (apiaryData.length == 0) {
                            this.zones.push(data.body[i]);
                        }
                    });
                }
            }
            else {
                this.zoneService.find(this.apiary.zoneId).subscribe((subData) => {
                    this.zones = [subData.body].concat(subData.body);
                }, error => this.onError(error));
            }
        }, error => this.onError(error));
        this.beekeeperService.query().subscribe(data => {
            this.beekeepers = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.apiary = response.data;
            this.isNew = this.apiary.id === null || this.apiary.id === undefined;
            this.updateForm(this.apiary);
        });
    }
    updateForm(apiary) {
        this.form.patchValue({
            id: apiary.id,
            name: apiary.name,
            state: apiary.state,
            number: apiary.number,
            intensive: apiary.intensive,
            transhumance: apiary.transhumance,
            zoneId: apiary.zoneId,
            beekeeperId: apiary.beekeeperId,
        });
    }
    save() {
        this.isSaving = true;
        const apiary = this.createFromForm();
        apiary.beekeeperId = parseInt(localStorage.getItem("beekeeperId"));
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.apiaryService.update(apiary));
        }
        else {
            this.subscribeToSaveResponse(this.apiaryService.create(apiary));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_8__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Apiary ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            if (action = 'created') {
                let navigationExtras = { state: { apiaryId: response.body.id }, queryParams: { apiaryId: response.body.id } };
                yield this.navController.navigateForward('/tabs/entities/hive/new', navigationExtras);
            }
            else {
                yield this.navController.navigateBack('/tabs/entities/apiary');
            }
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_8__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _apiary_model__WEBPACK_IMPORTED_MODULE_1__.Apiary()), { id: this.form.get(['id']).value, name: this.form.get(['name']).value, state: 2 /* "PENDING" */, number: this.form.get(['number']).value, intensive: this.form.get(['intensive']).value, transhumance: this.form.get(['transhumance']).value, zoneId: this.form.get(['zoneId']).value, beekeeperId: this.form.get(['beekeeperId']).value });
    }
    compareZone(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackZoneById(index, item) {
        return item.id;
    }
    compareBeekeeper(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackBeekeeperById(index, item) {
        return item.id;
    }
};
ApiaryUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_9__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_10__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_10__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_10__.ToastController },
    { type: _zone__WEBPACK_IMPORTED_MODULE_3__.ZoneService },
    { type: _beekeeper__WEBPACK_IMPORTED_MODULE_4__.BeekeeperService },
    { type: _apiary_service__WEBPACK_IMPORTED_MODULE_2__.ApiaryService }
];
ApiaryUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_8__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_11__.Component)({
        selector: 'page-apiary-update',
        template: _apiary_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ApiaryUpdatePage);



/***/ }),

/***/ 59054:
/*!********************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary.module.ts ***!
  \********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ApiaryPageModule": () => (/* binding */ ApiaryPageModule),
/* harmony export */   "ApiaryResolve": () => (/* binding */ ApiaryResolve)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @ngx-translate/core */ 87514);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/common */ 36362);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../services/auth/user-route-access.service */ 51284);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs */ 64139);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 59151);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! rxjs/operators */ 86942);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./apiary */ 30267);
/* harmony import */ var _apiary_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./apiary-update */ 37588);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 50180);













let ApiaryResolve = class ApiaryResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((apiary) => apiary.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Apiary());
    }
};
ApiaryResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.ApiaryService }
];
ApiaryResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], ApiaryResolve);

const routes = [
    {
        path: '',
        component: _apiary__WEBPACK_IMPORTED_MODULE_1__.ApiaryPage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _apiary_update__WEBPACK_IMPORTED_MODULE_2__.ApiaryUpdatePage,
        resolve: {
            data: ApiaryResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.ApiaryDetailPage,
        resolve: {
            data: ApiaryResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _apiary_update__WEBPACK_IMPORTED_MODULE_2__.ApiaryUpdatePage,
        resolve: {
            data: ApiaryResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let ApiaryPageModule = class ApiaryPageModule {
};
ApiaryPageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_apiary__WEBPACK_IMPORTED_MODULE_1__.ApiaryPage, _apiary_update__WEBPACK_IMPORTED_MODULE_2__.ApiaryUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.ApiaryDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], ApiaryPageModule);



/***/ }),

/***/ 7290:
/*!*********************************************************************!*\
  !*** ./src/app/pages/entities/apiary/apiary-update.html?ngResource ***!
  \*********************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-back-button></ion-back-button>\n    </ion-buttons>\n    <ion-title>Criar/Editar Apiário</ion-title>\n\n    <ion-buttons slot=\"end\">\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\n      </ion-button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class=\"ion-padding\">\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\n    <ion-list>\n      <ion-item [hidden]=\"!form.id\">\n        <ion-label>ID</ion-label>\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Nome do apiário</ion-label>\n        <ion-input type=\"text\" name=\"name\" formControlName=\"name\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label position=\"floating\">Número do apiário</ion-label>\n        <ion-input type=\"number\" name=\"number\" formControlName=\"number\"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label>Cultura intensiva?</ion-label>\n        <ion-checkbox formControlName=\"intensive\"></ion-checkbox>\n      </ion-item>\n      <ion-item>\n        <ion-label>Apiário Transumante?</ion-label>\n        <ion-checkbox formControlName=\"transhumance\"></ion-checkbox>\n      </ion-item>\n      <ion-item>\n        <ion-label>Zona do mesmo</ion-label>\n        <ion-select id=\"field_zone\" formControlName=\"zoneId\" [compareWith]=\"compareZone\">\n          <ion-select-option [value]=\"zoneOption.id\" *ngFor=\"let zoneOption of zones; trackBy: trackZoneById\"\n            >{{zoneOption.name}}</ion-select-option>\n        </ion-select>\n      </ion-item>\n    </ion-list>\n  </form>\n</ion-content>\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_apiary_apiary_module_ts.js.map