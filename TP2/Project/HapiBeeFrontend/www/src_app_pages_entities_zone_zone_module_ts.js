"use strict";
(self["webpackChunkapp"] = self["webpackChunkapp"] || []).push([["src_app_pages_entities_zone_zone_module_ts"],{

/***/ 98356:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/zone/zone-update.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ZoneUpdatePage": () => (/* binding */ ZoneUpdatePage)
/* harmony export */ });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! tslib */ 42321);
/* harmony import */ var _zone_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./zone-update.html?ngResource */ 50794);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ 3184);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ 90587);
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ionic/angular */ 93819);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ 52816);
/* harmony import */ var _zone_model__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./zone.model */ 79848);
/* harmony import */ var _zone_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./zone.service */ 10347);
/* harmony import */ var _apiary__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../apiary */ 50180);









let ZoneUpdatePage = class ZoneUpdatePage {
    constructor(activatedRoute, navController, formBuilder, platform, toastCtrl, apiaryService, zoneService) {
        this.activatedRoute = activatedRoute;
        this.navController = navController;
        this.formBuilder = formBuilder;
        this.platform = platform;
        this.toastCtrl = toastCtrl;
        this.apiaryService = apiaryService;
        this.zoneService = zoneService;
        this.isSaving = false;
        this.isNew = true;
        this.form = this.formBuilder.group({
            id: [null, []],
            name: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            number: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            coordX: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            coordY: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            coordZ: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
            controlledZone: ['false', [_angular_forms__WEBPACK_IMPORTED_MODULE_4__.Validators.required]],
        });
        // Watch the form for changes, and
        this.form.valueChanges.subscribe(v => {
            this.isReadyToSave = this.form.valid;
        });
    }
    ngOnInit() {
        this.apiaryService.query().subscribe(data => {
            this.apiaries = data.body;
        }, error => this.onError(error));
        this.activatedRoute.data.subscribe(response => {
            this.zone = response.data;
            this.isNew = this.zone.id === null || this.zone.id === undefined;
            this.updateForm(this.zone);
        });
    }
    updateForm(zone) {
        this.form.patchValue({
            id: zone.id,
            name: zone.name,
            number: zone.number,
            coordX: zone.coordX,
            coordY: zone.coordY,
            coordZ: zone.coordZ,
            controlledZone: zone.controlledZone,
        });
    }
    save() {
        this.isSaving = true;
        const zone = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.zoneService.update(zone));
        }
        else {
            this.subscribeToSaveResponse(this.zoneService.create(zone));
        }
    }
    subscribeToSaveResponse(result) {
        result.subscribe((res) => this.onSaveSuccess(res), (res) => this.onError(res.error));
    }
    onSaveSuccess(response) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            let action = 'updated';
            if (response.status === 201) {
                action = 'created';
            }
            this.isSaving = false;
            const toast = yield this.toastCtrl.create({ message: `Zone ${action} successfully.`, duration: 2000, position: 'middle' });
            yield toast.present();
            yield this.navController.navigateBack('/tabs/entities/zone');
        });
    }
    previousState() {
        window.history.back();
    }
    onError(error) {
        return (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__awaiter)(this, void 0, void 0, function* () {
            this.isSaving = false;
            console.error(error);
            const toast = yield this.toastCtrl.create({ message: 'Failed to load data', duration: 2000, position: 'middle' });
            yield toast.present();
        });
    }
    createFromForm() {
        return Object.assign(Object.assign({}, new _zone_model__WEBPACK_IMPORTED_MODULE_1__.Zone()), { id: this.form.get(['id']).value, name: this.form.get(['name']).value, number: this.form.get(['number']).value, coordX: this.form.get(['coordX']).value, coordY: this.form.get(['coordY']).value, coordZ: this.form.get(['coordZ']).value, controlledZone: this.form.get(['controlledZone']).value });
    }
    compareApiary(first, second) {
        return first && first.id && second && second.id ? first.id === second.id : first === second;
    }
    trackApiaryById(index, item) {
        return item.id;
    }
};
ZoneUpdatePage.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_6__.ActivatedRoute },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.NavController },
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_4__.FormBuilder },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.Platform },
    { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_7__.ToastController },
    { type: _apiary__WEBPACK_IMPORTED_MODULE_3__.ApiaryService },
    { type: _zone_service__WEBPACK_IMPORTED_MODULE_2__.ZoneService }
];
ZoneUpdatePage = (0,tslib__WEBPACK_IMPORTED_MODULE_5__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Component)({
        selector: 'page-zone-update',
        template: _zone_update_html_ngResource__WEBPACK_IMPORTED_MODULE_0__,
    })
], ZoneUpdatePage);



/***/ }),

/***/ 74602:
/*!****************************************************!*\
  !*** ./src/app/pages/entities/zone/zone.module.ts ***!
  \****************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ZonePageModule": () => (/* binding */ ZonePageModule),
/* harmony export */   "ZoneResolve": () => (/* binding */ ZoneResolve)
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
/* harmony import */ var _zone__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./zone */ 9927);
/* harmony import */ var _zone_update__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./zone-update */ 98356);
/* harmony import */ var ___WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! . */ 88434);













let ZoneResolve = class ZoneResolve {
    constructor(service) {
        this.service = service;
    }
    resolve(route, state) {
        const id = route.params.id ? route.params.id : null;
        if (id) {
            return this.service.find(id).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.filter)((response) => response.ok), (0,rxjs_operators__WEBPACK_IMPORTED_MODULE_5__.map)((zone) => zone.body));
        }
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.of)(new ___WEBPACK_IMPORTED_MODULE_3__.Zone());
    }
};
ZoneResolve.ctorParameters = () => [
    { type: ___WEBPACK_IMPORTED_MODULE_3__.ZoneService }
];
ZoneResolve = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.Injectable)({ providedIn: 'root' })
], ZoneResolve);

const routes = [
    {
        path: '',
        component: _zone__WEBPACK_IMPORTED_MODULE_1__.ZonePage,
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: 'new',
        component: _zone_update__WEBPACK_IMPORTED_MODULE_2__.ZoneUpdatePage,
        resolve: {
            data: ZoneResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/view',
        component: ___WEBPACK_IMPORTED_MODULE_3__.ZoneDetailPage,
        resolve: {
            data: ZoneResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
    {
        path: ':id/edit',
        component: _zone_update__WEBPACK_IMPORTED_MODULE_2__.ZoneUpdatePage,
        resolve: {
            data: ZoneResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
        },
        canActivate: [_services_auth_user_route_access_service__WEBPACK_IMPORTED_MODULE_0__.UserRouteAccessService],
    },
];
let ZonePageModule = class ZonePageModule {
};
ZonePageModule = (0,tslib__WEBPACK_IMPORTED_MODULE_7__.__decorate)([
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_8__.NgModule)({
        declarations: [_zone__WEBPACK_IMPORTED_MODULE_1__.ZonePage, _zone_update__WEBPACK_IMPORTED_MODULE_2__.ZoneUpdatePage, ___WEBPACK_IMPORTED_MODULE_3__.ZoneDetailPage],
        imports: [_ionic_angular__WEBPACK_IMPORTED_MODULE_9__.IonicModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.FormsModule, _angular_forms__WEBPACK_IMPORTED_MODULE_10__.ReactiveFormsModule, _angular_common__WEBPACK_IMPORTED_MODULE_11__.CommonModule, _ngx_translate_core__WEBPACK_IMPORTED_MODULE_12__.TranslateModule, _angular_router__WEBPACK_IMPORTED_MODULE_13__.RouterModule.forChild(routes)],
    })
], ZonePageModule);



/***/ }),

/***/ 50794:
/*!*****************************************************************!*\
  !*** ./src/app/pages/entities/zone/zone-update.html?ngResource ***!
  \*****************************************************************/
/***/ ((module) => {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-back-button></ion-back-button>\r\n    </ion-buttons>\r\n    <ion-title>Zone</ion-title>\r\n\r\n    <ion-buttons slot=\"end\">\r\n      <ion-button [disabled]=\"!isReadyToSave\" (click)=\"save()\" color=\"primary\">\r\n        <span *ngIf=\"platform.is('ios')\">{{'DONE_BUTTON' | translate}}</span>\r\n        <ion-icon name=\"checkmark\" *ngIf=\"!platform.is('ios')\"></ion-icon>\r\n      </ion-button>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n\r\n<ion-content class=\"ion-padding\">\r\n  <form *ngIf=\"form\" name=\"form\" [formGroup]=\"form\" (ngSubmit)=\"save()\">\r\n    <ion-list>\r\n      <ion-item [hidden]=\"!form.id\">\r\n        <ion-label>ID</ion-label>\r\n        <ion-input type=\"hidden\" id=\"id\" formControlName=\"id\" readonly></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Name</ion-label>\r\n        <ion-input type=\"text\" name=\"name\" formControlName=\"name\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Number</ion-label>\r\n        <ion-input type=\"number\" name=\"number\" formControlName=\"number\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Coord X</ion-label>\r\n        <ion-input type=\"text\" name=\"coordX\" formControlName=\"coordX\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Coord Y</ion-label>\r\n        <ion-input type=\"text\" name=\"coordY\" formControlName=\"coordY\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label position=\"floating\">Coord Z</ion-label>\r\n        <ion-input type=\"text\" name=\"coordZ\" formControlName=\"coordZ\"></ion-input>\r\n      </ion-item>\r\n      <ion-item>\r\n        <ion-label>Controlled Zone</ion-label>\r\n        <ion-checkbox formControlName=\"controlledZone\"></ion-checkbox>\r\n      </ion-item>\r\n    </ion-list>\r\n  </form>\r\n</ion-content>\r\n";

/***/ })

}]);
//# sourceMappingURL=src_app_pages_entities_zone_zone_module_ts.js.map